var express = require('express');
var bodyParser = require("body-parser");
var app = express();
var APP_PORT = 8088;

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Startup our Payara wrapper and deploy the application
const PayaraMicroWrapper = Java.type('net.cuprak.graalvm.PayaraMicroWrapper');
PayaraMicroWrapper.run();

// Call the stateless session bean to get our message
app.get('/', function (req, res) {
    res.send(PayaraMicroWrapper.sayHello());
});

app.listen(APP_PORT, function() {
    console.log("Server listening on port " + APP_PORT);
});