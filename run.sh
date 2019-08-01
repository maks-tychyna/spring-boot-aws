git reset --hard
git checkout origin/master
git fetch origin
mvn clean package
java -jar target/spring-boot-aws.jar
