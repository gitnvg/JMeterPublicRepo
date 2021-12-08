#!/usr/bin/env groovy
    node {

      stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/gitnvg/JMeterPublicRepo.git",
                                credentialsId: "gitnvg"
                        ]
                ],
                branches: [[name: "main"]]
        ], poll: false
      }

      stage('Complete any setup steps') {
		echo "Complete set-up steps"
      }

      stage('Execute Performance Tests') {
        dir("${WORKSPACE}/scripts") {
			bat 'C:/apache-jmeter-3.2/apache-jmeter-3.2/bin/jmeter.bat -n -t Demo-Google.jmx -l Demo-Google.jtl'
        }
      }

      stage('Analyse Results') {
		echo "Analyse results"
      }
    }
