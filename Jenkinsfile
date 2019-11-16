pipeline {

    agent any

    stages {

        stage ('Build') {
            steps {
                withMaven(maven: 'maven_3_6_2') {
                    // Run in non-interactive (batch) mode
                	sh 'mvn -B -DskipTests clean package'
                }
            }
        }

        stage ('Test') {
            steps {
                withMaven(maven: 'maven_3_6_2') {
                    sh 'mvn test'
                }
            }
        }

    }

}