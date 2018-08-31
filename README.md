# springbootTemplateSSL
Template for a secure spring boot web app.

INSTALLATION CHECKLIST - WORK IN PROGRESS

**********************************************
1.[]-install java
**********************************************
..[]-update system
>apt-get update && apt-get upgrade

..[]-install a required package
>apt-get install software-properties-common

..[]-add java repo
>add-apt-repository ppa:webupd8team/java

..[]-update again
>apt-get update

..[]-install 8 0r 9
>apt-get install oracle-java8-installer
>apt-get install oracle-java9-installer
****************************************************












**************************************************
2.[]-install MySQL
**************************************************
>sudo apt-get update
>sudo apt-get install mysql-server

.[]-will be prompted to change root password
..[]-changed to desired password

.[]-follow prompts and remove default/test logins and tables

.[]-create a database
>mysql -p
>create database {dbname};

.[]-add admin user for remote schema maintenance
>grant all privileges on {sitename}.* to '{admin_login}'@'%' identified by "{admin_password}"; 

.[]-create website user for running queries from site
>grant select on {sitename}.* to '{site_user_name}'@'%' identified by "{site_user_password}"; 
>grant insert on {sitename}.* to '{site_user_name}'@'%' identified by "{site_user_password}"; 
>grant update on {sitename}.* to '{site_user_name}'@'%' identified by "{site_user_password}"; 
>grant delete on {sitename}.* to '{site_user_name}'@'%' identified by "{site_user_password}"; 

.[]-bind address
..[]-open my.cnf
..[]-add text:

[mysqld]
bind-address    = 0.0.0.0

..[]-save

*************************************************












*************************************************
3.[]-create tables
*************************************************
.[]-used mysql workbench to make test tables
.[]-used mysql workbench to make prod tables
************************************************










************************************************
4.[]-pre deploy ssl key setup
***********************************************
>sudo certbot certonly --standalone -d {site_name}.com -d www.{site_name}.com
- Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/{site_name}.com/fullchain.pem
   Your key file has been saved at:
   /etc/letsencrypt/live/{site_name}.com/privkey.pem
   Your cert will expire on 2018-10-29. To obtain a new or tweaked
   version of this certificate in the future, simply run certbot
   again. To non-interactively renew *all* of your certificates, run
   "certbot renew"
   
.[]-place cert chain and keys in keystore
>openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out www.{site_name}.com.pfx -alias tomcat

.[]-secure with password as prompted
***********************************************










************************************************
5.[]-deploy to PROD
***********************************************
.[]-locally run as maven-install
..[]-generates jar
..[]-copy jar to var/

.[]-upload to var/{app_jar_name}/
***********************************************










**************************************************
.[]-setup service?
************************************************
.[]-on PROD create systemd service for running app
..[]-create file "etc/systemd/system/{app_jar_name}.service"
..[]-add text:

[Unit]
Description={app_jar_name}
After=syslog.target

[Service]
User=root
ExecStart=/bin/bash /var/{app_jar_name}/{app_jar_name}.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target

..[]-save

..[]-start service
>service {app_jar_name} start

..[]-verify service running
>systemctl status {app_jar_name}

..[]-stop service 
>service {app_jar_name} stop

..[]-verify service stopped
>systemctl status {app_jar_name}
************************************************
