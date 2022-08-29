import hudson.model.*
import jenkins.model.*
import org.csanchez.jenkins.plugins.kubernetes.*


String jenkinsURL = System.setenv("JENKINS_URL", "http://http://192.168.49.2:32000")
String Secret = System.setenv("JENKINS_SECRET", "3aacc2bb1a5f408882979aced5a14c22")
String AgentName = System.setenv("JENKINS_AGENT_NAME", "jenkinsAgent")
String namespace = System.setenv("NAMESPACE", "jenkins")
String agentImage = System.setenv("JENKINS_AGENT_IMAGE", "jenkins/agent:latest")
String agentCPU = System.setenv("JENKINS_AGENT_REQUEST_CPU", "0.2")
String agentMemory = System.setenv("JENKINS_AGENT_REQUEST_MEMORY", "2Mi")
String agentCPILimit = System.setenv("JENKINS_AGENT_LIMIT_CPU", "0.2")
String agentMemoryLimit = System.setenv("JENKINS_AGENT_LIMIT_MEMORY", "2Mi")



//String jenkinsName = System.setenv("JENKINS_NAME", "")

if (Jenkins.instance.clouds) {
    jenkinsK8sAgent = Jenkins.instance.clouds.get(0)
    echo "Cloud config found: ${jenkinsK8sAgent}"
} else {
    jenkinsK8sAgent = new KubernetesCloud("kubernetes")
    Jenkins.instance.clouds.add("jenkinsK8sAgent")
    echo "k8s cloud config added: ${jenkinsK8sAgent}"
}