pipeline {
    agent any
    tools{
        maven 'maven_3_5_0'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Jekkan322/devops-automation']])
                bat 'mvn clean install'
            }
        }
         stage('Java tests'){
            steps{
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    bat 'docker build -t jekkan322/devops-integration .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerpwd')]) {
                        bat 'docker login -u jekkan322 -p %dockerpwd%'
                    }
                    bat 'docker push jekkan322/devops-integration'
                }
            }
        }
        stage('Deploy to k8s'){
            steps{
                script{
                    kubernetesDeploy (configs: 'deploymentservice.yaml', kubeconfigId: 'k8spwdconfig')
                }
            }
        }
    }
}
