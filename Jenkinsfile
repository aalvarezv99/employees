pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "spring-boot-app"
        DOCKER_REGISTRY = env.DOCKER_REGISTRY
        DOCKER_CREDENTIALS_ID = env.DOCKER_TOKEN
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/aalvarezv99/employees'
            }
        }

        stage('Build') {
            steps {
                sh 'gradle build --no-daemon'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.withRegistry(DOCKER_REGISTRY, DOCKER_CREDENTIALS_ID) {
                        docker.image(DOCKER_IMAGE).build()
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                sh 'gradle test'
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry(DOCKER_REGISTRY, DOCKER_CREDENTIALS_ID) {
                        docker.image(DOCKER_IMAGE).push('latest')
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}