pipeline {
    agent any

    tools {
        jdk 'jdk21'        // Name from Global Tool Configuration
        maven 'Maven3'     // Name from Global Tool Configuration
    }

    environment {
        REPORT_DIR       = "reports"                        // folder containing reports
        TESTNG_HTML      = "${env.REPORT_DIR}/ExtentReport.html"
        CUCUMBER_HTML    = "${env.REPORT_DIR}/extent-report.html"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Rakshitha-Hugar-A/saucedemo-capstoneProject-selenium.git',
                    credentialsId: 'github-pat'  // optional if repo is private
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('Publish Test Results') {
            steps {
                // Publish XML test results (TestNG/Surefire)
                junit allowEmptyResults: true, testResults: '**/surefire-reports/*.xml'
            }
        }

        stage('Publish TestNG Extent Report') {
            steps {
                publishHTML([
                    reportDir: "${env.REPORT_DIR}",
                    reportFiles: 'ExtentReport.html',
                    reportName: 'TestNG Extent Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }

        stage('Publish Cucumber Extent Report') {
            steps {
                publishHTML([
                    reportDir: "${env.REPORT_DIR}",
                    reportFiles: 'extent-report.html',
                    reportName: 'Cucumber Extent Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            // Archive reports and other artifacts
            archiveArtifacts artifacts: '**/reports/**, **/target/ExtentReport.html', allowEmptyArchive: true
        }
        success {
            echo "Build succeeded"
        }
        failure {
            echo "Build failed — check console and reports"
        }
    }
}
