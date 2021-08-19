pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
               //bat "rmdir  /s /q TicketBookingServiceJunitTesting"
                bat "git clone https://github.com/TestUser463/TestAutomation.git"
                bat "mvn clean -f TestAutomation"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f TestAutomation"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f TestAutomation"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f TestAutomation"
            }
        }
    }
}
