pipeline{
    agent any
    tools{
        maven '3.8.6'
    }
    stages{
        stage('Source') {
            steps{
                git branch: 'main', url: 'https://github.com/bayembacke221/unit-test.git'
            }
        }
         stage('Test') {
                    steps{
                        bat 'mvn test'
                    }
                }
        stage ('Build') {
            steps{
                bat 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
            }
        }
        stage ('SonarQube Analysis') {
            steps{
                bat 'mvn sonar:sonar'
            }
        }

    } // stages

    post {
        aborted {
            echo "Sending message to Agent"
        } // aborted

        failure {
            echo "Sending message to Agent developer"
        } // failure

        success {
            echo "Sending message to Agent developer"
        } // success
    } // post

}
