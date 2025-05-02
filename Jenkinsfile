pipeline {
    agent any

    triggers {
        githubPush()
    }

    tools {
        maven 'Maven_3.9.9'  // Name must match what's configured in Jenkins global tool config
    }

    parameters {
        choice(name: 'BRANCH_NAME', choices: ['master', 'dev', 'main'], description: 'Select the Git branch to build.')
        choice(name: 'ENVIRONMENT', choices: ['qa', 'pp', 'uat', 'prod'], description: 'Select the deployment environment.')
    }

    environment {
        JAVA_HOME = 'C:/Program Files/Java/jdk-17'  // Adjust based on your installed JDK
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH_NAME}", url: 'https://github.com/sathyananjappan1991/Springboot.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (params.ENVIRONMENT == 'qa') {
                        echo "Deploying to QA environment"
                        // Add QA deployment logic here
                    } else if (params.ENVIRONMENT == 'pp') {
                        echo "Deploying to Pre-Prod environment"
                        // Add Pre-Prod deployment logic here
                    } else if (params.ENVIRONMENT == 'uat') {
                        echo "Deploying to UAT environment"
                        // Add UAT deployment logic here
                    } else if (params.ENVIRONMENT == 'prod') {
                        echo "Deploying to Production environment"
                        input message: "Confirm deployment to Production?", ok: "Deploy"
                        // Add Production deployment logic here
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Post-build steps running...'
            // Cleanup or notification steps
        }
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
