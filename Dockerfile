FROM httpd:latest
EXPOSE 80
WORKDIR /usr/local/apache2/htdocs/
COPY index.html .
