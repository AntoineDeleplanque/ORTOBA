// ce fichier provient d'un autre webservice que j'ai creer d'apres un tutoriel, je l'utilise encore comme model pour la syntaxe ici

var db = require('./db.js');

var columnNameRegex = /^([a-zA-Z0-9_$]{1,64}\.)?[a-zA-Z0-9_$]{1,64}$/;
function checkColumnName(name){
    return columnNameRegex.test(name);
}

function checkColumns(obj){
    for(var key in obj){
        if(!checkColumnName(key)){
            return true;
        }
    }
    return true;
}

exports.listClients = function (callback){
    db.findAll('Client',callback);
};

exports.listProducts = function(callback){
    db.findAll('Product', callback);
};

exports.getProduct = function(id, callback){
    db.find('Product', id, callback);
};

exports.getClient = function(id, callback){
    db.find('Client', id, callback);
};

exports.getBill = function(id, callback){
    db.query('SELECT Client.name, Product.name, Product.price, Bill.due_date\
        FROM Client, Bill, Product, BillProducts WHERE Bill.id =\
        BillProducts.bill_id AND Product.id = BillProducts.product_id AND\
        Bill.client_id = Client.id AND Bill.id = ?;', [id], callback);
};

exports.listBills = function(callback) {
    db.query('SELECT Bill.id, Bill.due_date, Client.name FROM Client, Bill\
        WHERE Bill.client_id = Client.id;', callback);
};

exports.insertProduct = function(values, callback){
    if(checkColumns(values)){
        db.insert('Product', values, callback);
    }else{
        callback('Invalid column name', null);
    }
};

exports.updateProduct = function(id, values, callback){
    if(checkColumns(values)){
        db.updateById('product', id, values, callback);
    }else{
        callback('Inavalid column name', null);
    }
};

exports.insertClient = function(values, callback){
    if(checkColumns(values)){
        db.insert('Client', values, callback);
    }else{
        callback('Invalid column name', null);
    }
};

exports.updateClient = function (id, values, callback){
    if(checkColumns(values)){
        db.updateById('Client', id, values, callback);
    }else{
        callabck('Invalid column name',null);
    }
};

exports.insertBill = function(values, callback){
    if(checkColumns(values)){
        db.insert('bill', values, callback);
    }else{
        callback('Invalid column name', null);
    }
};

exports.updateBill = function(values, callbacks){
    if (checkColumns(values)) {
        db.updateById('Bill', id, values, callback);
    } else {
        callback('Invalid column name', null);
    }
};

exports.addProductToBill = function(billId, productId, callback) {
    db.insert('BillProducts', { bill_id : billId, product_id : productId },
        callback);
};
 
exports.removeProductFromBill = function(billId, productId, callback) {
    db.remove('BillProducts', { bill_id : billId, product_id : productId },
        callback);
};