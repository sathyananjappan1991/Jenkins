pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9' // Ensure this matches the name of Maven in Jenkins Global Tool Configuration
    }

    parameters {
        choice(name: 'BRANCH_NAME', choices: ['main', 'dev', 'feature'], description: 'Select Git branch to build.')
        choice(name: 'ENVIRONMENT', choices: ['qa', 'uat', 'prod'], description: 'Choose deployment environment.')
    }

    environment {
        JAVA_HOME = 'C:/Program Files/Java/jdk-17'  // Adjust this path based on your JDK location
 MAVEN_HOME = '/opt/maven/bin/mvn'                   // Adjust to your Maven installation path    }

    triggers {
        githubPush()  // Trigger build when there's a push on GitHub
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH_NAME}", url: 'https://github.com/sathyananjappan1991/Springboot.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'  // Use 'bat' for Windows command execution
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn test'  // Run unit tests using 'bat' on Windows
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true  // Archive the JAR files built by Maven
            }
        }

        stage('Deploy') {
            steps {
                script {
                    switch(params.ENVIRONMENT) {
                        case 'qa':
                            echo "Deploying to QA environment..."
                            // Add QA deployment steps here
                            break
                        case 'uat':
                            echo "Deploying to UAT environment..."
                            // Add UAT deployment steps here
                            break
                        case 'prod':
                            echo "Preparing for Production deployment..."
                            input message: "Confirm Production Deployment", ok: "Deploy"
                            // Add production deployment steps here
                            break
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            cleanWs()  // Clean the workspace after build
        }
        success {
            echo '✅ Build and deployment succeeded!'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
    }
}
