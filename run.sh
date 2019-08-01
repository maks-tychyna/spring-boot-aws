git reset --hard
git checkout origin/master
git fetch origin
mvn clean package
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 target/spring-boot-aws.jar
