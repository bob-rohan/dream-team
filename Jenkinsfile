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
        bat 'build-docker-image.bat'
      }
    }
  }
}