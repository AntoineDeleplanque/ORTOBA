var data = require('./ortoba-data.js');
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

app.get('/equipe/getAll',function(req, res){
    data.getEquipes(dataCallback(res));
});

app.get('/equipe/classement', function(req, res){
    data.classement(dataCallback(res));
})

app.post('/equipe/add/:nom/:ville', function(req, res){
    data.addEquipe(req.params.nom, req.params.ville, dataCallback(res));
});

app.post('/match/add/:IdEquipe1/:ScoreEquipe1/:IdEquipe2/:ScoreEquipe2', function(req, res){
    data.addMatch(req.params.IdEquipe1, req.params.ScoreEquipe1, req.params.IdEquipe2, req.params.ScoreEquipe2, dataCallback(res));
});

/*app.get('/bills/:id', function(req, res) {
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
});*/

app.listen(80);