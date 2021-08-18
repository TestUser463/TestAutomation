node(){

		def repoURL='https://github.com/TestUser463/TestAutomation.git'

		stage("Prepare Workspace"){
			cleanWs()
			env.WORKSPACE_LOCAL=sh(returnStdout:true,script:'pwd').trim()
			echo"Workspace set to:"+env.WORKSPACE_LOCAL
		}
		stage('Checkout Self'){
		git branch:'master',credentialsId:'',url:repoURL
		}
		stage('Cucumber Tests'){
			withMaven(maven:'maven35'){
				sh """
					cd ${env.WORKSPACE_LOCAL}
					mvn clean test
				"""
			}
		}
		stage('Expose report'){
			archive "**/cucumber.json"
			cucumber '**/cucumber.json'
		}
		
		stage('Import results to Xray'){
		
		def description ="[BUILD_URL|${env.BUILD_URL}]"
		def labels='["regression"]'
		def environment="Test"
		def testExecutionFieldId=10603
		def projectKey="Test Automation"
		def xrayConnectorId='db49b802-23cf-4fea-bdbb-c1ab1f612b6f'
		def info='''{
				"fields":{
					"project":{
					"key":"''' +projectKey+'''"
					},
					"labels":'''+labels+''',
					"description":"'''+description+'''",
					"summary":"Automated Regression Execution @'''+env.BUILD_TIME+' '+environment+'''",
					"issuetype":{
					"id":"'''+ testExecutionFieldId+ '''"
					},
					["''' +environment+'''"
					]
					}
					}'''
				echo info
				
			step([$class: 'XrayImportBuilder', endpointName: '/cucumber/multipart', importFilePath: 'target/cucumber-json/cucumber.json', importInParallel: 'false', importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance:xrayConnectorId])
		}
		
}
