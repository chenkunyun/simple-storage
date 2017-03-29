#/bin/bash
export STORAGE_TYPE=mysql
export MYSQL_DB=zipkin
export MYSQL_USER=root
export MYSQL_PASS=123456
export MYSQL_HOST=localhost
export MYSQL_TCP_PORT=3306
java -jar /Users/kchen/Library/Dev/zipkin-server/zipkin-server-1.21.0 --server.port=9411
