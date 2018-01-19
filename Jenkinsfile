pipeline {
  agent {
    node {
      label 'master'
    }
    
  }
  stages {
    stage('Build dreamteam-web') {
      steps {
        build 'dreamteam-web'
      }
    }
    stage('Build docker image') {
      steps {
        sh '''cd dreamteam-web;
docker build -t bobrohan/dreamteam-web .'''
      }
    }
  }
}