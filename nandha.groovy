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
							branches: [[name: '/main']],
							doGenerateSubmoduleConfigurations: false,
							extensions: [],
							submoduleCfg: [],
							userRemoteConfigs: [[
							credentialsId: 'none',
							url: 'https://github.com/nandha060101/dinesh.git']]])
echo "nandha.groovy"

                        }
                       
                    
                    }
            }
                   
