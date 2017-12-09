cd /www/var/html/invite
git pull
cd /opt/blocks-limited-api/
git pull
gradle build
nohup java -jar build/libs/blocks-limited-api-0.0.1.jar > /opt/log/blocks-limited-api/spring.log  2>&1 &