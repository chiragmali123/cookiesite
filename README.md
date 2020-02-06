
#Application to demonstrate evercookie.


mvn clean package -DskipTests

docker rm -f cookie-app



docker rmi akshay0harale/cookie-app:latest

docker build -t akshay0harale/cookie-app:latest .

docker push akshay0harale/cookie-app:tagname

docker run --name cookie-app  -v /home/synerzip/db/doc:/app/app-db -p 8080:8080  akshay0harale/cookie-app:latest
