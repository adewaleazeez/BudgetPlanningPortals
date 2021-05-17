toastr.options = {
    "closeButton": true,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

//check login status, access rights and get menus
checkLogin();

$(document).ready(function () {
    //Get LGAs
    getObjects();

    document.getElementById('erasable').innerHTML = "";
});

function createObj() {
    ShowLoading();

    //Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("obj-name").value;
    var error = "";
    if (data['name'] === "") {
        error += "<br>Name must not be blank<br>";
    }
    if (error !== "") {
        toastr["error"](error, "Please Correct The Following Error(s)!!!");
        StopLoading();
        return true;
    }
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/LGAsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (response.indexOf($('#insertedx').val()) !== -1) {
                getObjects();

                ReturnToList();

                // Notification
                toastr["success"]("LGA created successfully!", "Created");
            } else
                toastr["error"]("LGA creation failed, record exists!", "Failure");

            StopLoading();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            StopLoading();
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

function editObj() {
    ShowLoading();

    //Update request type
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = document.getElementById("obj-id").value;
    data['name'] = document.getElementById("obj-ename").value;
    var error = "";
    if (data['name'] === "") {
        error += "<br>Name must not be blank<br>";
    }
    if (error !== "") {
        toastr["error"](error, "Please Correct The Following Error(s)!!!");
        StopLoading();
        return true;
    }
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/LGAsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (response.indexOf($('#updatedx').val()) !== -1) {
                CloseEdit();

                getObjects();

                // Notification
                toastr["success"]("LGA updated successfully!", "Updated");
            } else
                toastr["error"]("Item edit failed!", "Failure");

            StopLoading();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            StopLoading();
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

function getObjects() {
    data = {};
    data['option'] = $('#select-all').val();

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/LGAsServlet",
        data: data,
        dataType: "json",
        success: function (response) {
            var table = "<table id='main-tablex' class='table table-clean'>" +
                    "<thead>" +
                    "<tr>" +
                    "<th><b>S/No</b></th>" +
                    "<th><b>Name</b></th>" +
                    "<th><b>Date Created</b></th>" +
                    "<th><b>Action</b></th>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody>";

            if (response.length > 0) {
                var count=0;
                $.each(response, function (index, element) {
                    var buttons = '<button onclick="showEditObj(' + element[0] + ');" type="button" class="btn btn-sm btn-success btn-labeled">' +
                            '<span class="btn-label btn-label-left"><i class="fa fa-edit"></i></span>Edit</button>&nbsp;&nbsp;';

                    buttons += '<button onclick="deleteObj(' + element[0] + ');" type="button" class="btn btn-sm btn-danger btn-labeled">' +
                            '<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete</button>';

                    table += "<tr><td>" + (++count) +
                            "</td><td>" + element[1] +
                            "</td><td>" + element[2] +
                            "</td><td>" + buttons + "</td></tr>";
                });
            } else
                table += "<tr colspan='5'><td>No LGA created yet.</td></tr>";

            table += "</tbody></table>";

            document.getElementById("main-table").innerHTML = table;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditObj(requestTypeID) {
    $('#obj-id').val(requestTypeID);

    data = {};
    data['option'] = $('#selectx').val();
    data['id'] = requestTypeID;

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/LGAsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function (response) {
            $.each(response, function (index, element) {
                document.getElementById("obj-ename").value = element[1];
            });

            EditItem();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

function deleteObj(objID) {
    if (confirm("Are you sure you want to delete this?")) {
        ShowLoading();

        data = {};
        data['option'] = $('#deletex').val();
        data['id'] = objID;

        $.ajax({
            type: "POST",
            url: $('#site-url').val() + "/LGAsServlet",
            data: data,
            dataType: "text",
            cache: false,
            success: function (response) {
                if (response.indexOf($('#deletedx').val()) !== -1) {
                    getObjects();

                    // Notification
                    toastr["success"]("LGA deleted successfully!", "Deleted");
                } else {
                    // Notification
                    toastr["error"]("Item deletion failed!", "Failure");
                }

                StopLoading();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                StopLoading();
                //console.log(textStatus + " " + errorThrown);
            }
        });
    } else
        return false;
}

function checkEnter(e) { //e is event object passed from function invocation
    var characterCode; //literal character code will be stored in this variable

    if (e && e.which) { //if which property of event object is supported (NN4)
        e = e;
        characterCode = e.which //character code is contained in NN4's which property
    } else {
        e = event;
        characterCode = e.keyCode; //character code is contained in IE's keyCode property
    }

    if (characterCode === 13) { //if generated character code is equal to ascii 13 (if enter key)
        //checkLogin();
        return false;
    } else {
        return true;
    }
}