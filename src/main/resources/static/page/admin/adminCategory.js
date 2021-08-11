
function categoryTable(tableId){
    return $('#' + tableId).DataTable({
        dom: "Bfrt<'row'<'col-sm-12 col-md-5'l><'col-sm-12 col-md-7'p>>",
        columns: [
            {data: 'id'},
            {data: 'name'},
            {data: 'createdBy'},
            {data: 'createdAt'},
            {data: 'updatedBy'},
            {data: 'updatedAt'},
            {data: 'action'}
        ],
        order: true,
        columnDefs: [
            {
                targets: 0,
                searchable: false,
                visible: false,
            },
            {
                targets: 1,
                searchable: true,
                orderable: true
            },
            {
                targets: '_all',
                searchable: false,
                defaultContent: '-'
            }
        ],
        responsive: true
    });
}

let activeCategoryTable = categoryTable('active-category-table');
rowClick('active-category-table', activeCategoryTable, viewCategory);

let allCategoryTable = categoryTable('all-category-table');
rowClick('all-category-table', allCategoryTable, viewCategory)

function categoryRow(data){
    return {
        id: data.id,
        name: data.name,
        createdBy: data.createdBy,
        createdAt: data.createdAt != null? dateFormat(data.createdAt,'YYYY-MM-DD hh:mm A') : "",
        updatedBy: data.updatedBy,
        updatedAt: data.updatedAt != null? dateFormat(data.createdAt,'YYYY-MM-DD hh:mm A') : "",
        action: actionColumn(data.id, data.status)
    };
}

function oneRow(row, table){
    let rowData = categoryRow(row);
    table.row.add(rowData).draw();
}

getCategory();
function getCategory(){
    get('/category', setActiveTableData);
    get('/admin/category', setAllTableData);
}

function setActiveTableData(data){
    getAllCategorySuccess(data, activeCategoryTable);
}

function setAllTableData(data){
    getAllCategorySuccess(data, allCategoryTable);
}

function getAllCategorySuccess(data, table){
    table.clear().draw();
    if (data.data == null){
        return;
    }
    data.data.forEach(function (row) {
        oneRow(row, table);
    });
}

$('#show-category-form').click(function (){
    show('add-category-form');
    clearForm('category-form');
});

$('#hide-category-form').click(function (){
    hide('add-category-form');
});

$("#category-form").submit(function (e) {
    e.preventDefault();
    removeError();
    let formData = new FormData(this);
    postOrPut('/admin/category', formData, categorySaved);
});

function categorySaved(data) {
    if (data.errors){
        showError(data.errors);
    }else {
        getCategory();
        hide('add-category-form');
        clearForm('category-form');
    }
}

function editItem(id){
    get('/category/' + id, showEditCategoryForm)
}

function showEditCategoryForm(data) {
    if (data.statusCode === 204){
        alertFunction("Error", data.message, 'error');
        return;
    }
    let category = data.data;
    clearForm('category-form');

    $('#categoryId').val(category.id);
    $('#name').focus();
    $('#name').val(category.name);
    $('#description').val(category.description);

    $('#form-title').text('Update Category');
    $('#form-save-btn').text('Update');
    show('add-category-form');
}

function viewCategory(id){
    get('/admin/category/' + id, viewCategoryItem);
}

function viewCategoryItem(data) {
    if (data.statusCode === 204){
        alertFunction("Error", data.message, 'error');
        return;
    }
    let category = data.data;
    console.log(category)

    $('#categoryModalName').text(category.name);
    $('#categoryModalCreatedBy').text(category.createdBy);
    $('#categoryModalCreatedAt').text(dateFormat(category.createdAt, 'YYYY-MM-DD hh:mm A'));
    $('#categoryModalUpdatedBy').text(category.updatedBy);
    $('#categoryModalUpdatedAt').text(dateFormat(category.updatedAt, 'YYYY-MM-DD hh:mm A'));
    $('#categoryModalDescription').text(category.description);

    if (!category.status){
        show('category-active-btn');
        $('#category-active-btn').empty();
        $('#category-active-btn').append('<button onclick="mackActiveData(' + category.id +
                            ')" type="button" class="btn btn-primary">Make Active</button>');
    }else {
        hide('category-active-btn');
    }
    $('#categoryModal').modal('show')
}

function deleteItem(id){
    deleteAlert('/admin/category/' + id, deleteSuccess);
}

function deleteSuccess(data) {
    console.log(tableRowIndex)
    if (data.data){
        alertFunction("Success", data.message, 'success');
        activeCategoryTable.row(tableRowIndex).remove().draw(false);
        tableRowIndex = -1;
    }else {
        alertFunction("Error", data.message, 'error');
    }
}

function mackActiveData(id){
    active('/admin/category/active', id, activeSuccess);
}

function activeSuccess(data){
    console.log(data);
    if (data.data){
        oneRow(data.data, activeCategoryTable);
        alertFunction("Success", data.message, 'success');
        $('#categoryModal').modal('hide');
    }else {
        alertFunction("Error", data.message, 'error');
    }
}
