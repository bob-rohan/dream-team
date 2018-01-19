pipeline {
  agent {
    node {
      label 'master'
    }
    
  }
  stages {
    stage('Build dreamteam-web') {
      steps {
        bat 'dreamteam-web/maven-build.bat'
      }
    }
    stage('Build docker image') {
      steps {
        bat 'dreamteam-web\\docker-build.bat'
      }
    }
  }
}