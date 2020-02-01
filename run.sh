#!/bin/bash

sudo apt install nginx
sudo rm /etc/nginx/sites-available/default
cd /etc/nginx/sites-available/
sudo ln -s ~YOU_NAME_COMP/noSkype/nginx_conf
cd  ../sites-enabled/
sudo rm default
sudo ln -s /etc/nginx/sites-available/nginx_conf
sudo service nginx restart
