pipeline {
    agent any

    tools {
        jdk 'jdk21'        // From Global Tool Configuration
        maven 'Maven3'     // From Global Tool Configuration
    }

    environment {
        REPORT_DIR       = "reports"  // Folder for both TestNG and Cucumber reports
        TESTNG_HTML      = "${env.REPORT_DIR}/ExtentReport.html"
        CUCUMBER_HTML    = "${env.REPORT_DIR}/extent-report.html"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Rakshitha-Hugar-A/saucedemo-capstoneProject-selenium.git'
            }
        }

        stage('Clean Reports') {
            steps {
                echo "Cleaning old reports..."
                bat "rmdir /s /q ${env.REPORT_DIR} || echo No old reports"
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn -B test'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit allowEmptyResults: true, testResults: '**/surefire-reports/*.xml'
            }
        }

        stage('Publish TestNG Extent Report') {
            steps {
                publishHTML([
                    allowMissing: true,                 // <--- Added
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
                    allowMissing: true,                 // <--- Added
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
            echo "Archiving reports and screenshots..."
            archiveArtifacts artifacts: '**/reports/**', allowEmptyArchive: true
        }
        success {
            echo "Build succeeded"
        }
        failure {
            echo "Build failed — check console and reports"
        }
    }
}
