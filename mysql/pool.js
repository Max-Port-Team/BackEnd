const mysql = require('mysql2');

const pool = mysql.createPool({
    host: 'database',
    port: 3306,
    user: 'root',
    password: '7hHeKT5jJxVGsU',
    database: 'MaxPort'
})

let query = (sql, params, callback) => {
    pool.getConnection((err, connection) => {
        if (err) { return callback(err) }
        connection.query(sql, params, (err, res) => {
            connection.release();
            console.log(sql, err, res);
            if (err) { return callback(err) }
            return callback(err, res)
        })
    })
}

module.exports = query