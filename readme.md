FYPJ Secure DevOps - Spring Petclinic
Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/).
## Section 1: Installation Documentation
<details>
<summary>Git Installation on Ubuntu 20.04</summary>
<br>
Reference: <a href="https://linuxconfig.org/how-to-install-git-on-ubuntu-20-04-lts-focal-fossa-linux">https://linuxconfig.org/how-to-install-git-on-ubuntu-20-04-lts-focal-fossa-linux </a>
<br><br>
To install Git
<pre>
sudo apt update
sudo apt install git
</pre>
Check the Installation
<pre>
git --version
</pre>
Set up your global username and email
<pre>
git config --global user.name "Ben"
git config --global user.email "ben@securedevops.com"
</pre>
</details>

<details>
<summary>Maven Installation on Ubuntu 20.04</summary>
<br>
Reference: <a href="https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-20-04/">https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-20-04/ </a>
<br><br>
To install Maven
<pre>
sudo apt update 
sudo apt install maven 
</pre>
Check the Installation
<pre>
mvn --version
</pre>
</details>

<details>
<summary>Jenkins Installation on Ubuntu 20.04</summary>
<br>
Reference: <a href="https://www.digitalocean.com/community/tutorials/how-to-install-jenkins-on-ubuntu-20-04">https://www.digitalocean.com/community/tutorials/how-to-install-jenkins-on-ubuntu-20-04 </a>
<h2>Pre-requisites:</h2>
Oracle JDK 11 installed
<br>
Reference: <a href="https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-20-04#installing-specific-versions-of-openjdk">https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-20-04#installing-specific-versions-of-openjdk </a>
<br><br>
To install JDK 11 
<pre>
sudo apt update 
sudo apt-get install openjdk-11-jre -y 
sudo apt-get install openjdk-11-jdk -y 
</pre>
Set the JAVA_HOME environment variable.
<br>
Firstly, determine where Java is installed using the following command
<pre>
sudo update-alternatives --config java
</pre>
OpenJDK 11 should be located at '/usr/lib/jvm/java-11-openjdk-amd64/bin/java'
<br>
Copy the path, then open the configuration file, "/etc/environment" using any text editor
<pre>
sudo nano /etc/environment
</pre>
At the end of the file, add the following line (NOTE: DO NOT INCLUDE the bin/ portion)
<pre>
JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64"
</pre>
<h2>Installing Jenkins</h2>
Use the following commands
<pre>
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add - 
sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt update
sudo apt install jenkins 
</pre>
<h2>Starting Jenkins</h2>
Start Jenkins by using systemctl and verify that Jenkins successfully started by checking the status
<pre>
sudo systemctl start jenkins
sudo systemctl status jenkins
</pre>
<h2>Opening the Firewall</h2>
By default, Jenkins run on port 8080. Open that port using ufw
<pre>
sudo ufw allow 8080
</pre>
NOTE: If the firewall is inactive, the following commands will allow OpenSSH and enable the firewall.
<pre>
sudo ufw allow OpenSSH
sudo ufw enable
</pre>
Check ufw's status to confirm the new rules
<pre>
sudo ufw status
</pre>
<h2>Setting Up Jenkins</h2>
<ol>
    <li>To set up your installation, visit Jenkins on its default port, 8080, using you server domain name or IP address: http://your_server_ip_or_domain:8080</li>
    <li>You should receive the Unlock Jenkins screen, which displays the location of the initial password.</li>
    <li>In the terminal window, use the cat command to display the password:
    <pre>
    sudo cat /var/lib/jenkins/secrets/initialAdminPassword
    </pre></li>
    <li>Copy the 32-character alphanumeric password from the terminal and paste it into the Administrator password field, then click Continue.</li>
    <li>The next screen presents the option of installing suggested plugins (recommended) or selecting specific plugins.</li>
    <li>When the installation is completed, you will be prompted to set up the first administrative user.</li>
    <li>When the first administrative user is set up, you will receive an Instance Configuration page that will ask you to confirm the preferred URL for your Jenkins instance. Confirm either the domain name for your server or your server's IP address.</li>
    <li>After confirming the appropriate information, click Save and Finish. You will receiving a confirmation page confirming that "Jenkins is Ready!"</li>
</ol>
</details>

