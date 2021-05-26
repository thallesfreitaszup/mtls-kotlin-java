const fs = require('fs');
const https = require('https');
const certificate = fs.readFileSync('./certificate.pem')
const key = fs.readFileSync('./mydomain.key')
console.log(certificate.toString('utf-8'))
console.log(key.toString('utf-8'))
console.log('alou')
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
            console.log(req.client.authorized)
            if(!req.client.authorized){
                res.writeHead(200);
                res.end('client not certificated');
            }
            res.writeHead(200);
            res.end('Hello, world!');
        }
    )
    .listen(9443);