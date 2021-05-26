const fs = require('fs');
const https = require('https');
const certificate = fs.readFileSync('./certificate.pem')
const key = fs.readFileSync('./mydomain.key')
https
    .createServer(
        {
            // ...
            key: key,
            cert: certificate,
            rejectUnauthorized: true,
            requestCert:true,
            ca: fs.readFileSync('./client-certificate.pem'),
            // ...
        },
        (req, res) => {
            console.log(`Authroized: ${req.client.authorized}`)
            if(!req.client.authorized){
                res.writeHead(401);
                res.end('client not certificated');
            }
            res.writeHead(200);
            res.end('Hello, world!');
        }
    )
    .listen(9443);