<details>
<summary>SonarQube Installation on Ubuntu 20.04</summary>
<br>
Reference: <a href="https://www.fosstechnix.com/how-to-install-sonarqube-on-ubuntu-20-04/">https://www.fosstechnix.com/how-to-install-sonarqube-on-ubuntu-20-04/ </a>
<h2>Prerequisites</h2>
PostgreSQL Version 9.3 or higher, SSH access with sudo privileges, and Firewall Port 9000
<br>
To install SonarQube, you need to increase the vm.max_map_count kernel, file descriptor, and ulimit for current session at runtime. Use the following.
<pre>
sudo sysctl -w vm.max_map_count=262144 
sudo sysctl -w fs.file-max=65536 
ulimit -n 65536 
ulimit -u 4096
</pre>
To increase the vm.max_map_count kernel, file descriptor, and ulimit permanently. 
<br>
Open the following configuration file
<pre>
sudo nano /etc/security/limits.conf
</pre>
Add the following lines into the configuration file
<pre>
sonarqube   -   nofile  65536
sonarqube   -   noproc  4096
</pre>
Before starting the installation process, update and upgrade system packages
<pre>
sudo apt-get update
sudo apt-get upgrade
</pre>
Install wget and unzip package
<pre>
sudo apt-get install wget unzip -y
</pre>
<h2>Install OpenJDK 11</h2>
NOTE: Same as the installation of OpenJDK 11 in Jenkins
<br>
Install OpenJDK 11 and JRE 11 using the following commands:
<pre>
sudo apt-get install openjdk-11-jdk -y
sudo apt-get install openjdk-11-jre -y
</pre>
Check Java version:
<pre>
java --version
</pre>
<h2>Install and Setup PostgreSQL 10 Database for SonarQube</h2>
Add and download the PostgreSQL repository
<pre>
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ `lsb_release -cs`-pgdg main" >> /etc/apt/sources.list.d/pgdg.list'
wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O - | sudo apt-key add -
</pre>
Install the PostgreSQL database server by using the following command.
<pre>sudo apt-get -y install postgresql postgresql-contrib</pre>
Start PostgreSQL database server
<pre>sudo systemctl start postgresql</pre>
Enable it to start automatically at boot time
<pre>sudo systemctl enable postgresql</pre>
Change the password for the default PostgreSQL user
<pre>sudo passwd postgres</pre>
Switch to the postgres user
<pre>su - postgres</pre>
Create a new user by doing the following
<pre>createuser sonar</pre>
Switch to the PostgreSQL shell
<pre>psql</pre>
Set a password for the newly created user for the SonarQube database
<pre>ALTER USER sonar WITH ENCRYPTED password 'sonar';</pre>
Create a new database for PostgreSQL database by running
<pre>CREATE DATABASE sonarqube OWNER sonar;</pre>
Gant all privileges to sonar user on the sonarqube database
<pre>grant all privileges on DATABASE sonarqube to sonar;</pre>
Exit from the psql shell
<pre>\q</pre>
Switch back to the sudo user by running the exit command
<pre>exit</pre>
<h2>Install SonarQube on Ubuntu 20.04</h2>
NOTE: The version demonstrated here is for Sonarqube version 7.9.3.
<br>
Download the sonarqube installer files using the following 2 commands:
<pre>
cd /tmp
sudo wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-7.9.3.zip
</pre>
Unzip the setup to the /opt directory
<pre>
sudo unzip sonarqube-7.9.3.zip -d /opt
</pre>
Move extracted setup to /opt/sonarqube directory
<pre>
sudo mv /opt/sonarqube-7.9.3 /opt/sonarqube
</pre>
<h2>Configure SonarQube</h2>
Create a group called sonar
<pre>
sudo groupadd sonar
</pre>
Now add the user with directory access
<pre>
sudo useradd -c "user to run SonarQube" -d /opt/sonarqube -g sonar sonar 
sudo chown sonar:sonar /opt/sonarqube -R
</pre>
Open the SonarQube configuration file using your favourite text editor
<pre>
sudo nano /opt/sonarqube/conf/sonar.properties
</pre>
Find the following lines
<pre>
#sonar.jdbc.username=
#sonar.jdbc.password=
</pre>
Uncomment and type the PostgreSQL database username and password which was created above
<pre>
sonar.jdbc.username=sonar
sonar.jdbc.password=sonar
sonar.jdbc.url=jdbc:postgresql://localhost:5432/sonarqube
</pre>
Open and edit the sonar script file
<pre>
sudo nano /opt/sonarqube/bin/linux-x86-64/sonar.sh
</pre>
Set RUN_AS_USER in the sonar script file
<pre>
RUN_AS_USER=sonar
</pre>
Switch back to the sonar user to start SonarQube
<pre>
sudo su sonar
</pre>
Move to the script directory
<pre>
cd /opt/sonarqube/bin/linux-x86-64/
</pre>
Run the script to start SonarQube
<pre>
./sonar.sh start
</pre>
Check if SonarQube is running with the command below
<pre>
./sonar.sh status
</pre>
To check SonarQube logs, navigate to /opt/sonarqube/logs/sonar.logs
<pre>
tail /opt/sonarqube/logs/sonar.log
</pre>
<h2>Configure the Systemd service</h2>
Firstly, stop the SonarQube service
<pre>
cd /opt/sonarqube/bin/linux-x86-64/
./sonar.sh stop
</pre>
Create a systemd service file for SonarQube.
<pre>sudo nano /etc/systemd/system/sonar.service</pre>
In the systemd service file, add the below lines:
<pre><code>
[Unit] 
Description=SonarQube service 
After=syslog.target network.target 
<br>
[Service] 
Type=forking 
<br>
ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop 
<br>
User=sonar 
Group=sonar 
Restart=always 
<br>
LimitNOFILE=65536 
LimitNPROC=4096 
<br>
[Install] 
WantedBy=multi-user.target
</code></pre>
Start the SonarQube daemon
<pre>sudo systemctl start sonar</pre>
Enable the SonarQube service
<pre>sudo systemctl enable sonar</pre>
Check if the SonarQube is running
<pre>sudo systemctl status sonar</pre>
<h2>Access SonarQube</h2>
Access SonarQube using the browser, type the following:
<pre>http://localhost:9000</pre>
You can change the IP Address in SonarQube properties
<pre>sudo nano /opt/sonarqube/conf/sonar.properties</pre>
<h2>Troubleshooting</h2>
Ensure virtual memory value is 262144 in order to run SonarQube
<pre>sudo sysctl -w vm.max_map_count=262144</pre>
To set the value permanently, update the vm.max_map_count value in /etc/sysctl.conf. To verify, use the following:
<pre>sudo sysctl vm.max_map_count</pre>
</details>

