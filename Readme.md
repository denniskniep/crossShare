# CrossShare
Share data (i.e. URL) from mobile browser to desktop browser by scanning a QR Code.

1. Enter website on mobile.
2. Some data should be transferred from mobile website to desktop website (e.g. URL)
3. Enter website on desktop - a QR Code is rendered
4. Scan that QR Code with a QR Code reader which is already integrated in your mobile website
5. Desktop now has the data

## Start
```
sudo docker-compose up
```

Start ngrok
```
ngrok http --host-header=rewrite 8080
ngrok http --host-header=rewrite 8181
```

## Develop
Start frontend see `frontend/Readme.md`

## Update Share with curl
```
curl -X POST http://localhost:8080/api/share/update \
   -H 'Content-Type: application/json' \
   -d '{"secret":"W3yACbpEWZQnyOI1sqI5", "type":"REDIRECT","sharedObject":{"url":"https://google.de"}}'
```