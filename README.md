# AWS CodePipeline and CloudFormation stacks

## Technology stack:
 - AWS CodePipeline is created with a CloudFormation template
 - Application language is Java 8 (Spring Boot)
 - Git and Maven are used to checkout the repository and build fat jar within EC2 instance

**The deployed application is able to upload files into S3 bucket**

**When running the CF stack you should provide the following parameters:**
```
- GitHubOAuthToken
- ApplicationStackName
- JasyptEncryptorPassword (this is required for encrypting the RDS MySQL DB password in the properties file)
```
 