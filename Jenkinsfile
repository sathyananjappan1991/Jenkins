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
        JAVA_HOME = 'C:/Program Files/Java/jdk-17'  // Adjust this path to match your actual JDK
        PATH = "${JAVA_HOME}/bin;${PATH}"  // Ensure Java is in the PATH
         MAVEN_HOME = 'E:/Tools/apache-maven-3.2.5'                   // Adjust to your Maven installation path
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
            echo 'Post-build cleanup or notifications...'
        }
        success {
            echo '✅ Build and deployment succeeded!'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
    }
}
