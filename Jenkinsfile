pipeline{
    agent any
    stages {
        stage('Build'){
            steps {
                echo 'Building the application.'
            }
        }


        stage('Checkout') {
            steps {
                checkout scm

                script {
                    env.GIT_COMMIT_SHA = sh(
                        script: 'git rev-parse HEAD',
                        returnStdout: true
                    ).trim()
                }

                echo "Checked out commit: ${env.GIT_COMMIT_SHA}"
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


        stage('Create Build Metadata') {
            steps {
                sh '''
                    set -eu

                    cat > build-metadata.json <<EOF
        {
          "buildNumber": "${BUILD_NUMBER}",
          "gitCommit": "${GIT_COMMIT_SHA}",
          "artifact": "s3://lambda-cicd-platform-artifacts-491766120496-ap-south-1/builds/${BUILD_NUMBER}/lambda-cicd-platform-1.0.0.jar"
        }
        EOF
                '''

                archiveArtifacts artifacts: 'build-metadata.json',
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
