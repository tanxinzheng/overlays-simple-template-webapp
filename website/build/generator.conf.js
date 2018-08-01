/**
 * Created by tanxinzheng on 17/12/11.
 */
var mysql = require('promise-mysql');
let database='xmo_demo'
let url     ='127.0.0.1'
let username='root'
let password='123456'

mysql.createConnection({
  host: url,
  user: username,
  password: password,
  database: database
}).then(function(conn){
  // do stuff with conn
  var result = conn.query(`
  select
        t.table_schema tableSchema,
        t.table_name tableName,
        t.column_comment columnComment,
        t.CHARACTER_maximum_LENGTH length,
        t.numeric_scale scale,
        case column_key when 'PRI' then 1 else 0 end as isPrimaryKey,
        upper(t.data_type) jdbcType,
        t.column_name actualColumnName,
        t.column_default defaultValue,
        t.is_nullable nullable
        from information_schema.columns t
  `)
  conn.end();
  return result;
}).then((rows)=>{
  console.log(rows);
});
