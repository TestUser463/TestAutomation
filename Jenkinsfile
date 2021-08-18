node(){

		def repoURL='https://github.com/TestUser463/TestAutomation.git'

		stage("Prepare Workspace"){
			cleanWs()
			env.WORKSPACE_LOCAL=sh(returnStdout:true,script:'pwd').trim()
			echo"Workspace set to:"+env.WORKSPACE_LOCAL
		}
		stage('Checkout Self'){
		git branch:'XrayVideo',credentialsId:'TestUser463',url:repoURL
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
	
	stage('Import results to Xray') {
            steps {
                step([$class: 'XrayImportBuilder', endpointName: '/cucumber', importFilePath: 'target/cucumber-json/cucumber.json', serverInstance: 'db49b802-23cf-4fea-bdbb-c1ab1f612b6f'])
            }
        }
}
