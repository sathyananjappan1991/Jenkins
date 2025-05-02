pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9' // Must match name in Jenkins Global Tool Configuration
    }

    parameters {
        choice(name: 'BRANCH_NAME', choices: ['main', 'dev', 'feature'], description: 'Select Git branch to build.')
        choice(name: 'ENVIRONMENT', choices: ['qa', 'uat', 'prod'], description: 'Choose environment for deployment.')
    }

    environment {
        JAVA_HOME = 'C:/Program Files/Java/jdk-17' // Adjust this path as per your JDK installation
        PATH = "${JAVA_HOME}/bin;${env.PATH}"
    }

    triggers {
        githubPush() // Automatically trigger build on GitHub push
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

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                script {
                    switch(params.ENVIRONMENT) {
                        case 'qa':
                            echo "Deploying to QA environment..."
                            // Add deployment script or command
                            break
                        case 'uat':
                            echo "Deploying to UAT environment..."
                            // Add deployment script or command
                            break
                        case 'prod':
                            echo "Preparing for Production deployment..."
                            input message: "Confirm Production Deployment", ok: "Deploy"
                            // Add production deployment steps
                            break
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            cleanWs()
        }
        success {
            echo '✅ Build & deployment succeeded.'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
    }
}
