# Lambda CI/CD Platform (Learning Project)

A small Java 21 + Spring Boot application with a static REST endpoint. The goal is to learn Git, Maven, Jenkins, artifact packaging, and then AWS Lambda deployment—not business logic.

## Project layout

```text
lambda-cicd-platform/
├── app/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/example/lambdacicd/
│       │   ├── Application.java
│       │   ├── StreamLambdaHandler.java
│       │   └── controller/HelloController.java
│       └── test/java/com/example/lambdacicd/controller/
│           └── HelloControllerTest.java
├── Jenkinsfile
├── .gitignore
└── README.md
```

## Endpoint

`GET /api/hello`

Example response:

```json
{
  "message": "Hello from the Lambda CI/CD learning project!",
  "service": "lambda-cicd-platform",
  "version": "1.0.0"
}
```

## Prerequisites

- JDK 21
- Maven 3.9+
- Git
- Jenkins installed locally (your existing macOS/Homebrew installation is fine)

Check your tools:

```bash
java -version
mvn -version
git --version
```

## Build and test locally

From the project root:

```bash
cd app
mvn clean test
mvn clean package
```

The shaded deployment JAR should be created at:

`app/target/lambda-cicd-platform-1.0.0.jar`

Run as a regular Spring Boot web app for a quick local check:

```bash
mvn spring-boot:run
```

Then in another terminal:

```bash
curl http://localhost:8080/api/hello
```

## Jenkins starter pipeline

The root `Jenkinsfile` checks out the repository, runs tests, packages the app, and archives the JAR. It intentionally does not deploy to AWS yet; first get the repeatable CI build working.

Create a Jenkins Pipeline job and point it at your Git repository, using branch `main` and script path `Jenkinsfile`. Configure a Maven tool named `Maven-3.9` in **Manage Jenkins → Tools** if your Jenkins setup uses a configured Maven installation. If Maven is available on the agent PATH instead, the Jenkinsfile can use it directly.

## AWS Lambda packaging note

This project includes an AWS Serverless Java Container handler and Maven Shade packaging. It is intended as a learning starting point for an API Gateway **REST API / HTTP API proxy event** to Lambda. For a production deployment, validate the chosen API Gateway payload format and Lambda runtime configuration in your AWS account before enabling deployment automation.

The next pipeline iteration can add a manually approved deployment stage that updates an existing Lambda function with the packaged JAR, then smoke-tests the endpoint. We will add AWS credentials through Jenkins credentials / an IAM role—not hard-coded keys.

## Safety

- No AWS resources are created by this project.
- No AWS credentials are stored in this repository.
- Do not commit `.env`, AWS credentials, or local configuration files.
