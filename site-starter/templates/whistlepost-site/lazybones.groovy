def props = [:]
props.organization = ask("Define value for 'organization' [My Org]: ", "My Org", "organization")
def defaultGroup = "org.${props.organization.toLowerCase().split().join('')}"
props.group = ask("Define value for 'group' [$defaultGroup]: ", defaultGroup, "group")
props.version = ask("Define value for 'version' [0.1]: ", "0.1", "version")
props.projectName = ask("Define value for 'projectName' [My Site]: ", "My Site", "projectName")
def defaultProjectUrl = "http://${props.projectName.toLowerCase().split().join('.')}"
props.projectUrl = ask("Define value for 'projectUrl' [$defaultProjectUrl]: ", defaultProjectUrl, "projectUrl")
def defaultProjectId = props.projectName.toLowerCase().split().join('')
props.projectId = ask("Define value for 'projectId' [$defaultProjectId]: ", defaultProjectId, "projectId")

// mongodb configuration
props.mongo_connectionString = ask('MongoDB Connection String? [mongo:27017]: ', 'mongo:27017', 'mongo_connectionString')
props.mongo_database = ask('MongoDB Database? [sling]: ', 'sling', 'mongo_database')
props.mongo_collection = ask('MongoDB Collection? [resources]: ', 'resources', 'mongo_collection')
props.mongo_providerRoots = ask('MongoDB Provider Roots? [articles]: ', 'articles', 'mongo_providerRoots')

processTemplates "README.md", props
processTemplates "settings.gradle", props
processTemplates "**/build.gradle", props
processTemplates "**/gradle.properties", props
processTemplates "site-app/package.json", props
processTemplates "site-app/webpack.config.js", props
processTemplates "docker-compose.yml", props

new File(projectDir, 'site-app/src/main/resources/SLING-INF/content/js/site.js').renameTo(new File(projectDir, "site-app/src/main/resources/SLING-INF/content/js/${props.projectId}.js"))
new File(projectDir, 'site-app').renameTo(new File(projectDir, "$props.projectId-app"))
new File(projectDir, 'site-content').renameTo(new File(projectDir, "$props.projectId-content"))
new File(projectDir, 'site-config/src/main/resources/SLING-INF/content/apps/site').renameTo(new File(projectDir, "site-config/src/main/resources/SLING-INF/content/apps/${props.projectId}"))
new File(projectDir, 'site-config').renameTo(new File(projectDir, "$props.projectId-config"))
