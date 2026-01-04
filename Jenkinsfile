pipeline {
    agent any
    tools{
        maven 'maven_3_9_12'
    }
    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/annu1612/SpringBootApplication']])
                bat 'mvn clean install'
                }
        }
       /* stage('Build Docker Image'){
            steps{
               script{
                   bat 'docker build -t spring-boot-docker .'
               }
            }
        }*/
    }
}