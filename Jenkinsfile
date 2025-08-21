pipeline {
    agent any
    stages {
        stage('Prepare'){
            steps {
                git credentialsId : 'GithubOdorok',
                    branch : 'test',
                    url : 'https://github.com/Team-Odorok/Odorok_backend.git'
            }
        }
        stage('test') {
            steps {
                echo 'test stage'
            }
        }
        stage('build') {
            steps {
                echo 'build stage'
            }
        }
        stage('docker build') {
            steps {
                echo 'docker build stage'
            }
        }
    }
}