<details>
<summary>Docker and Docker Compose Installation on Ubuntu 20.04</summary>
<br>
<h2>Installation of Docker Compose on Ubuntu 20.04</h2>
Reference: <a href="https://docs.docker.com/compose/install/">https://docs.docker.com/compose/install/ </a>
<br><br>
Run the following commands
<pre>
sudo curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose 
sudo chmod +x /usr/local/bin/docker-compose 
docker-compose --version
</pre>
<h2>Installation of Docker on Ubuntu 20.04</h2>
Reference: <a href="https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04">https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04 </a>
<br><br>
Run the following
<pre>
sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common 
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable" 
sudo apt update 
apt-cache policy docker-ce 
sudo apt install docker-ce 
</pre>
Docker should now be installed. To check if it is running, use the following command
<pre>
sudo systemctl status docker
</pre>
</details>

<details>
<summary>OWASP ZAP Installation on Ubuntu 20.04</summary>
<br>
Reference: <a href="https://tutorialforlinux.com/2020/05/06/step-by-step-owasp-zap-ubuntu-20-04-installation-guide/">https://tutorialforlinux.com/2020/05/06/step-by-step-owasp-zap-ubuntu-20-04-installation-guide/ </a>
<h2>Downloading the OWASP ZAP Installer for Linux</h2>
Download the Linux Installer for ZAP 2.9.0 using the link below:
<br>
<a href="https://www.zaproxy.org/download/">https://www.zaproxy.org/download/ </a>
<br>
<h2>Installation of OWASP ZAP</h2>
Installing OWASP ZAP by using the following commands:
<pre>
cd ~/Downloads
ls ~/Downloads | grep ZAP
chmod +x ./ZAP*.sh
sudo ./ZAP*.sh
</pre>
Following the installation wizard. 
<br>
<ol>
    <li>Choose Next</li>
    <li>Then, agree to the license and click next</li>
    <li>Choose the installation type (Standard). Then click 'Next'</li>
    <li>Click Install to confirm the installation</li>
    <li>Exit with Finish</li>
</ol>
<h2>Create OWASP ZAP Launcher</h2>
Create the OWASP ZAP Launcher by doing the following: 
<ol>
    <li>Execute the following command: 
        <pre>
sudo cp /opt/zaproxy/'OWASP ZAP.desktop' /usr/share/applications/
        </pre>
    </li>
    <li>Launch OWASP ZAP using the following command
        <pre>
zap.sh
        </pre>
    </li>
</ol>

</details>

<details>
<summary>Anchore Installation on Ubuntu 20.04</summary>
<br>
Reference: <a href="https://docs.anchore.com/current/docs/engine/quickstart/">https://docs.anchore.com/current/docs/engine/quickstart/</a>
<br>
<h2>Prerequisites</h2>
A system running Docker v1.12 or higher, and a version of Docker Compose that supports at least v2 of the docker-compose configuration format.
<h2>Make a directory to store the "docker compose.yaml" file </h2>
<pre>
mkdir ~/aevolume
cd ~/aevolume
</pre>
<h2>Download the docker compose.yaml file and start</h2>
<pre>
curl -O https://docs.anchore.com/current/docs/engine/quickstart/docker-compose.yaml 
docker-compose up -d 
</pre>
<h2>Verify service availability</h2>
<pre>
docker-compose ps 
</pre>
<h2>Get the status of the Anchore Engine services</h2>
<pre>
docker-compose exec api anchore-cli system status
</pre>
NOTE: On your first time running Anchore Engine, it will take some time (10+ minutes, depending on network speed) for vulnerability data to get synced into the engine. You can check the status using the following commands: 
<pre>
docker-compose exec api anchore-cli system feeds list 
docker-compose exec api anchore-cli system wait 
</pre>
<h2>Optional - Begin using Anchore by using the anchore-engine service to analyze images</h2>
The following commands is an example:
<pre>
docker-compose exec api anchore-cli image add docker.io/library/debian:7 
docker-compose exec api anchore-cli image wait docker.io/library/debian:7 
docker-compose exec api anchore-cli image content docker.io/library/debian:7 
docker-compose exec api anchore-cli image vuln docker.io/library/debian:7 all 
docker-compose exec api anchore-cli evaluate check docker.io/library/debian:7 
</pre>
</details>

