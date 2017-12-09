cd /www/var/html/invite
git pull
cd /opt/blocks-limited-api/
git pull
gradle build
kill -9 `ps -ef | grep blocks-limited-api|egrep -v "grep"|awk '{print $2}'`
nohup java -jar build/libs/blocks-limited-api-0.0.1.jar > /opt/log/blocks-limited-api/spring.log  2>&1 &