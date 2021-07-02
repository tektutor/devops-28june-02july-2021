All the instructions must be done as an adminstrative(root) user.

### Installing Prometheus
```
sudo su -
yum update -y
```

Disable SELinux
```
setenforce 0
```
For permanently disabling selinux, edit /etc/sysconfig/config and update "SELINUX=enforcing" to "SELINUX=disabled"


#### Create a Prometheus user
```
useradd --no-create-home --shell /bin/false prometheus
```

#### Create prometheus required folders
```
mkdir /etc/prometheus
mkdir /var/lib/prometheus
chown prometheus:prometheus /etc/prometheus
chown prometheus:prometheus /var/lib/prometheus
```

#### Download Prometheus and extract the tar gunzip file
```
yum install wget -y
wget https://github.com/prometheus/prometheus/releases/download/v2.12.0/prometheus-2.12.0.linux-amd64.tar.gz
tar -xvzf  prometheus-2.12.0.linux-amd64.tar.gz
cp -r prometheus-2.12.0.linux-amd64/prometheus /usr/local/bin
cp -r prometheus-2.12.0.linux-amd64/promtool /usr/local/bin
cp -r prometheus-2.12.0.linux-amd64/consoles /etc/prometheus
cp -r prometheus-2.12.0.linux-amd64/console_libraries/ /etc/prometheus
chown -R prometheus:prometheus /etc/prometheus/consoles
chown -R prometheus:prometheus /etc/prometheus/console_libraries
```

#### Configure Prometheus to collect metrics from Jenkins

vim /etc/prometheus/prometheus.yml

```
# Global config
global:
  scrape_interval:     15s 
  evaluation_interval: 15s 
  scrape_timeout: 15s  

scrape_configs:
  - job_name: 'prometheus'
    scrape_interval: 5s
    static_configs:
      - targets: ['localhost:9090']

  - job_name: 'jenkins'
    scrape_interval: 5s
    metrics_path: /prometheus
    static_configs:
      - targets: ['localhost:8080']
```

Save the file and change ownership to prometheus user.

```
chown prometheus:prometheus /etc/prometheus/prometheus.yml
```

#### Create a Prometheus Service
vim /etc/systemd/system/prometheus.service

```
[Unit]
Description=Prometheus
Documentation=https://prometheus.io/docs/introduction/overview/
Wants=network-online.target
After=network-online.target

[Service]
Type=simple
Environment="GOMAXPROCS=2"
User=prometheus
Group=prometheus
ExecReload=/bin/kill -HUP $MAINPID
ExecStart=/usr/local/bin/prometheus \
  --config.file=/etc/prometheus/prometheus.yml \
  --storage.tsdb.path=/var/lib/prometheus \
  --web.console.templates=/etc/prometheus/consoles \
  --web.console.libraries=/etc/prometheus/console_libraries \
  --web.listen-address=0.0.0.0:9090 \
  --web.external-url=

SyslogIdentifier=prometheus
Restart=always

[Install]

WantedBy=multi-user.target
```

#### Start the prometheus service
```
systemctl daemon-reload
systemctl start prometheus.service
systemctl status prometheus.service
```

#### Access Prometheus from web browser on the lab machine
```
http://localhost:9090
```

### Install Grafana

#### Create Grafana yum repo file
vim /etc/yum.repos.d/grafana.repo
```
[grafana]
name=grafana
baseurl=https://packages.grafana.com/oss/rpm
repo_gpgcheck=1
enabled=1
gpgcheck=1
gpgkey=https://packages.grafana.com/gpg.key
sslverify=1
sslcacert=/etc/pki/tls/certs/ca-bundle.crt
```

#### Install Grafana
```
yum install -y grafana
systemctl start grafana-server
systemctl status grafana-server
```

#### Accessing Grafana Dashboard
From your lab web server, you may access Grafana at the URL http://localhost:3000

###### Login Credentials
```
username - admin
password - admin
```