<details>
<summary>Ansible Installation on Ubuntu 20.04</summary>
<br>
Reference: <a href="https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-ansible-on-ubuntu-20-04">https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-ansible-on-ubuntu-20-04 </a>
<br>
<h2>Prerequisites</h2>
<ol>
    <li>One Ansible control node which is the machine you will use to connect to and control the Ansible hosts over SSH. </li>
        <ul>
            <li>The Ansible control node is the same machine as the Jenkins server</li>
            <li>Ansible control node have SSH keypair associated with the user</li>
        </ul>
<li>One or more Ansible Hosts: An Ansible host is any machine that the Ansible control node is configured to automate.</li>
    <ul>
        <li>Ansible control node's SSH public key added to the "authorized_keys" of a system user. This user can be either root or a regular user with sudo privileges.</li>
            <ul>
                <li>Reference: <a href="https://www.digitalocean.com/community/tutorials/how-to-set-up-ssh-keys-on-ubuntu-20-04">https://www.digitalocean.com/community/tutorials/how-to-set-up-ssh-keys-on-ubuntu-20-04</a></li>
                    <ul>
                        <li>Creating the Key Pair on the Ansible Control Node</li>
                        <pre>
ssh-keygen
                        </pre>
                        <li>Copy the public key to the Ansible host using ssh-copy-id</li>
                        <pre>
ssh-copy-id username@ansible_host
                        </pre>
                    </ul>
            </ul>
    </ul>
</ol>
<h2>Installing Ansible</h2>
<pre>
sudo apt update
sudo apt install ansible
</pre>
<h2>Setting Up the Inventory File</h2>
<pre>
sudo nano /etc/ansible/hosts
</pre>
<pre>
[servers]
server1 ansible_host=203.0.113.111
server2 ansible_host=203.0.113.112
<br>
[all:vars]
ansible_python_interpreter=/usr/bin/python3
</pre>
To check the inventory, run the following command:
<pre>
ansible-inventory --list -y
</pre>
Test connection
<pre>
ansible all -m ping -u root
</pre>
NOTE: The above command is assuming you are connecting to the Ansble Host as root. The output should look like the following:
<pre>
server1 | SUCCES => {
        "changed": false,
        "ping": "pong"
}
</pre>
</details>

<details>
<summary>Kubernetes Installation on Ubuntu 20.04 – Installed on the Kubernetes Master (Ansible Host)</summary>
<br>
Reference: <a href="https://linuxconfig.org/how-to-install-kubernetes-on-ubuntu-20-04-focal-fossa-linux">https://linuxconfig.org/how-to-install-kubernetes-on-ubuntu-20-04-focal-fossa-linux</a>
<br>
<h1>On Kubernetes Master (Ansible Host)</h1>
<h2>Install Docker</h2>
<pre>
sudo apt update 
sudo apt install docker.io 
sudo systemctl start docker 
sudo systemctl enable docker 
</pre>
<h2>Install Kubernetes</h2>
<pre>
sudo apt install apt-transport-https curl 
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add 
sudo apt-add-repository "deb http://apt.kubernetes.io/ kubernetes-xenial main" 
sudo apt install kubeadm kubelet kubectl kubernetes-cni 
</pre>
<h2>Disable swap memory</h2>
<pre>
sudo swapoff -a 
sudo nano /etc/fstab 
</pre>
<h2>Set hostname - Optional</h2>
For the Kubernetes Master (Ansible Host)
<pre>
sudo hostnamectl set-hostname kubernetes-master 
</pre>
<h2>Initialize Kubernetes Master server</h2>
<pre>
sudo kubeadm init 
</pre>
Run the following three commands as the root to start using the Kubernetes Cluster 
<pre>
mkdir -p $HOME/.kube 
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config 
sudo chown $(id -u):$(id -g) $HOME/.kube/config 
</pre>
</details>

## Section 2: Configuration and Integration Documentation
<details>
<summary>Further Configuration with Jenkins</summary>
<h2>Changing the port to 9999</h2>
Do the following to change the default that Jenkins run on (8080) to 9999:
<ol>
    <li>Edit the "/etc/default/jenkins" configuration file
        <pre>
