import hudson.model.*
import jenkins.model.*
import org.csanchez.jenkins.plugins.kubernetes.*


String Secret = System.getenv("JENKINS_SECRET")
println "Secret: ${Secret}"
String AgentName = System.getenv("JENKINS_AGENT_NAME")
String namespace = System.getenv("NAMESPACE")
String agentImage = System.getenv("JENKINS_AGENT_IMAGE")
String agentCPU = System.getenv("JENKINS_AGENT_REQUEST_CPU")
String agentMemory = System.getenv("JENKINS_AGENT_REQUEST_MEMORY",)
String agentCPILimit = System.getenv("JENKINS_AGENT_LIMIT_CPU")
String agentMemoryLimit = System.getenv("JENKINS_AGENT_LIMIT_MEMORY")
String k8sAPIServer = "https://kubernetes.default"
String jenkinsURL = "http://172.17.0.2:8080"

//String jenkinsName = System.setenv("JENKINS_NAME", "")
Jenkins instance = Jenkins.getInstance();

/*if (Jenkins.instance.clouds) {
    existingCloud = Jenkins.instance.clouds.get(0)
    println "Cloud config found: ${existingCloud}"
} else {   */
    k = new KubernetesCloud("kubernetes")
    instance.clouds.add(k)
//}

k.setServerUrl(k8sAPIServer)
k.setJenkinsUrl(jenkinsURL)
k.setSkipTlsVerify(false)
k.setNamespace(namespace)
k.setWebSocket(true)
//k.setPodRetention(new Always())
//k.setDirectConnection(true)
k.setRetentionTimeout(5)
k.setReadTimeout(15)
k.setConnectTimeout(5)
k.setMaxRequestsPerHostStr("32")
k.setContainerCapStr("10")

def p = new PodTemplate()
println "up to here"
p.setName('centos6')
p.setLabel('centos6-agent')
p.setRemoteFs('/home/jenkins')
p.setYaml("""
apiVersion: v1
kind: Pod
spec:
  restartPolicy: Never
  nodeSelector:
    cloud.google.com/gke-nodepool: ${namespace}
            """)
p.setCommand("")
p.setInstanceCapStr('10')
p.setIdleMinutesStr('30')
p.setNodeUsageMode('NORMAL')
//p.setWorkspaceVolume(new EmptyDirWorkspaceVolume(false))

k.addTemplate(p)

println "k8s cloud config added: ${k}"

instance.save();
