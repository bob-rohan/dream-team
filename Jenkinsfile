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
        stash(name: 'dreamteam-web', includes: 'dreamteam-web\\target\\*.jar')
        stash(name: 'dreamteam-web-dockerfile', includes: 'dreamteam-web\\Dockerfile')
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
        unstash 'dreamteam-web-dockerfile'
        sh '''cd dreamteam-web
docker build -t bobrohan/dreamteam-web:latest .'''
      }
    }
  }
}