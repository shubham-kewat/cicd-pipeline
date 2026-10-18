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
    }
}