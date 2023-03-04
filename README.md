
# Rotina de transações

- Modules:
    - Car
    - Customer
    - Driver
    - Claim
    - Insurance



## Appendix

- Java 8+
- Spring Boot
- Gladle
- Database H2
- Hibernate JPA


## Installation

#### Manual
- Clone the project in some source folder
```bash
git clone git@github.com:lucasjavit/insurances.git
```
- Access the project
```bash
cd insurances
```
- compile the project
```bash
./gradlew build

```
- Execute
```bash
./gradlew bootRun
```

#### Docker
- Initialize the docker

```bash
git clone git@github.com:lucasjavit/insurances.git

```

- Access the project folder
```bash
cd insurances
```

- Build the project
```bash

docker build -t yourusername/repository-name .

```

- ERun the project
```bash
docker run -p 8080:8080 yourusername/repository-name
```


## Api Documentation

### CUSTOMERS
#### POST


```bash
curl --location 'localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data '{
    "name": "jose Aguiar",
    "drivers": [
        {
            "document": "132131",
            "birthdate": "2020-05-20",
            "car_id": 1,
            "is_main_driver": true,
            "is_claim": true
        },
        {
            "document": "45611",
            "birthdate": "1989-05-20",
            "car_id": 1,
            "is_main_driver": false
        }
    ]
}'
```

### INSURANCES
#### POST


```bash
curl --location 'localhost:8080/insurances/budget' \
--header 'Content-Type: application/json' \
--data '{

    "customer_id": 1,
    "car_id": 1,
    "is_active": true

}'
```


#### PATCH

```bash
curl --location --request PATCH 'localhost:8080/insurances/budget/1' \
--header 'Content-Type: application/json' \
--data '{

    "customer_id": 1,
    "car_id": 2,
    "is_active": true

}'
```

####  GET


```bash
curl --location 'localhost:8080/insurances/budget/1' \
--data ''
```

####  DELETE

```bash
curl --location --request DELETE 'localhost:8080/insurances/budget/1' \
--data ''
```

## Documentação Swagger

- Para acessar a documentação Swagger

```bash
http://localhost:8080/swagger-ui/index.html
```
