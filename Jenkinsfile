pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "spring-boot-app"
        DOCKER_REGISTRY = "${env.DOCKER_REGISTRY}"
        DOCKER_CREDENTIALS_ID = "${env.DOCKER_TOKEN}"
        DOCKER_PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/aalvarezv99/employees'
            }
        }

        stage('Build') {
            steps {
                bat 'gradle build --no-daemon'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat "\"${DOCKER_PATH}\" build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Tests') {
            steps {
                bat 'gradle test'
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    bat "\"${DOCKER_PATH}\" login -u ${env.DOCKER_USER} -p ${env.DOCKER_PASSWORD}"
                    bat "\"${DOCKER_PATH}\" tag ${DOCKER_IMAGE} ${DOCKER_REGISTRY}/${DOCKER_IMAGE}"
                    bat "\"${DOCKER_PATH}\" push ${DOCKER_REGISTRY}/${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
