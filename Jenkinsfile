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
  }
}