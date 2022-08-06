var express = require('express');
var router = express.Router();
var query = require('../mysql/pool');
var SHA256 = require("crypto-js/sha256");

router.post('/login', function (req, res, next) {
    let nickname = req.body.nickname;
    let password = req.body.password;
    query('SELECT * FROM people where nickname=? and password=?', [nickname,password], (err, result) => {
        if (err) {
            res.status(500);
            res.render('error');
        }
        else { 
            if (!result.length) {//无匹配
                res.send({status:false})
            }
            else {
                let cookie = SHA256(result[0].id + result[0].password + Date.now()).toString();
                let people = result[0];
                res.cookie('sid', cookie, { httpOnly: true });
                query('update people set sid=? where id = ?', [cookie, result[0].id], (err, result) => {
                    if (err) {
                        res.status(500);
                        res.render('error');
                    }
                    else {
                        res.send({
                            status: true,
                            id: people.id,
                            nickname: people.nickname,
                            avatar: people.avatar,
                        })
                    }
                })
            }
         }
    });
});

router.post('/register', function (req, res, next) {
    let nickname = req.body.nickname;
    let password = req.body.password;
    query('SELECT * FROM people where nickname=?', [nickname], (err, result) => {
        if (err) {
            res.status(500);
            res.render('error');
        }
        else {
            if (result.length) {//有匹配，用户名被占用，返回错误
                res.send({ status: false,errmsg:'用户名被占用！' })
            }
            else {
                query('insert into people (nickname,password) values(?,?)', [nickname, password], (err, result) => {
                    if (err) {
                        res.status(500);
                        res.render('error');
                    }
                    else {
                        res.send({
                            status: true,
                        })
                    }
                })
            }
        }
    });
});

router.post('/logout', function (req, res, next) {
    query('SELECT * FROM people where sid = ?', [req.cookies.sid], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            if (!result.length) {
                res.status(403);
                res.send({ status:false, errmsg: '登录状态异常！' })
            }
            else {
                let people = result[0];
                res.clearCookie('sid');
                query('update people set sid = null where id = ?', [people.id], (err, result) => {
                    if (err) {
                        res.status(500);
                        res.send('error');
                    }
                    else {
                        res.send({status: true})
                    }
                })
            }
            
         }
    })
});

router.get('/get-info-by-id', function (req, res, next) {
    let ids= req.body.join(',')
    query('SELECT id,nickname,avatar FROM people where id in ('+ids+')',[], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            res.send(result);
        }
    });
});

router.get('/get-detailed-by-id', function (req, res, next) {
    let id = req.body.id
    query('SELECT id,nickname,avatar FROM people where id = ?', [id], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            let people = result[0];
            query('SELECT id,title,intro,time,author,tag,visit FROM article where author = ? order by id desc', [id], (err, result) => {
                if (err) {
                    res.status(500);
                    res.send('error');
                }
                else {
                    let articleArr = result.slice(0,10);
                    let articleList = [];
                    result.forEach((val) => { articleList.push(val.id) });
                    res.send({ nickname: people.nickname, avatar: people.avatar, articleArr, articleList });
                }
            });
        }
    });
});

router.put('/put-user-info', function (req, res, next) {
    query('SELECT * FROM people where sid = ?', [req.cookies.sid], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            if (!result.length) {//无匹配
                res.status(403);
                res.send({ errmsg: '登录状态异常！' })
            }
            else {
                let people = result[0];
                let nickname = req.body.nickname || people.nickname;
                let password = req.body.password || people.password;
                let avatar = req.body.avatar || people.avatar;
                if (nickname != people.nickname) {//名字改了，查询是否合法
                    query('SELECT * FROM people where nickname = ?', [nickname], (err, result) => {
                    if (err) {
                        res.status(500);
                        res.send('error');
                    }
                    else {
                        if (result.length) {//重名了，拒绝
                            res.send({ status: false, errmsg: '名称重复了，换一个昵称！' });
                        }
                        else {
                            query('update people set nickname=?,password=?,avatar=? where id = ?', [nickname, password,avatar,people.id], (err, result) => {
                                if (err) {
                                    res.status(500);
                                    res.send('error');
                                }
                                else {
                                    res.send({status: true})
                                }
                            })
                        }
                    }
                });
                }
                else {//不改名字
                    query('update people set password=?,avatar=? where id = ?', [password, avatar, people.id], (err, result) => {
                        if (err) {
                            res.status(500);
                            res.send('error');
                        }
                        else {
                            res.send({ status: true })
                        }
                    })
                }
            }
        }
    });
});


module.exports = router;