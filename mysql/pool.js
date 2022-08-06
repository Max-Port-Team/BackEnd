const mysql = require('mysql2');

const pool = mysql.createPool({
    host: '43.156.106.129',
    port: 65514,
    user: 'root',
    password: '7hHeKT5jJxVGsU',
    database: 'MaxPort'
})

let query = (sql, params,callback) => {
    pool.getConnection((err, connection) => {
        if (err) { return callback(err) }
        connection.query(sql, params, (err, res) => {
            connection.release();
            console.log(sql,err, res);
            if (err) { return callback(err) }
            return callback(err, res)
        })
    })
}

module.exports = query