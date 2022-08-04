
## 参数命名
### article类
title
intro
body
author
tag
visit
### people类
nickname
password
avatar

注：id均为后端自动生成，无需添加

## ArticleController

`public List<Article> queryAllArticle() `：查询网站所有文章

请求地址：http://43.156.106.129:65514/MaxPort/article/queryAllArticle

请求参数：无

返回数据：`List<Article>`文章类列表，数据格式为json，编码为utf-8



`public List<Article> queryAllArticleByAuthor(Map<String,String> map)`：根据authorId查询该作者写的文章

请求地址：http://43.156.106.129:65514/MaxPort/article/queryAllArticleByAuthor

请求参数：String authorId 作者的id （需封装在json中）

返回数据：`List<Article>`文章类列表，数据格式为json，编码为utf-8



`public Article queryAllArticleByArticleId(Map<String,String> map)`：根据ArticleId查询该对应的文章

请求地址：http://43.156.106.129:65514/MaxPort/article/queryAllArticleByAuthor

请求参数：String ArticleId 文章的id（需封装在json中）

返回数据：`Article`对应的文章实体，数据格式为json，编码为utf-8



`public Map<String, Object> addArticle(Map<String,String> map)`：将文章除id外各信息封装至一个json中，服务器端接收此json并封装成文章对象，进行添加

请求地址：http://43.156.106.129:65514/MaxPort/article/addArticle

请求参数：Map<String,String> map 文章信息的map （需封装在json中），参数命名需一致

返回数据：map对象，提示是否添加成功，数据格式为json，编码为utf-8



`public Map<String, Object> updateArticle`：将文章除id外各信息封装至一个json中，服务器端接收此json并封装成文章对象，进行更新

请求地址：http://43.156.106.129:65514/MaxPort/article/updateArticle

请求参数：Map<String,String> map 文章信息的map （需封装在json中），参数命名需一致

返回数据：map对象，提示是否添加成功，数据格式为json，编码为utf-8