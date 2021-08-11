
/**
 *
 * Show alert dialog
 * @param title, Alert dialog title
 * @param message, Alert dialog message
 * @param icon, Success, error icon
 *
 */
function alertFunction(title, message, icon) {
    swal({
        title: title,
        text: message,
        icon: icon,
        button: false,
        timer: 1500
    });
}

/**
 *
 * Alert dialog for delete wearing
 * @param url, url for delete data
 * @param success, is a call back function
 *
 */
function deleteAlert(url, success){
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this Data!",
        icon: "warning",
        buttons: ['NO', 'DELETE'],
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            deleteData(url, success);
        }
    });
}

function deleteData(url, success) {
    deletes(url, success);
}

function dataNotFound(){
    alertFunction("Error", 'Data Not Found!', 'error');
}