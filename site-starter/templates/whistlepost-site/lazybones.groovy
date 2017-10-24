def props = [:]
props.organization = ask("Define value for 'organization' [My Org]: ", "My Org", "organization")
def defaultGroup = "org.${props.organization.toLowerCase().split().join('')}"
props.group = ask("Define value for 'group' [$defaultGroup]: ", defaultGroup, "group")
props.version = ask("Define value for 'version' [0.1]: ", "0.1", "version")
props.projectName = ask("Define value for 'projectName' [My Site]: ", "My Site", "projectName")
def defaultProjectUrl = "http://${props.projectName.toLowerCase().split().join('.')}"
props.projectUrl = ask("Define value for 'projectUrl' [$defaultProjectUrl]: ", defaultProjectUrl, "projectUrl")
def defaultProjectId = props.projectName.toLowerCase().split().join('-')
props.projectId = ask("Define value for 'projectId' [$defaultProjectId]: ", defaultProjectId, "projectId")

processTemplates "README.md", props
processTemplates "settings.gradle", props
processTemplates "**/build.gradle", props

new File(projectDir, 'site-app').renameTo(new File(projectDir, "$props.projectId-app"))
new File(projectDir, 'site-content').renameTo(new File(projectDir, "$props.projectId-content"))
new File(projectDir, 'site-config').renameTo(new File(projectDir, "$props.projectId-config"))
