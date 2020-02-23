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

// media library configuration
props.media_location = ask('Media Library Location? [/opt/sling/media]: ', '/opt/sling/media', 'media_location')
props.media_providerRoot = ask('Media Library Provider Root? [media/library]: ', 'media/library', 'media_providerRoot')

props.whistlepostVersion = project.rootProject.version

processTemplates "README.md", props
processTemplates "settings.gradle", props
processTemplates "build.gradle", props
processTemplates "gradle.properties", props
processTemplates "package.json", props
processTemplates "webpack.config.js", props
processTemplates "docker-compose.yml", props

new File(projectDir, 'src/app/js/site.js').renameTo(new File(projectDir, "src/app/js/${props.projectId}.js"))
new File(projectDir, 'src/config/apps/site').renameTo(new File(projectDir, "src/config/apps/${props.projectId}"))
new File(projectDir, 'src/config/etc/map/http/site.80.json').renameTo(new File(projectDir, "src/config/etc/map/http/${props.projectDomain}.80.json"))
new File(projectDir, 'src/config/etc/map/http/any_site.80.json').renameTo(new File(projectDir, "src/config/etc/map/http/any_${props.projectDomain}.80.json"))
