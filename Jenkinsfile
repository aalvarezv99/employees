pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "springbootapp"
        DOCKER_REGISTRY = "${env.DOCKER_REGISTRY}"
        DOCKER_CREDENTIALS_ID = "${env.DOCKER_TOKEN}"
        DOCKERFILE_PATH = "."
    }

    tools {
        jdk 'Java-17'
        gradle 'Gradle-7.5'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/aalvarezv99/employees'
            }
        }

        stage('Build') {
            steps {
                script {
                    def gradleHome = tool name: 'Gradle-7.5', type: 'gradle'
                    bat "${gradleHome}/bin/gradle build --no-daemon"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${env.DOCKERFILE_PATH}")
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
