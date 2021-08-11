
let productList;

function getProductCardHtml(product){
    let discountPrice = product.price - product.discount;

    let discountHtml = '';
    if (product.discount > 0){
        discountHtml = '<div class="card-price"><del class="card-price px-2"><span class="currency">৳</span><span class="price">'
                        + product.price + '</span></del><span class="fs-discount text-white-50">(-' + product.discount + ')</span></div>';
    }

    return '<div class="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2 px-1"><div class="card bg-dark-gradient custom-hover">' +
            '<div class="stretched-link-wrap"><a href="/product/' + product.id + '/details/page" class="stretched-link"></a>' +
            '<div class="card-header p-2"><img class="card-img-top border w-100" src="' + product.mainImage.imageUrl +
            '"alt="Product Image" style="width: 150px; height: 200px"></div><div class="card-body py-2">' +
            '<h5 class="card-title mb-2 fw-mediumbold">' + product.name + '</h5>' +
            '<div class="d-flex justify-content-end"><div class="card-price text-secondary"><span class="currency">৳</span>' +
            '<span class="price">' + discountPrice + '</span></div>' + discountHtml + '</div></div></div><div class="card-footer">' +
            '<button onclick="addProductToCurt(' + product.id + ')" class="btn btn-primary btn-border btn-round pull-right">' +
            '<span class="btn-label"><i class="fas fa-cart-plus"></i></span>Add Cart</button></div></div></div>';
}

getProduct();
function getProduct() {
    let data = {
        draw: 1,
        start: 0,
        length: 25,
        order: [{column: 0, dir: 'desc'}],
        columns: [
            {
                "data": "id",
                "name": "",
                "searchable": false,
                "orderable": false,
                "search": {
                    "value": "",
                    "regex": false
                }
            }
        ]
    };
    postOrPut('/product', JSON.stringify(data), productSuccess, true);
}

function productSuccess(data) {
    if (data.error){
        alertFunction('Error', data.message, 'error')
        return;
    }
    $('#product-card-modal').empty();
    if (data.data){
        productList = data.data;
        productList.forEach(function (product){
            let card = getProductCardHtml(product);
            $('#product-card-modal').append(card);
        });
    }
}

function addProductToCurt(id) {
    if (productList.length <= 0){
        alertFunction('Error', data.message, 'error')
        return;
    }
    productList.forEach((product) => {
        if (product.id === id){
            // console.log(product);
            let cartItem = $('#total-cart-item').text();
            $('#total-cart-item').text(parseInt(cartItem) + 1)
            console.log(parseInt(cartItem) + 1)
        }
    });
}