./wait-for-it.sh db:5432 -t 15
java -Djava.security.egd=file:/dev/./urandom -jar backendjava.jar