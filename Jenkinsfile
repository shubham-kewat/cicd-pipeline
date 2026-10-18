pipeline{
    agent any
    stages {
        stage('Build'){
            steps {
                echo 'Building the application.'
            }
        }

        stage('Checkout'){
            steps{
                echo 'First time something to stage in github'
            }
        }

        stage('Test') {
            steps {
                dir('app') {
                    sh 'mvn clean test'
                }
            }
        }

        stage('Package') {
            steps {
                dir('app') {
                    sh 'mvn -DskipTests package'
                }
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'app/target/lambda-cicd-platform-1.0.0.jar',
                                 fingerprint: true
            }
        }
    }
}