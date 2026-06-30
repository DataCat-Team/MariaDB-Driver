pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare Maven Wrapper') {
            agent { label 'Linux-Build' }
            steps {
                sh 'chmod +x mvnw || true'
                sh './mvnw -v'
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh './mvnw clean package'
                    } else {
                        bat 'mvnw clean package'
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