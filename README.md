# Spring API Service forProduct Catalog with User Details  

Developed using MySQL and Elasticsearch

### NoSQL : Product Entity : ElasticSearch 
### SQL : User Entity : MySQL


This repository contains a Spring API service that utilizes Docker and Docker Compose to run the project. The API service supports CRUD operations for a user service with MySQL and a product catalog service with Elasticsearch.

## Prerequisites

To run this project, make sure you have the following installed:

- Docker: [Installation Guide](https://docs.docker.com/get-docker/)
- Docker Compose: [Installation Guide](https://docs.docker.com/compose/install/)

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/Ayushi1504/ProductCatalog.git
cd your-repo
```
2. Configure the MySQL User Table:

- Open `ProductCatalog/src/main/resources/application.properties`.
- Modify the following properties according to your MySQL configuration:
  spring.datasource.url=jdbc:mysql://localhost:3306/database_name
  spring.datasource.username=mysql_username
  spring.datasource.password=mysql_password

3. Configure the Elasticsearch Product Catalog Table:

- Open `ProductCatalog/src/main/resources/application.properties`.
- Modify the following properties according to your Elasticsearch configuration:


  elasticsearch.hostname=localhost
  elasticsearch.port=9200
  elasticsearch.username=elastic
  elasticsearch.fingerprint=authentication fingerprint
  elasticsearch.password=password_for_elastic


4. Update the Docker Compose file:


- Open the docker-compose.yaml file in a text editor.

Find the MYSQL_ROOT_PASSWORD environment variable under the mysql service section and replace your_password with your desired password for the MySQL root user.
Find the ELASTICSEARCH_PASSWORD environment variable under the elasticsearch service section and replace it with your desired password for Elasticsearch.


- Save the changes to the docker-compose.yaml file.
- Update the application.properties file:

5. Open a terminal or command prompt.
Navigate to the project directory.

Run the following command to build the Docker image for only running the Spring Java service:
```
docker-compose build spring-service
```

Start the containers:

Run the following command to start the MySQL cluster, Elasticsearch cluster, and the Spring Java service:
```
docker-compose up
```
Docker Compose will read the docker-compose.yaml file and start the defined services. You should see logs indicating the progress of the containers' startup.


### Access the services:

## MySQL: 
You can connect to the MySQL database using a MySQL client or any MySQL management tool, using the following credentials:


- Host: localhost
- Port: 3306
- Username: root
- Password: Use the password you set in the docker-compose.yaml file.

### To Load data into MYSQL:
    You can update the file setup.sql and add the insert queries for loading data in the user table.

## Elasticsearch: 
You can access Elasticsearch using an HTTP client or tools like Kibana. Elasticsearch will be available at http://localhost:9200. Use the credentials:

- Username: elastic
- Password: Use the password you set in the docker-compose.yaml file.

The docker compose up command will start the Elasticsearch container with the defined schema mapping.

### To Load data into Elasticsearch: 
After the Elasticsearch container is up and running, you can load data into the product_catalog index. You can use a tool like cURL or a REST client like Postman to send HTTP requests to the Elasticsearch API.

Here's an example cURL command to index a document into the product_catalog index:

```
curl -XPOST -H "Content-Type: application/json" -d '{
  "productId": "1",
  "productType": "type1",
  "price": 100,
  "category": 1,
  "inventory": "10 units"
}' http://localhost:9200/product_catalog/_doc/1
```

You can modify the data in the JSON object according to your requirements and execute the cURL command to load data into Elasticsearch.

## Spring Java service: 
Your Spring Java service will be accessible at http://localhost:8080.

### That's it! 
You have now successfully run the Docker Compose file, updated the passwords for MySQL and Elasticsearch, and modified the application.properties file for the Spring Java service. 

You can interact with your services based on your specific use case.
For queries please contact ayushi.2016.sharma@gmail.com .