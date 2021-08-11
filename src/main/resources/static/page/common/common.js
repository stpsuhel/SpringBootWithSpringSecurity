
/**
 *
 * show form
 * @param id, form id
 *
 */
function show(id){
    $('#' + id + '').show();
}

/**
 *
 * hide form
 * @param id, form id
 *
 */
function hide(id){
    $('#' + id + '').hide();
}

/**
 *
 * format date time as needed
 * @param date, which needed to format
 * @param format, convert this format
 * @returns {*}, return date to specified format
 *
 */
function dateFormat(date, format) {
    if (format === 'YYYY-MM-DD hh:mm A'){
        return moment(date).format(format);
    }
    return moment(date, 'YYYY-MM-DD hh:mm A').format(format);
}

/**
 *
 * clear all form input & error message
 * @param id, form id
 *
 */
function clearForm(id){
    $('#' + id).trigger('reset');
    removeError();
}

// $(".select2-dropdown").select2({
//     closeOnSelect: true,
//     placeholder: "Grade",
//     width: '100%',
//     dropdownAutoWidth: true
// });

