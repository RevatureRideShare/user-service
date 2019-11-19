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
        
        stage ('Deploy') {
            steps {
                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF_LOGIN',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    sh '/usr/local/bin/cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD \
                    -o Revature Training -s development'
                    sh '/usr/local/bin/cf push'
                }
            }
        }

    }
    
}