sudo nano /etc/default/jenkins
        </pre>
    </li>
    <li>Change the JENKINS_ARG line to the following:
        <pre>
JENKINS_ARGS="--webroot=/var/cache/$NAME/war --httpPort=9999"
        </pre>
    </li>
</ol>
</details>

<details>
<summary>Configuring Tools with Jenkins</summary>
<h1>Maven</h1>
Do the following:
<ol>
    <li>Navigate to Jenkins > Manage Jenkins > Global Tool Configuration</li>
    <li>Scroll down to Maven and do the following:
        <ol type='a'>
            <li>For <strong>Name, </strong>enter <strong>Maven</strong></li>
            <li>For <strong>MAVEN_HOME, </strong>enter <strong>/usr/share/maven</li>
        </ol>
    </li>
</ol>
</details>

<details>
<summary>Using SonarQube in Jenkins Pipeline</summary>
<h2>SonarQube and Jenkins Configuration and Integration</h2>
<ul>
    <li>At SonarQube</li>
        <ol>
            <li>Navigate to "http://localhost:9000/account/security" in SonarQube</li>
            <li>For <strong>'Enter Token Name'</strong>, enter <strong>Jenkins-Auth-Token</strong></li>
            <li>Click <strong>Generate</strong> to generate the token for Jenkins from SonarQube</li>
            <li>Copy and save the token immediately. The token will only be shown once.</li><br>
        </ol>
    <li>At Jenkins</li>
        <ol>
            <li>Install the SonarQube plugin in Jenkins
                <ul>
                    <li>Navigate to Manage Jenkins > Manage Plugins</li>
                    <li>Under the <strong>Available</strong> tab, search for 'SonarQube'</li>
                    <li>Select <strong>SonarQube Scanner for Jenkins</strong> and select 'Install without restart'</li>
                </ul>
            </li>
            <li>Add SonarQube credentials in Jenkins
                <ul>
                    <li>After the installing the plugin, navigate to Jenkins > Credentials > System > Global Credentials and click on "Add Credentials"</li>
                    <li>Enter the following: 
                        <ul>
                            <li>For <strong>Kind: </strong>Secret text</li>
                            <li>For <strong>Scope: </strong>Select 'Global (Jenkins, nodes, items, all child items, etc)'</li>
                            <li>For <strong>Secret: </strong>Paste the copied SonarQube generated token</li>
                            <li>For <strong>ID: </strong>Enter 'sonarqube-token'</li>
                            <li>For <strong>Description: </strong>Enter 'sonarqube-token'</li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li>Add the SonarQube server on Jenkins by doing the following steps:
                <ul>
                    <li>Navigate to Manage Jenkins > Configure System > SonarQube servers</li>
                    <li>Enter the following: 
                        <ul>
                            <li>Check <strong>'Enable injection of SonarQube server configuration as build environment variables'</strong></li>
                            <li>For <strong>Name: </strong>Provide a descriptive name for the connection. (Name: SonarQube)</li>
                            <li>For <strong>Server URL: </strong>http://192.168.227.196:9000</li>
                            <li>For <strong>Server authentication token: </strong>Select the token that was entered for SonarQube <strong>(sonarqube-token)</strong></li>
                        </ul>
                    </li>
                </ul> 
            </li>
        </ol>
</ul>

<h2>Required plugins installed in SonarQube</h2>
<ol>
    <li>On SonarQube, navigate to Administration > Marketplace</li>
    <li>Install the following plugins:
        <ul>
            <li>Code Smells</li>
            <li>Findbugs</li>
            <li>Java Code Quality and Security</li>
            <li>Codehawk Java</li>
            <li>Dependency-Check</li>
        </ul>
    </li>
</ol>

<h2>Additional dependencies added in SpringPetclinic's pom.xml for SonarQube</h2>
The following are additional dependencies that were added in order to trigger both SonarQube and OWASP Dependency Check to perform code quality analysis on SpringPetclinic.

```xml
...
    <properties>
        ...
        <sonar.dependencyCheck.xmlReportPath>target/dependency-check-report.xml</sonar.dependencyCheck.xmlReportPath>
        <sonar.dependencyCheck.htmlReportPath>target/dependency-check-report.html</sonar.dependencyCheck.htmlReportPath>
        <sonar.dependencyCheck.jsonReportPath>target/dependency-check-report.json</sonar.dependencyCheck.jsonReportPath>
    </properties>

    <dependencies>
        ...
    </dependencies>

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>org.sonarsource.scanner.maven</groupId>
                <artifactId>sonar-maven-plugin</artifactId>
                <version>3.6.1.1688</version>
            </plugin>
            <plugin>
                <groupId>org.owasp</groupId>
                <artifactId>dependency-check-maven</artifactId>
                <version>6.0.2</version>
                <configuration>
                    <format>ALL</format>
                    <outputDirectory>target</outputDirectory>
                </configuration>
            </plugin>
        </plugins>
    </build>
...
```
</details>

