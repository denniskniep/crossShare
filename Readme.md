
## Develop
Start fontend. see `frontend/Readme.md`


## Update Share with curl
```
curl -X POST http://localhost:8080/api/share/update \
   -H 'Content-Type: application/json' \
   -d '{"secret":"W3yACbpEWZQnyOI1sqI5", "type":"REDIRECT","sharedObject":{"url":"https://google.de"}}'
```