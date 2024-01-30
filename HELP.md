

## prerequisite

* have java 17
* aws configured (have ~/.aws/config )

## how to run the app 
* put the local stack profile
```bash

echo -e "\n[profile local_pets]\nregion=us-east-1\naws_access_key_id=localstack\naws_secret_access_key=localstack" >> ~/.aws/config
```
*  run the app 

```bash 
* ./gradlew bootRun
```



## Steps to upload file and pet and 

Create pictures 
``` bash 
curl -X POST http://localhost:8080/api/v1/xray -F 'file=@/path/to/location/filename.png'
```

Create pet 

``` bash 
 curl -X POST -H "Content-Type: application/json" -d '{
  "name": "YourPetName",
  "specie": "YourPetSpecies",
  "age": 3,
  "breed": "YourPetBreed"
}'  http://localhost:8080/api/v1/pet

```
 
Attach them 
``` bash 
curl -X PATCH http://localhost:8080/api/v1/pet/1/xrayImages/2                        
``` 


get dto 
``` bash 
curl -H "Content-Type: application/json" http://localhost:8080/api/v1/pet/152
``` 


get file
``` bash 
 curl -o output_image.png -H "Accept: application/octet-stream"  http://localhost:8080/api/v1/xray/3
``` 
