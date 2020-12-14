# Bake Sale App

### How to build 
There is a script that creates a docker images of the api
```
./build.sh
```

### How to run
To run the api and db use docker-compose:
```
docker-compose up -d
```

To run the client:
```
cd frontend && yarn install && yarn start
```

### Load initial state
The sale is configured via file, the reference is `./frontend/public/items.csv`