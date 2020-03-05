#!/bin/bash

sudo apt install nginx
sudo rm /etc/nginx/sites-available/*
sudo rm /etc/nginx/sites-enabled/*
sudo ln -s "$PWD"/nginx_conf /etc/nginx/sites-available/
sudo ln -s /etc/nginx/sites-available/nginx_conf /etc/nginx/sites-enabled/
sudo service nginx restart
