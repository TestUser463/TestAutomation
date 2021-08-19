pipeline{
agent any
stages 
{
stage('Build') 
{
steps{
echo "Building the Code.........."
bat "mvn clean"
}
}
stage('Test') 
{
steps{
echo "Testing the Code.........."
bat "mvn test"
}
}
stage('Compile') 
{
steps{
echo "Compiling the Project.........."
bat "mvn compile"
}
}
stage('Deploy') 
{
steps{
echo "Deploying the Project.........."
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
}
