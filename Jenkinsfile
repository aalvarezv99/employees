pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "spring_boot_app"
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
                    def customImage = docker.build("${env.DOCKER_IMAGE}", "${env.DOCKERFILE_PATH}")
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def postgresContainer = "postgres-db"
                    def appContainer = "spring-boot-app"
                    bat "docker rm -f ${postgresContainer} || true"
                    bat "docker rm -f ${appContainer} || true"
                    bat 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