<details>
<summary>Using Docker in Jenkins Pipeline</summary>
<h2>Additional configurations to use Docker on Jenkins</h2>
Add the jenkins user to the docker group.
<pre>
sudo gpasswd -a jenkins docker
</pre>
Edit the following configuration file.
<pre>
sudo nano /usr/lib/systemd/system/docker.service
</pre>
Edit the following rule to expose the API. 
<br>
<strong>(NOTE: DO NOT CREATE A NEW LINE. SIMPLY ADD THE COMMANDS AT THE END OF THE EXISTING LINE.)</strong>
<pre>
ExecStart=/usr/bin/docker daemon -H unix:// -H tcp://localhost:2375
</pre>
Reload and restart your Docker daemon.
<pre>
sudo systemctl daemon-reload
sudo systemctl restart docker
</pre>
Restart Jenkins.
<pre>
sudo service jenkins restart
</pre>
<h2>Dockerfiles for SpringPetclinic</h2>
There are two different branches. DevSecOps and InsecureDocker. Both using different docker images.
<ul>
    <li><strong>DevSecOps:</strong> Using the latest docker image as of the time of writing. Contains minimal vulnerabilities.</li>
    <li><strong>InsecureDocker:</strong> Using an outdated docker image. Contains many high severity vulnerabilities which would fail the Anchore Container Security Policy.</li>
</ul>
<h3>Dockerfile for DevSecOps</h3>
<pre><code>
FROM openjdk:16-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
</code></pre>
<h3>Dockerfile for InsecureDocker</h3>
<pre><code>
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
</code></pre>
<h2>Setting up Docker Hub credentials in Jenkins</h2>
<ol>
    <li>Navigate to Jenkins > Credentials > System > Global credentials (unrestricted) > Add Credentials</li>
    <li>Enter the following:
        <ul>
            <li>For <strong>Kind: </strong>Select "Secret text"</li>
            <li>For <strong>Scope: </strong>Select "Global (Jenkins, nodes, items, all child items, etc)"</li>
            <li>For <strong>Secret: </strong>Enter the password for your Docker Hub's password</li>
            <li>For <strong>ID: </strong> Enter 'dockerpwd'</li>
            <li>For <strong>Description: </strong> Enter 'Docker password'</li>
        </ul>
    </li>
</ol>
</details>

<details>
<summary>Using OWASP ZAP and Java Zap Programs in Jenkins</summary>
<h2>OWASP ZAP SSH Threshold Freestyle Job</h2>
<h3>Details</h3>
<strong>Name of Job: </strong>SpringPetclinic_DevSecOps_SSHZapThresholdInput
<br>
<strong>SpringPetclinic_DevSecOps_SSHZapThresholdInput</strong> is a Freestyle Job in Jenkins. It will be build in the <strong>SpringPetclinic_DevSecOps_Pipeline</strong> pipeline, during the <strong>ZAP Threshold Input</strong> stage.
<h3>Install SSH Plugin on Jenkins</h3>
Do the following:
<ol>
    <li>Navigate to Jenkins > Manage Jenkins > Manage Plugins</li>
    <li>Click on the <strong>Available</strong> tab and search for <strong>SSH plugin</strong></li>
    <li>Select <strong>SSH plugin</strong> and click <strong>Install without restart</strong></li>
</ol>
<h3>Configure SSH remote host on Jenkins</h3>
Do the following:
<ol>
    <li>Navigate to Jenkins > Manage Jenkins > Configure System</li>
    <li>Scroll down to SSH remote hosts and enter the following:
        <ul>
            <li><strong>Hostname: </strong>Enter <strong>secure-devops</strong></li>
            <li><strong>Port: </strong>Enter <strong>22</strong></li>
            <li><strong>Credentials: </strong>Do the following steps:
                <ol type="a">
                    <li>Click on <strong>Add</strong> and then <strong>Jenkins</strong></li>
                    <li>For <strong>Domain: </strong> Select <strong>Global credentials (unrestricted)</strong></li>
                    <li>For <strong>Kind: </strong> Select <strong>Username with password</strong></li>
                    <li>For <strong>Username: </strong> Enter <strong>fypj</strong>
                    <li>For <strong>Password: </strong> Enter <strong>P@ssw0rd</strong>
                    <li>For <strong>ID : </strong> Enter <strong>fypj</strong>
                    <li>Click on <strong>Add</strong></li>
                    <li>After successfully adding the credentials, for <strong>Credentials</strong>, select <strong>fypj</strong></li>
                </ol>
            </li>
            <li>Leave the other fields in their default value.</li>
        </ul>
    </li>
