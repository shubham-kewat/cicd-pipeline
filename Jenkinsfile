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

        stage('Upload Artifact to S3') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'aws-artifact-uploader',
                        usernameVariable: 'AWS_ACCESS_KEY_ID',
                        passwordVariable: 'AWS_SECRET_ACCESS_KEY'
                    )
                ]) {
                    sh '''
                        set -eu

                        export AWS_DEFAULT_REGION=ap-south-1

                        aws sts get-caller-identity \
                            --query Account \
                            --output text

                        aws s3 cp \
                            app/target/lambda-cicd-platform-1.0.0.jar \
                            s3://lambda-cicd-platform-artifacts-491766120496-ap-south-1/builds/${BUILD_NUMBER}/lambda-cicd-platform-1.0.0.jar \
                            --only-show-errors

                        aws s3api head-object \
                            --bucket lambda-cicd-platform-artifacts-491766120496-ap-south-1 \
                            --key builds/${BUILD_NUMBER}/lambda-cicd-platform-1.0.0.jar \
                            --query ContentLength \
                            --output text
                    '''
                }
            }
        }

    }
}
