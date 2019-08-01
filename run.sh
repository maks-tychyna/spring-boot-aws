git reset --hard
git checkout origin/master
mvn clean package
java -jar target/spring-boot-aws.jar