</ol>
<h2>Install OWASP ZAP and HTML Publisher plugin on Jenkins</h2>
Do the following:
<ol>
    <li>Navigate to Jenkins > Manage Jenkins > Manage Plugins</li>
    <li>Click on the <strong>Available</strong> tab and search for <strong>OWASP ZAP</strong> and <strong>HTML Publisher</strong></li>
    <li>Select <strong>Official OWASP ZAP Jenkins Plugin</strong> and <strong>HTML Publisher</strong></li>
    <li>Click on <strong>Install without restart</strong></li>
</ol>
<h2>OWASP ZAP and Jenkins Configuration and Integration</h2>
Do the following:
<ol>
    <li>Navigate to Jenkins > Manage Jenkins > Configure System</li>
    <li>Scroll down <strong>Global properties</strong> and do the following:
        <ul>
            <li>Check <strong>Environment variables</strong></li>
            <li>For <strong>Name</strong>, enter <strong>ZAPROXY_HOME</strong></li>
            <li>For <strong>Value</strong>, enter <strong>/opt/zaproxy</strong></li>
        </ul>
    </li>
    <li>Scroll down to <strong>ZAP</strong> and enter the following: 
        <ul>
            <li>For <strong>Default Host: </strong>Enter 'localhost'</li>
            <li>For <strong>Default Port: </strong>Enter '8090'</li>
        </ul>
    </li>
</ol>
<h2>OWASP ZAP Java Programs for SpringPetclinic</h2>
All of the ZAP Java Programs are stored inside the <strong>JavaZapPrograms</strong> folder. 
<br>
These Java Programs are programmed to receive custom threshold inputs for the number of high, medium, low, and informational level vulnerabilities found from the ZAP scan and decide whether to abort or continue the pipeline based on the Zap Scan results.
<br><br>
There are 3 different Java programs:
<ol>
<strong>
    <li>ZapModel/ZapRiskLevels.java</li>
    <li>ZapReview/ZapSpringPetclinicReview.java</li>
    <li>ZapThresholdInput/ZapThresholdInput.java</li>
</strong>
</ol>
ZapThreshold.txt is a text file which stores the threshold inputs.
</details>

<details>
<summary>Using Anchore Container Security in Jenkins</summary>
<h2>More details regarding Anchore Container Security with Jenkins</h2>
Inside the 'aevolume' folder contains the 'docker-compose.yaml' file which needs to be executed in order for Anchore-Engine to be operational.
<h2>Install Anchore Container Image Scanner Plugin on Jenkins</h2>
Do the following:
<ol>
    <li>Navigate to Jenkins > Manage Jenkins > Manage Plugins</li>
    <li>Click on the <strong>Available</strong> tab and search for "Anchore"</li>
    <li>Select <strong>Anchore Container Image Scanner Plugin</strong></li>
    <li>Click on <strong>Install without restart</strong></li>
</ol>
<h2>Jenkins and Anchore Integration</h2>
Do the following:
<ol>
    <li>Navigate to Jenkins > Manage Jenkins > Configure System</li>
    <li>Scroll down to <strong>Anchore Container Image Scanner</strong>
        <ul>
            <li>For <strong>Engine URL: </strong>Enter 'http://localhost:8228/v1'</li>
            <li>For <strong>Engine Username: </strong>Enter 'admin'</li>
            <li>For <strong>Engine Password: </strong>Enter 'foobar'</li>
        </ul>
    </li>
</ol>
</details>

<details>
<summary>Using Ansible in Jenkins Pipeline</summary>
<h2>Details</h2>
An 'ansible.yaml' file has already been created. It can be found in the repository. It is used to copy and execute the 'deployment.yaml' file in all of the configured Ansible hosts (AKA Kubernetes-Master) to deploy the SpringPetclinic containerized application.
<br><br>
Below is the content of the "ansible.yaml" file:
<pre>
---
- name: Deploy Kubernetes 
  hosts: all
  become: true
  tasks:
    - name: Copy Jar Files to Kubernetes Master
      copy:
        src: target/spring-petclinic-2.3.0.BUILD-SNAPSHOT.jar
        dest: /root/target/
    - name: Copy Dockerfile to Kubernetes Master
      copy:
        src: Dockerfile
        dest: /root/
    - name: Copy Deployment File to Kubernetes Master
      copy: 
        src: deployment.yaml
        dest: /root/
    - name: Delete deployent (if any)
      command: kubectl delete --ignore-not-found=true -f /root/deployment.yaml
    - name: Build Docker container locally
      shell: |
        cd /root
        docker build -t fypjsecuredevops/springpetclinic:latest .
    - name: Create new deployment 
      command: kubectl apply -f /root/deployment.yaml
