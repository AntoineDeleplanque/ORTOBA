var data = require('./bill-data.js');
var express = require('express');
var app = express();

function dataCallback(res){
    return function(err, data){
        if(err){
            res.send({error : err});
        }else{
            res.send(data);
        }
    };
}


app.get('/bills',function(req, res){
    data.listBills(dataCallback());
});

app.get('/bills/:id', function(req, res) {
    data.getBill(req.params.id, dataCallback(res));
});

app.get('/clients', function(req, res){
    data.listClients(dataCallback(res));
});

app.get('/clients/:id', function(req,res){
    data.getClient(req.params.id, dataCallback(res));
});

app.get('/products', function(req, res){
    data.listProducts(dataCallback(res));
});

app.get('/products/:id', function(req, res){
    data.getProduct(req.params.id, dataCallback(res));
});

app.post('/bills/:billId/add/:productId', function(req, res){
    data.addProductToBill(req.params.billId, req.params.productId, dataCallback(res));
});

app.post('/bills/:billId/remove/:productId', function(req, res){
    data.removeProductFromBill(req.params.billId, req.params.productId, dataCallback(res));
});

app.post('/bills/:id', function(req, res){
    data.updateBill(req.params.id, req.body, dataCallback(res));
});

app.post('/products/:id', function(req, res){
    data.updateProduct(req.params.id, req.body, dataCallback(res));
});

app.post('.clients/:id', function(req, res){
    data.updateClient(req.params.id, req.body, dataCallback(res));
});

app.post('/clients', function(req, res){
    data.insertClient(req.body, dataCallback(res));
});

app.post('/bills', function(req, res){
    data.insertBill(req.body, dataCallback(res));
});

app.post('/products', function(req, res){
    data.insertProduct(req.body, dataCallback(res));
});

app.listen(8080);