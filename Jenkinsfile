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
        stash(name: 'dreamteam-web', includes: 'dreamteam\\dreamteam-web\\target\\*.jar')
      }
    }
    stage('Build docker image') {
      agent {
        node {
          label 'Raspberry Pi'
        }
        
      }
      options {
        skipDefaultCheckout(true)
      }
      steps {
        sh 'pwd'
        unstash 'dreamteam-web'
      }
    }
  }
}