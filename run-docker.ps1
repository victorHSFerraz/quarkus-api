./mvnw clean package -DskipTests

docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-social-jvm .

docker run -i --rm -p 8080:8080 `
  --name container-name `
  -e DB_USERNAME=$env:DB_USERNAME `
  -e DB_PASSWORD=$env:DB_PASSWORD `
  -e DB_URL=$env:DB_URL `
  quarkus/quarkus-social-jvm

