/**
 *
 * All Ajax Call for 'GET' Request
 * @param url, for request
 * @param success, is a call back function
 *
 */
function get(url, success){
    $.ajax({
        url: url,
        type: 'get',
        contentType: false,
        processData: false,
        success: success,
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(jqXhr)
            console.log(textStatus)
            console.log(errorMessage)
            // alertFunction('Forbidden Error', )
        }
    });
}

/**
 *
 * All Ajax Call for 'POST' or 'PUT' Request
 * @param url, for request
 * @param formData, is the data that send with ajax call
 * @param success, is a call back function
 * @param contentType, for post request, if controller accept RequestBody as body
 * then only contentType should be pass as true otherwise no need to pass last parameter
 *
 */
function postOrPut(url, formData, success, contentType = false){
    let id;
    if (contentType){
        id = 0;
    }else {
        id = parseInt(formData.get('id'));
    }
    $.ajax({
        url: url,
        type: id === 0? 'post' : 'put',
        data: formData,
        contentType: contentType? 'application/json' : contentType,
        processData: false,
        success: success,
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(jqXhr)
            console.log(textStatus)
            console.log(errorMessage)
        }
    });
}

/**
 *
 * All Ajax Call for 'DELETE' Request
 * @param url, for request
 * @param success, is a call back function
 *
 */
function deletes(url, success){
    $.ajax({
        url: url,
        type: 'delete',
        contentType: false,
        processData: false,
        success: success,
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(jqXhr)
            console.log(textStatus)
            console.log(errorMessage)
        }
    });
}

/**
 *
 * All Ajax Call for 'POST' or 'PUT' Request
 * @param url, for request
 * @param id, which data make active
 * @param success, is a call back function
 *
 */
function active(url, id, success){
    let formData = new FormData();
    formData.set('id', id);
    $.ajax({
        url: url,
        type: 'put',
        data: formData,
        contentType: false,
        processData: false,
        success: success,
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(jqXhr)
            console.log(textStatus)
            console.log(errorMessage)
        }
    });
}