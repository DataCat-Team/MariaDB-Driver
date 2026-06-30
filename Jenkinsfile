pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK 21'
        maven 'Maven 3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean package'
                    } else {
                        bat 'mvn clean package'
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar, target/*.yarn', fingerprint: true, allowEmptyArchive: true
        }

        success {
            echo 'Build completed successfully.'
        }

        failure {
            echo 'Build failed.'
        }
    }
}