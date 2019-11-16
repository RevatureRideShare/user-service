pipeline {

    agent any

    stages {

        stage ('Build') {
            steps {
                withMaven(maven: 'maven_3_6_2') {
                	sh 'mvn clean package'
                }
            }
        }
    }
    
}