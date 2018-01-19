pipeline {
  agent {
    node {
      label 'Windows'
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