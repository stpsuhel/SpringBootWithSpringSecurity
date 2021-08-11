
/**
 *
 * Error message for input field
 * @param errors, list of errors for each input field\
 *
 */
function showError(errors){
    errors.forEach(function (error) {
        $('#' + error.field + 'ErrorDiv').addClass('has-error');
        let selector = $('#' + error.field + 'Error');
        selector.empty();
        selector.append('<small class="px-2 form-text text-danger text-capitalize text-muted">' +
            error.defaultMessage + '*</small>');
    });
}

/**
 *
 * Remove all error message
 *
 */
function removeError() {
    $('.errors').empty();
    $('.inputError').removeClass('has-error');
}