</pre>
There is also a 'dev.inv' file. This file is an inventory file which contains details such as the IP address of all of the configured Ansible hosts. 
<br>
Below is the content of the 'dev.inv' file:
<pre>
[servers]
server1 ansible_host=192.168.227.203
<br>
[all:vars]
ansible_python_interpreter=/usr/bin/python3
</pre>
NOTE: The contents of the 'dev.inv' file should be the same as the contents in the '/etc/ansible/hosts' configuration file found in the Ansible Control Node (Jenkins Server).

<h2>Jenkins Ansible Integration</h2>
Reference: <a href>https://www.youtube.com/watch?v=PRpEbFZi7nI</a>
<ul>
    <li><h3>Install the Ansible plugin</h3>
        <ol>
            <li>Go to the Jenkins Home Page</li>
            <li>Navigate to Manage Jenkins > Manage Plugins</li>
            <li>Click on the tab "Available" and search for Ansible</li>
            <li>Select the 'Ansible' plugin and click on "Install without restart"
        </ol>
    </li>
    <li><h3>Configure Ansible on Jenkins</h3>
        <ol>
            <li>Navigate to Manage Jenkins > Global Tool Configuration</li>
            <li>Scroll down to the Ansible section</li>
            <li>Click "Add Ansible"</li>
            <li>Enter the following:
                <ol>
                    <li><strong>Name:</strong> Ansible</li>
                    <li><strong>Path to ansible executables directory:</strong> /usr/bin</li>
                </ol>
            </li>
        </ol>
    </li>
</details>

## Section 3: Running the SpringPetclinic Pipeline on Jenkins Server  
The main pipeline for SpringPetclinic is called SpringPetclinic_DevSecOps_Pipeline.
<br>
Below is the pipeline script for <strong>SpringPetclinic_DevSecOps_Pipeline</strong>.
<pre>
pipeline {
    agent any 
    tools {
        maven "Maven_01"
    }
    
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'DevSecOps', url: 'https://github.com/darrenchooji/fyp_securedevops_springpetclinic.git'
            }
        }
        stage('Build and Unit Testing') {
            steps {
                sh 'mvn clean install'
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('SonarQube Static Code Analysis & OWASP Dependency Check') {
            steps {
                sh 'mvn org.owasp:dependency-check-maven:check'
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.1.1688:sonar'
                }
            }
        }
        stage('SonarQube Quality Gate') {
            steps {
                timeout(time: 60, unit: 'SECONDS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Build, Push, and Run Testing Docker Container') {
            steps {
                sh 'docker build -t fypjsecuredevops/testing_springpetclinic:latest .'
                withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerpassword')]) {
                    sh "docker login -u fypjsecuredevops -p ${dockerpassword}"
                    sh 'docker push fypjsecuredevops/testing_springpetclinic:latest'
                }
                sh 'docker run -d --name spring_petclinic -p 8088:8080 fypjsecuredevops/testing_springpetclinic:latest'
                sleep(time: 30, unit: "SECONDS")
            }
        }
        stage('ZAP Threshold Input') {
            steps {
                build 'SpringPetclinic_DevSecOps_SSHZapThresholdInput'
            }
        }
        stage('ZAP Dynamic Application Security Testing') {
            steps {
                build 'SpringPetclinic_DevSecOps_ZapScan'
            }
        }
        stage('ZAP Review') {
            steps {
                script {
                    sh '''cd /var/lib/jenkins/workspace/SpringPetclinic_DevSecOps_Pipeline/JavaZapPrograms/src
javac ZapModel/*.java ZapReview/ZapSpringPetclinicReview.java
'''
                     
                    env.LS = sh(script:'java -cp /var/lib/jenkins/workspace/SpringPetclinic_DevSecOps_Pipeline/JavaZapPrograms/src ZapReview.ZapSpringPetclinicReview', returnStdout: true).trim()
                    if (env.LS == 'Fail') {
                        error 'Number of security vulnerabilities exceeded threshold'
                    }
                }
            }
            post {
                always {
                    sh 'docker stop spring_petclinic'
                    sh 'docker rm spring_petclinic'
                }
            }
        }
        stage('Anchore Container Security') {
            steps {
                sh '''cd /var/lib/jenkins/workspace/SpringPetclinic_DevSecOps_Pipeline/aevolume
docker-compose up -d'''
                sleep(time: 30, unit: "SECONDS")
                anchore forceAnalyze: true, name: 'anchore_images'
            }
            post {
                always {
                    sh '''cd /var/lib/jenkins/workspace/SpringPetclinic_DevSecOps_Pipeline/aevolume
docker-compose down'''
                }
            }
        }
        stage('Deploy using Ansible to Kubernetes Master') {
            steps {
                ansiblePlaybook credentialsId: 'private-key', disableHostKeyChecking: true, installation: 'Ansible', inventory: 'dev.inv', playbook: 'ansible.yaml'
                sleep(time: 90, unit: "SECONDS")
            }
        }
    }
}
</pre>


