## Test app for Dia-soft
Написать REST сервис выполняющий CRUD операции над таблицей product(id int, name varchar, amount int)
и один метод который возвращает сумму полей amount по полю name которое передается в метод.
Ожидается использование spring boot в реализации.

## Running the application
Just run TestappApplication

### Curl commands REST API

| Path                 |  Method | Description   |
|----------------------|---------|---------------|
| [`/product/all`]     |   `GET` |get all product|
* **Example:**

`curl -s http://localhost:8080/product/all`

| Path                 |  Method | Description     |
|----------------------|---------|-----------------|
| [`/product/{id}`]    |   `GET` |get by id product|
* **Example:**

`curl -s http://localhost:8080/product/23e552ca-afed-432f-8b6f-f9b013dc1f9d`

| Path                 |  Method | Description     |
|----------------------|---------|-----------------|
| [`/product/{id}`]    |   `DEL` |del by id product|
* **Example:**

`curl -s -X DELETE http://localhost:8080/product/dfdd1d10-d7c6-4824-830f-2716bac70a86`

| Path                 |  Method | Description     |
|----------------------|---------|-----------------|
| [`/product`]         |  `POST` | create product  |
* **Example:**

`curl -s -X POST -d '{"name":"name4", "amount":"5000"}' -H 'Content-Type:application/json' http://localhost:8080/product`

| Path                 |  Method | Description     |
|----------------------|---------|-----------------|
| [`/product/{id}`]    |   `PUT` |  update product |
* **Example:**

`curl -s -X PUT -d '{"name":"name2222", "amount":"1000000"}' -H 'Content-type: application/json' http://localhost:8080/product/70290a60-fe57-46cd-b110-105897bd8664`

| Path                                     |  Method | Description        |
|------------------------------------------|---------|--------------------|
| [`/product/getSumAmountQuery/{name}`]    |   `GET` |get sum amount Query|
* **Example:**

`curl -s http://localhost:8080/product/getSumAmountQuery/name2`