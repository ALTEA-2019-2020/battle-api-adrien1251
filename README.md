# Battle api

This API is implemented to simulate pokemon battle 

# Configuration 

This project run with postgres database 

## Set env variable 
Different environment variable need to be set : 

`USER` : the user to connect to the spring boot app

`PASSWORD`: the password to connect to the spring boot app
 
## Run the project 

After configure all env variable do
`mvn clean install && mvn spring-boot:run`

The application is started on port 9001

# Implementation 

This API is available on this link : [`https://battle-api-adrien1251.herokuapp.com/`](https://battle-api-adrien1251.herokuapp.com/)

Available routes:

`POST battles/?dresseur1=<name>&dresseur2=<name>`   : Create battle room

`GET battles/<uuid>`                                : Get specific battle

`GET battles/`                                      : Get all battles

`POST battles/<uuid>/<trainerName>/attack`          : Trainer name attack opponent in room with this uuid

`POST /{uuid}/{trainerName}/heal`                   : Trainer name heal his current pokemon in room with this uuid 
