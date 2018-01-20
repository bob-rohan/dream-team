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
    stage('Docker build') {
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
    stage('Docker clean') {
      agent {
        node {
          label 'Raspberry Pi'
        }
        
      }
      steps {
        sh '''docker stop $(docker ps -a | grep dreamteam-web | awk \'{print $1}\')
docker rm $(docker ps -a | grep dreamteam-web | awk \'{print $1}\')'''
      }
    }
    stage('Docker run') {
      agent {
        node {
          label 'Raspberry Pi'
        }
        
      }
      steps {
        sh 'docker run -d -p 8089:8089 --name=dreamteam-web bobrohan/dreamteam-web'
      }
    }
  }
}