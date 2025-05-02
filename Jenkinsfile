pipeline {
    agent any

    triggers {
        githubPush()
    }

    parameters {
        choice(name: 'BRANCH_NAME', choices: ['master', 'dev', 'main'], description: 'Select the Git branch to build.')
        choice(name: 'ENVIRONMENT', choices: ['qa', 'pp', 'uat', 'prod'], description: 'Select the deployment environment.')
    }

    environment {
        JAVA_HOME = 'C:\Program Files\Java\jdk-17'  // Adjust to your Java version
        MAVEN_HOME = 'E:\Sathya Nanjappan\Softwares\apache-maven-3.9.9-bin\apache-maven-3.9.9'                   // Adjust to your Maven installation path
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH_NAME}", url: 'https://github.com/sathyananjappan1991/Springboot.git'
            }
        }

        stage('Build') {
            steps {
                sh "${env.MAVEN_HOME}/bin/mvn clean package -DskipTests"
            }
        }

        stage('Unit Tests') {
            steps {
                sh "${env.MAVEN_HOME}/bin/mvn test"
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
            // e.g., clean workspace, notify teams, archive artifacts
        }
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
