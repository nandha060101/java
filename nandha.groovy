properties([
    parameters([
        choice(
            choices: ['demo'],
            description: 'Select branch to build',
            name: 'branch')])])
                        node{
                                    if ("${params.branch}" == 'demo')
                    {
                       stage("Code checkout from SCM")
                        {
							checkout([
							$class: 'GitSCM',
						       branches: [[name: '*/main']],
							doGenerateSubmoduleConfigurations: false,
							extensions: [],
							submoduleCfg: [],
							userRemoteConfigs: [[
							credentialsId: 'none',
						       url: 'https://github.com/nandha060101/check.git']]])
						      
							
                        }
			    stage("Build")
                        {
                            sh'''rm -rf /var/lib/jenkins/workspace/UAT-backendjobs/sample-uat/dist/filecomparisonutility/*
                                 cd /var/lib/jenkins/workspace/UAT-backendjobs/sample-uat
                                 npm i --legacy-peer-deps
                                 NG_PERSISTENT_BUILD_CACHE=1 ng build filecomparisonutility'''
                        }
                       
                    
                    }
            }
                   
