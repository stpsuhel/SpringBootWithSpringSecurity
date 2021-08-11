
function productTable(tableId, url){
    // let searchFilter = $('#' + tableId + '_filter input');
    let table = $('#' + tableId);
    return table.DataTable({
        dom: "Bfrt<'row'<'col-sm-12 col-md-5'l><'col-sm-12 col-md-7'p>>",
        language: {
            searchPlaceholder: 'Search by Name...',
            processing: "<div class='overlay custom-loader-background'>" +
                        "<i class='fa fa-cog fa-spin custom-loader-color'></i></div>",
            // paginate: {
            //     "sFirst":    "&lt;&lt;",
            //     "sLast":     "&gt;&gt;",
            //     "sNext":     "&gt;",
            //     "sPrevious": "&lt;"
            // },
        },
        processing: true,
        serverSide: true,
        deferRender: true,
        ajax: {
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: function (data) {
                return JSON.stringify(data);
            },
            dataSrc: function (response) {
                if (response.error){
                    alertFunction('Error', response.message, 'error');
                }
                return $.map(response.data, (row) => productRow(row));
            }
        },
        columns: [
            {data: 'id'},
            {data: 'name'},
            {data: 'price'},
            {data: 'discount'},
            {data: 'stock'},
            {data: 'category'},
            {data: 'action'}
        ],
        order: [[1, 'asc'], [5, 'asc']],
        paging: true,
        // paginationType: 'first_name',
        columnDefs: [
            {
                targets: 0,
                visible: false,
            },
            {
                targets: 1,
                searchable: true,
                orderable: true
            },
            {
                targets: 5,
                orderable: true
            },
            {
                targets: '_all',
                searchable: false,
                orderable: false,
                defaultContent: '-'
            }
        ],
        responsive: true
    });
}

let activeProductTable = productTable('active-product-table', '/product');
rowClick('active-product-table', activeProductTable, viewProduct);

let allProductTable = productTable('all-product-table', '/admin/product/all');
rowClick('all-product-table', allProductTable, viewProduct)

function productRow(data){
    return {
        id: data.id,
        name: data.name,
        price: data.price,
        discount: data.discount,
        stock: data.stock,
        category: data.category.name,
        action: actionColumn(data.id, data.status)
    };
}

function oneRow(row, table){
    let rowData = productRow(row);
    table.row.add(rowData).draw();
}

$('#show-form').click(function (){
    show('add-product-form');
    clearForm('product-form');
});

$('#hide-form').click(function (){
    hide('add-product-form');
});

$("#product-form").submit(function (e) {
    e.preventDefault();
    removeError();
    let formData = new FormData(this);
    postOrPut('/admin/product', formData, productSaved);
});

function productSaved(data) {
    if (data.errors){
        showError(data.errors);
    }else {
        // getProduct();
        hide('add-product-form');
        clearForm('product-form');
    }
}

function editItem(id){
    get('/product/' + id, showEditProductForm)
}

function showEditProductForm(data) {
    if (data.statusCode === 204){
        alertFunction("Error", data.message, 'error');
        return;
    }
    let product = data.data;
    clearForm('product-form');

    $('#productId').val(product.id);
    $('#name').focus().val(product.name);
    $('#price').val(product.price);
    $('#discount').val(product.discount);
    $('#stock').val(product.stock);
    $('#category').val(product.category.id).trigger('change');
    $('#description').val(product.description);

    product.color.split(',').forEach(function (color){
        $('#' + color).prop('checked', true);
    });

    $('#form-title').text('Update Product');
    $('#form-save-btn').text('Update');
    show('add-product-form');
}

function viewProduct(id){
    get('/admin/product/' + id, viewProductItem);
}

function viewProductItem(data) {
    if (data.statusCode === 204){
        alertFunction("Error", data.message, 'error');
        return;
    }
    let product = data.data;
    console.log(product)

    $('#modalName').text(product.name);
    $('#modalPrice').text(product.price);
    $('#modalDiscount').text(product.discount);
    $('#modalStock').text(product.stock);
    $('#modalCategory').text(product.category.name);
    $('#modalCreatedBy').text(product.createdBy);
    $('#modalCreatedAt').text(dateFormat(product.createdAt, 'YYYY-MM-DD hh:mm A'));
    $('#modalUpdatedBy').text(product.updatedBy);
    $('#modalUpdatedAt').text(dateFormat(product.updatedAt, 'YYYY-MM-DD hh:mm A'));
    $('#modalDescription').text(product.description);

    if (product.category){
        $('#categoryModalName').text(product.category.name);
        $('#categoryModalCreatedBy').text(product.category.createdBy);
        $('#categoryModalCreatedAt').text(dateFormat(product.category.createdAt, 'YYYY-MM-DD hh:mm A'));
        $('#categoryModalUpdatedBy').text(product.category.updatedBy);
        $('#categoryModalUpdatedAt').text(dateFormat(product.category.updatedAt, 'YYYY-MM-DD hh:mm A'));
        $('#categoryModalDescription').text(product.category.description);
    }

    if (!product.status){
        show('active-btn');
        $('#active-btn').empty()
            .append('<button onclick="mackActiveData(' + product.id +
                            ')" type="button" class="btn btn-primary">Make Active</button>');
    }else {
        hide('active-btn');
    }
    $('#productModal').modal('show')
}

function deleteItem(id){
    deleteAlert('/admin/product/' + id, deleteSuccess);
}

function deleteSuccess(data) {
    console.log(tableRowIndex)
    if (data.data){
        alertFunction("Success", data.message, 'success');
        activeProductTable.row(tableRowIndex).remove().draw(false);
        tableRowIndex = -1;
    }else {
        alertFunction("Error", data.message, 'error');
    }
}

function mackActiveData(id){
    active('/admin/product/active', id, activeSuccess);
}

function activeSuccess(data){
    console.log(data);
    if (data.data){
        oneRow(data.data, activeProductTable);
        alertFunction("Success", data.message, 'success');
        $('#productModal').modal('hide');
    }else {
        alertFunction("Error", data.message, 'error');
    }
}
