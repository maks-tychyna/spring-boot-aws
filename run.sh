git reset --hard
git fetch origin
git checkout origin/master
mvn clean package
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 target/spring-boot-aws.jar
