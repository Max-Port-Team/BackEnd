var express = require('express');
var router = express.Router();
var query = require('../mysql/pool')

router.get('/queryAllArticle', function (req, res, next) {
    query('SELECT id,title,intro,time,author,tag,visit FROM article ORDER BY RAND() LIMIT 10', [], (err, result) => {
        if (err) {
            res.status(500);
            res.render('error');
        }
        else { res.send(result); }
    });
});

router.get('/queryAllArticleByAuthor', function (req, res, next) {
    let author = req.body.authorId;
    query('SELECT id,title,intro,time,author,tag,visit FROM article WHERE author=? ORDER BY id desc LIMIT 10', [author], (err, result) => {
        if (err) {
            res.status(500);
            res.render('error');
        }
        else {
            let ArticleTen = result;
            query('SELECT id FROM article where author = ? ORDER BY id desc', [author], (err, result) => {
                if (err) {
                    res.status(500);
                    res.render('error');
                }
                else {
                    let articleList = [];
                    result.forEach((val) => {articleList.push(val.id)});
                    res.send({ articleArr: ArticleTen, articleList });
                }
            });
        }
    });
    
});

router.get('/queryArticleByArticleId', function (req, res, next) {
    let articleList = req.body.articleId.join(',')
    console.log(articleList);
    query('SELECT id,title,intro,time,author,tag,visit FROM article where id in (' + articleList+')', [], (err, result) => {
        if (err) {
            res.status(500);
            res.render('error');
        }
        else { res.send(result); }
    });
});

router.get('/queryDetailArticle', function (req, res, next) {
    let article = req.body.articleId
    query('SELECT * FROM article where id=?', [article], (err, result) => {
        if (err) {
            res.status(500);
            res.render('error');
        }
        else { res.send(result[0]); }
    });
});

router.post('/addArticle', function (req, res, next) {
    query('SELECT * FROM people where sid=?', [req.cookies.sid], (err, result) => {
        if (err) {
            res.status(500);
            res.render('error');
        }
        else {
            if (!result.length) {//无匹配
                res.send({ status: false })
            }
            else {
                query('insert into article (title,tag,intro,body,author) values(?,?,?,?,?)', [req.body.title, req.body.tag, req.body.intro, req.body.body,result[0].id], (err, result) => {
                    if (err) {
                        res.status(500);
                        res.render('error');
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
            res.render('error');
        }
        else {
            if (!result.length) {//无匹配
                res.send({ status: false })
            }
            else {
                let userId = result[0].id;
                query('select * from article where id=?', [req.body.id], (err, result) => {
                    if (err) {
                        res.status(500);
                        res.render('error');
                    }
                    else {
                        if (!result.length||result[0].author != userId) { res.send({ status: false }) }
                        else {
                            query('update article set title=?,tag=?,intro=?,body=? where id=?', [req.body.title,req.body.tag, req.body.intro, req.body.body,req.body.id], (err, result) => {
                                if (err) {
                                    res.status(500);
                                    res.render('error');
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
