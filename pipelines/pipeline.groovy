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
			bat 'C:/Users/trvxn/Downloads/apache-jmeter-5.4.1/apache-jmeter-5.4.1/bin/jmeter.bat -n -t Demo-Google.jmx -l Demo-Google.jtl'
        }
      }

       stage('CheckLog') {
      steps {
        if (manager.logContains('Avg : 400')) {
          error("Build failed because of this and that..")  
	}
    }
    }
	}
