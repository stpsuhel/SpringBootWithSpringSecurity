/**
 * Clicked row index number
 * @type {number}
 */
let tableRowIndex = -1;

/**
 *
 * Data Table Row click function
 * @param tableId, id of the datatable
 * @param table, instance of the table
 * @param view, is the call back function
 *
 */
function rowClick(tableId, table, view){
    $('#' + tableId + ' tbody').on( 'click', 'td', function () {
        const id = table.row(this).data().id;
        tableRowIndex = table.row(this).index();
        const index = table.cell(this).index().column;
        const title = table.column(index).header().textContent;

        if (title !== 'Action'){
            view(id);
        }
    });
}

/**
 *
 * Html for last column of table
 * @param id, id of that row data
 * @param status is data active or not
 * @returns {string}, html to show column
 *
 */
function actionColumn(id, status){
    let editButton = '';
    if (status === 1){
        editButton = '<button onclick="editItem(' + id + ')" type="button" data-toggle="tooltip" title="" ' +
                        'class="btn btn-link btn-primary btn-lg" data-original-title="Edit"><i class="fa fa-edit"></i></button>';
    }
    return '<div class="form-button-action">' + editButton + '<button onclick="deleteItem(' + id + ')" type="button" data-toggle="tooltip" ' +
            'title="" class="btn btn-link btn-danger" data-original-title="Remove"><i class="fa fa-times"></i></button></div>';
}