var express = require('express');
var router = express.Router();
var query = require('../mysql/pool')

let AddAuthorName = (result) => {
    return new Promise((resolve, reject) => {
        if (!result.length) { resolve(result); }
        let flag = result.length;
        result.forEach(function (val, index) {
            query('SELECT nickname FROM people where id = ?', [val.author], (err, res) => {
                if (err) { reject(err) }
                else {
                    val.authorName = res[0].nickname;
                    result[index] == val;
                    flag--;
                    if (!flag) {
                        resolve(result);
                    }
                }
            })
        })
    })
}

router.get('/queryAllArticle', function (req, res, next) {
    query('SELECT id,title,intro,time,author,tag,visit FROM article ORDER BY RAND() LIMIT 10', [], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            AddAuthorName(result).then((val) => { res.send(val); }, (err) => { res.status(500); res.send('error')})
        }
    });
});

router.get('/queryAllArticleByTag', function (req, res, next) {
    console.log(req.query.tag);
    query('SELECT id,title,intro,time,author,tag,visit FROM article where tag=? ORDER BY RAND() LIMIT 10', [req.query.tag], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            AddAuthorName(result).then((val) => { res.send(val); }, (err) => { res.status(500); res.send('error') })
        }
    });
});

router.get('/queryAllArticleByAuthor', function (req, res, next) {
    let author = req.query.authorId;
    query('SELECT id,title,intro,time,author,tag,visit FROM article WHERE author=? ORDER BY id desc LIMIT 10', [author], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            let ArticleTen = result;
            query('SELECT id FROM article where author = ? ORDER BY id desc', [author], (err, result) => {
                if (err) {
                    res.status(500);
                    res.send('error');
                }
                else {
                    let articleList = [];
                    result.forEach((val) => { articleList.push(val.id) });
                    res.send({ articleArr: ArticleTen, articleList });
                }
            });
        }
    });

});

router.get('/queryArticleByArticleId', function (req, res, next) {
    let articleList = req.query.articleId
    console.log(articleList);
    query('SELECT id,title,intro,time,author,tag,visit FROM article where id in (' + articleList + ')', [], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else { AddAuthorName(result).then((val) => { res.send(val); }, (err) => { res.status(500); res.send('error') }) }
    });
});

router.get('/queryDetailArticle', function (req, res, next) {
    let article = req.query.articleId
    query('SELECT * FROM article where id=?', [article], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else { res.send(result[0]); }
    });
});

router.post('/addArticle', function (req, res, next) {
    query('SELECT * FROM people where sid=?', [req.cookies.sid], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            if (!result.length) {//无匹配
                res.status(403);
                res.send({ status: false })
            }
            else {
                query('insert into article (title,tag,intro,body,author) values(?,?,?,?,?)', [req.body.title, req.body.tag, req.body.intro, req.body.body, result[0].id], (err, result) => {
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
    });
});

router.put('/updateArticle', function (req, res, next) {
    query('SELECT * FROM people where sid=?', [req.cookies.sid], (err, result) => {
        if (err) {
            res.status(500);
            res.send('error');
        }
        else {
            if (!result.length) {//无匹配
                res.status(403);
                res.send({ status: false })
            }
            else {
                let userId = result[0].id;
                query('select * from article where id=?', [req.body.id], (err, result) => {
                    if (err) {
                        res.status(500);
                        res.send('error');
                    }
                    else {
                        if (!result.length || result[0].author != userId) { res.send({ status: false }) }
                        else {
                            query('update article set title=?,tag=?,intro=?,body=? where id=?', [req.body.title, req.body.tag, req.body.intro, req.body.body, req.body.id], (err, result) => {
                                if (err) {
                                    res.status(500);
                                    res.send('error');
                                }
                                else { res.send({ status: true }) }
                            })
                        }
                    }
                })
            }
        }
    });
});

module.exports = router;
