pipeline{
    agent any
    tools{
        maven '3.8.1'
    }

    stages{
        stage('Source') {
            steps{
                git branch: 'main', url: 'https://github.com/bayembacke221/unit-test.git'
            }
        }
         stages {
                stage('Clean') {
                    steps {
                        withMaven(maven: 'Default',jdk: 'Java 18') {
                            sh "echo JAVA_HOME=$JAVA_HOME"
                            sh "mvn clean"
                        }
                    }
                }
            }
         stage('Clean package') {
                    steps{
                        sh 'mvn clean package'
                    }
                }

        stage ('Build') {
            steps{
                sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
            }
        }

        stage ('SonarQube Analysis') {
            steps{
                sh 'mvn sonar:sonar'
            }
        }

    } // stages

    post {
        aborted {
            echo "Sending message to Agent"
        } // aborted

        failure {
            echo "Sending message to Agent"
        } // failure

        success {
            echo "Sending message to Agent developer"
        } // success
    } // post

}