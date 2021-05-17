/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

checkLogin();
//$(document).ready(function () {
getBudgetYear();
//$('#clearFormModal').iziModal();

//Close side-menu
$('#nav-togglerx').click();

$("#sub_sector_id0").select2();
//});

function getBudgetYear() {
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#selectx').val()},
        dataType: "json",
        cache: false,
        async: false,
        success: budgetYearReturnValues,
        error: function () {
            toastr["error"]("No record selected!", "Budget Year Selection Failed!!!");
        }
    });
}
var currentyear = null;
function budgetYearReturnValues(data) {
    if (data.length > 0) {
        var resp = "";
        for (var i = 0; i < data.length; i++) {
            var obj = data[i];

            for (var key in obj) {
                var attrName = key;
                var attrValue = obj[key];
                if (attrValue === null || attrValue === 'null') {
                    attrValue = "";
                }
                if (attrName === "0") {
                    resp += "<option value='" + attrValue + "' selected>";
                }
                if (attrName === "1") {
                    resp += attrValue + "</option>";
                }
                if (attrName === "2") {
                    currentyear = attrValue;
                }
            }
        }
        $('#budget_year').html(resp);
    }
    getSubSectors();

}
;

function getSubSectors() {
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#select-by-name').val()},
        dataType: "json",
        cache: false,
        async: false,
        success: function (data) {
            mdalist = data;
            var resp = "";
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];

                for (var key in obj) {
                    var attrName = key;
                    var attrValue = obj[key];
                    attrValue = "" + attrValue;
                    if (attrName === "0") {
                        resp += "<option value='" + attrValue + "'>";
                    }
                    if (attrName === "1") {
                        resp += attrValue + "</option>";
                    }
                }
            }
            $('#sub_sector_id0').html("<option value='00'></option>" + resp);
            //getMenuYear();

        },
        error: function () {
            toastr["error"]("No record selected!", "Sub Sector Selection Failed!!!");
        }
    });
}

var mdalist = null;
function getMDAs(id) {
    id = $("#" + id).val();
    if (id === "00") {
        $('#datalist').html("");
    } else {
        $.ajax({
            type: "GET",
            url: $('#site-url').val() + "/MDACeilingServlet",
            data: {option: $('#select-by-name').val(), id: id},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                mdalist = data;
                //console.log(data);
                getMenuYear();

            },
            error: function () {
                toastr["error"]("No record selected!", "MDA Selection Failed!!!");
            }
        });
    }
}

//var contingencies = 0.0;
function getMenuYear() {
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#reset-menu').val()},
        dataType: "json",
        cache: false,
        async: false,
        success: menuYearReturnValues,
        error: function () {
            toastr["error"]("No record selected!", "Menu Year  Selection Failed!!!");
        }
    });
}
function menuYearReturnValues(data) {
    if (data.length > 0) {
        var resp = "";
        var flag = 0;
        for (var i = 0; i < data.length; i++) {
            var obj = data[i];

            for (var key in obj) {
                var attrName = key;
                var attrValue = obj[key];
                if (attrValue === null || attrValue === 'null') {
                    attrValue = "";
                }
                if (attrName === "0") {
                    if (flag === 0) {
                        flag = 1;
                        resp += "<li role='presentation' class='active'><a class='' href='#" + attrValue + "' aria-controls='" + attrValue + "' role='tab' data-toggle='tab' aria-expanded='true'>" + attrValue + "</a></li>";
                    } else {
                        resp += "<li role='presentation' class=''><a class='' href='#" + attrValue + "' aria-controls='" + attrValue + "' role='tab' data-toggle='tab' aria-expanded='true'>" + attrValue + "</a></li>";
                    }
                }
                //if (attrName === "1" && contingencies === 0.0) {
                //    contingencies = attrValue;
                //}
            }
        }
        $('#year_menu').html(resp);
    }
    getHeadList();

}
;

var headlist = null;
function getHeadList() {
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#select-a-record').val()},
        dataType: "json",
        cache: false,
        async: false,
        success: function (data) {
            headlist = data;
            createDataList();
        },
        error: function () {
            toastr["error"]("No record selected!", "Menu Year  Selection Failed!!!");
        }
    });
}

function createDataList() {
    
    $('#PageLoading').modal('show');
    var budget_year = document.getElementById("budget_year").value;
    var sub_sector_id = $("#sub_sector_id0").val();
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#select-all').val(), budget_year_id: budget_year, sub_sector_id: sub_sector_id},
        dataType: "json",
        cache: false,
        async: false,
        success: dataListReturnValues,
        error: function () {
            toastr["error"]("No record selected!", "Menu Year  Selection Failed!!!");
            $('#PageLoading').modal('hide');
        }
    });
}

var component_type_list = "";
function dataListReturnValues(data) {
    //alert(data);
    //console.log(data);
    //alert(data.length);
    if (data.length > 0) {
        var resp = "";
        var header = "";
        var activeflag = true;
        var baseYear = "";
        var currYear = "";
        var record_id = "";
        var columnlist = "";
        var header1 = 0;
        // compid = 0;
        //var stopheader = false;
        for (var i = 0; i <= data.length; i++) {
            var obj = data[i];
            for (var key in obj) {
                var attrName = key;
                var attrValue = obj[key];
                if (attrValue === null || attrValue === 'null') {
                    attrValue = "";
                }
                if (attrName === "0") {
                    //alert(baseYear +">"+ header);
                    if (attrValue > baseYear && header > "") {
                        resp += "</tr><tr bgcolor='#ffff66'><td><b>MDAs</b></td>";
                        for (var j = 0; j < headlist.length; j++) {
                            var obj2 = headlist[j];
                            for (var key2 in obj2) {
                                var attrName2 = key2;
                                var attrValue2 = obj2[key2];
                                if (attrValue2 === null || attrValue2 === 'null') {
                                    attrValue2 = "";
                                }
                                if (attrName2 === "1") {
                                    resp += "<td style='text-align: right'><b>" + attrValue2 + " (&#8358;)</b></td>";
                                }
                            }
                        }

                        resp += "</tr><tr>";
                        mdaid = "";
                        //console.log(mdalist);
                        for (var k = 0; k < mdalist.length; k++) {
                            var obj3 = mdalist[k];
                            for (var key3 in obj3) {
                                var attrName3 = key3;
                                var attrValue3 = obj3[key3];
                                if (attrValue3 === null || attrValue3 === 'null') {
                                    attrValue3 = "";
                                }
                                if (attrName3 === "0") {
                                    mdaid = parseInt(attrValue3).toString();
                                }
                                if (attrName3 === "1") {
                                    resp += "<td><b>" + attrValue3 + "</b></td>";
                                    reclist_arr = columnlist.split(",");
                                    for (var l = 0; l < reclist_arr.length; l++) {
                                        var recid = baseYear + "_" + reclist_arr[l] + "_" + mdaid;
                                        resp += "<td class='edit-column' align='right'><a onclick=$('#valb4edit').val(this.innerHTML); class='rowDataSd editable-control envelope-fig figx' href='#' id='" + recid + "' data-name='" + recid + "' data-type='text' data-pk='1' data-title='MDA Value'></a>&nbsp;</td>";
                                    }
                                }
                            }
                            resp += "</tr><tr>";
                        }

                        resp += "<tr><td><b>Total Distributed</b></td>";
                        reclist_arr = columnlist.split(",");
                        //console.log(columnlist);
                        for (var l = 0; l < reclist_arr.length; l++) {
                            var recid = baseYear + "_" + reclist_arr[l] + "_total";
                            if (l === 0) {
                                resp += "<td align='right'><input style='text-align: right' value='0.00' id='" + recid + "' type='text' readonly disabled size='15' /></td>";
                            } else {
                                resp += "<td align='right'><input style='text-align: right' value='0.00' id='" + recid + "' type='text' readonly disabled size='12' /></td>";
                            }
                        }
                        resp += "</tr></table></div>";
                        component_type_list = columnlist;
                        columnlist = "";
                        header1 = 0;
                    }
                    baseYear = attrValue;
                }
                if (attrName === "1") {
                    if (columnlist === "") {
                        columnlist += attrValue;
                    } else {
                        columnlist += "," + attrValue;
                    }
                    record_id = baseYear + "_" + attrValue;
                    //console.log("compid: "+compid+"       attrValue: "+attrValue);
                    //if(parseInt(attrValue) < compid){
                    //    stopheader = true;
                    //}else{
                    //    compid = parseInt(attrValue);
                    //}
                }
                if (attrName === "2") {
                    attrValue = attrValue.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                    if (baseYear > header) {
                        header = baseYear;
                        if (activeflag) {
                            activeflag = false;
                            currYear = baseYear;
                            //var heade_column = headlist.length - 3;
                            resp += "<div role='tabpanel' class='tab-pane active' id='" + baseYear + "'><table class='table table-clean table-striped' border='0'>";
                            //resp += "<tr><td></td><td colspan='3'></br></td>";
                            //resp += "<td colspan='"+heade_column+"'></td></tr>";
                            //resp += "<tr><td></td><td colspan='3'><input id='contingency_" + baseYear + "' type='hidden' /><input style='text-align: right' id='revenue_contingency_" + baseYear + "' type='hidden' value='" + attrValue + "' readonly disabled size='15' /></td>";
                            //resp += "<td colspan='"+heade_column+"'></td></tr>";
                            resp += "<input id='contingency0_" + baseYear + "' type='hidden' /><input id='revenue_contingency0_" + baseYear + "' type='hidden' />";
                            resp += "<input id='contingency_" + baseYear + "' type='hidden' /><input id='revenue_contingency_" + baseYear + "' type='hidden' />";
                            resp += "<tr><td><b>Total For Sector:&nbsp;</b></td>";
                        } else {
                            resp += "<div role='tabpanel' class='tab-pane' id='" + baseYear + "'><table class='table table-clean table-striped' border='0'>";
                            //resp += "<tr><td></td><td colspan='3'><input id='contingency0_" + baseYear + "' type='hidden' /></br><input id='revenue_contingency0_" + baseYear + "' type='hidden' /></td>";
                            //resp += "<td colspan='"+heade_column+"'></td></tr>";
                            //resp += "<tr><td></td><td colspan='3'><input id='contingency_" + baseYear + "' type='hidden' /><input style='text-align: right' id='revenue_contingency_" + baseYear + "' type='hidden' value='" + attrValue + "' readonly disabled size='15' /></td>";
                            //resp += "<td colspan='"+heade_column+"'></td></tr>";
                            resp += "<input id='contingency0_" + baseYear + "' type='hidden' /><input id='revenue_contingency0_" + baseYear + "' type='hidden' />";
                            resp += "<input id='contingency_" + baseYear + "' type='hidden' /><input id='revenue_contingency_" + baseYear + "' type='hidden' />";
                            resp += "<tr><td><b>Total For Sector:&nbsp;</b></td>";
                        }
                    }
                    var hidden_header = record_id + "_hidden";
                    if (header1 < 1 && i < data.length - 1) {
                        header1 = 1;
                        resp += "<td style='text-align: right'><input style='text-align: right' id='" + hidden_header + "' type='text' readonly disabled value='" + attrValue + "' size='15' />";
                        resp += "<input style='text-align: right' id='" + record_id + "_0' type='text' readonly disabled value='" + attrValue + "' size='15' /></td>";
                        //resp += "<a onclick=$('#valb4edit').val(this.innerHTML); class='rowDataSd editable-control envelope-fig figx' href='#' id='" + record_id + "_0' data-namerecid='" + record_id + "' data-type='text' data-pk='1' data-title='State Value'>" + attrValue + "</a>&nbsp;</td>";
                    } else {
                        resp += "<td style='text-align: right'><input style='text-align: right' id='" + hidden_header + "' type='text' readonly disabled value='" + attrValue + "' size='12' />";
                        resp += "<input style='text-align: right' id='" + record_id + "_0' type='text' readonly disabled value='" + attrValue + "' size='12' /></td>";
                    }
//console.log("A. hidden_header "+hidden_header+"    id "+record_id + "_0    attrValue "+attrValue+"    record_id "+record_id);
                }
            }
        }

        resp += "</tr><tr bgcolor='#ffff66'><td><b>MDAs</b></td>";
        for (var j = 0; j < headlist.length; j++) {
            var obj2 = headlist[j];
            for (var key2 in obj2) {
                var attrName2 = key2;
                var attrValue2 = obj2[key2];
                if (attrValue2 === null || attrValue2 === 'null') {
                    attrValue2 = "";
                }
                if (attrName2 === "1") {
                    resp += "<td style='text-align: right'><b>" + attrValue2 + " (&#8358;)</b></td>";
                }
            }
        }

        resp += "</tr><tr>";
        mdaid = "";
        for (var k = 0; k < mdalist.length; k++) {
            var obj3 = mdalist[k];
            for (var key3 in obj3) {
                var attrName3 = key3;
                var attrValue3 = obj3[key3];
                if (attrValue3 === null || attrValue3 === 'null') {
                    attrValue3 = "";
                }
                if (attrName3 === "0") {
                    mdaid = parseInt(attrValue3).toString();
                }
                if (attrName3 === "1") {
                    resp += "<td><b>" + attrValue3 + "</b></td>";
                    reclist_arr = columnlist.split(",");
                    for (var l = 0; l < reclist_arr.length; l++) {
                        var recid = baseYear + "_" + reclist_arr[l] + "_" + mdaid;
                        resp += "<td class='edit-column' align='right'><a onclick=$('#valb4edit').val(this.innerHTML); class='rowDataSd editable-control envelope-fig figx' href='#' id='" + recid + "' data-name='" + recid + "' data-type='text' data-pk='1' data-title='MDA Value'></a>&nbsp;</td>";
                    }
                }
            }
            resp += "</tr><tr>";
        }

        resp += "<tr><td><b>Total Distributed</b></td>";
        reclist_arr = columnlist.split(",");
        for (var l = 0; l < reclist_arr.length; l++) {
            var recid = baseYear + "_" + reclist_arr[l] + "_total";
            if (l === 0) {
                resp += "<td><input style='text-align: right' id='" + recid + "' value='0.00' type='text' readonly disabled size='15' /></td>";
            } else {
                resp += "<td><input style='text-align: right' id='" + recid + "' value='0.00' type='text' readonly disabled size='12' /></td>";
            }
        }
        resp += "</tr></table></div>";
        $('#datalist').html(resp);
        var arr_component_type_list = component_type_list.split(",");
        var revenue_less_contingency = parseFloat($('#' + currYear + '_' + arr_component_type_list[0] + "_0").val().replace(/,/g, "")); // - contingencies;
        //('#contingency0_'+currYear).val(contingencies.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        //$('#contingency_'+currYear).val(contingencies.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        $('#revenue_contingency0_' + currYear).val(revenue_less_contingency.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        $('#revenue_contingency_' + currYear).val(revenue_less_contingency.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        //initializeEditableControls();
        getEnvelopes();
    }
    //StopLoading();
}
;

/**
 * Method for initialising editable controls
 * 
 * @returns
 */
function initializeEditableControls() {
    $('.editable-control').each(function () {
        var id = $(this).attr('id');
        var title = $(this).attr('data-title');
        var name = $(this).attr('data-name');
        var pk = $(this).attr('data-pk');
        var type = $(this).attr('data-type');
        //var classs = $(this).attr('class');
        if (type === 'text' || type === 'number') {
            $('#' + id).editable({
                url: '/post',
                type: 'number',
                pk: pk,
                name: name,
                title: title,
                display: function (value) {
                    if (!value) {
                        if ($('#valb4edit').val() === "NA") {
                            $(this).html('0.00');
                        } else {
                            $(this).html($('#valb4edit').val());
                        }
                        return;
                    } else {
                        var grep = value.replace(/,/g, "");
                        if (isNaN(grep)) {
                            alert("The entered figure (" + value + ") is not a valid value");
                            value = null;
                            //document.getElementById("submit-final").disabled = true;
                            return;
                        } else {
                            //document.getElementById("submit-final").disabled = false;
                        }
                    }
                    var html = value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                    if (html.indexOf('.') === -1) {
                        html += '.00';
                    }
                    $(this).html(html);

                    var id_s = $(this).attr('id');
                    var id_arr = id_s.split("_");
                    if (id_arr[id_arr.length - 1] === "0") {
                        calculateheader(id_s);
                    } else {
                        calculateBody(id_s);
                    }
                }
            });
        }
    });
}

function calculateheader(id_s) {
    //console.log(id_s);
    sum = 0.0;
    var id_arr = id_s.split("_");
    var arr_component_type_list = component_type_list.split(",");
    for (var k = 0; k < arr_component_type_list.length; k++) {
        if (k > 0) {
            var id = id_arr[0] + "_" + arr_component_type_list[k] + "_0";
            var val = $("#" + id).html().replace(/,/g, "");
            if (val === null || val.length === 0) {
                $("#" + id).html("0.0");
            }
            var val = parseFloat($("#" + id).html().replace(/,/g, ""));

            var id_total = id_arr[0] + "_" + arr_component_type_list[k] + "_total";
            var val_total = $("#" + id_total).val().replace(/,/g, "");
            if (val_total === null || val_total.length === 0) {
                $("#" + id_total).val("0.0");
            }
            var val_total = parseFloat($("#" + id_total).val().replace(/,/g, ""));
            sum += val + val_total;
        }
    }
    //var contingency = parseFloat($("#contingency0_"+id_arr[0]).val().replace(/,/g, ""));
    //if(isNaN(contingency)){
    //    contingency = 0.0;
    //}
    var revenue_contingency = parseFloat($("#revenue_contingency0_" + id_arr[0]).val().replace(/,/g, ""));
    if (isNaN(revenue_contingency)) {
        revenue_contingency = parseFloat($('#' + id_arr[0] + '_1' + "_0").val().replace(/,/g, ""));
    }
    if ($('#valb4edit').val() === "NA") {
        //$("#valb4edit").val(0);
        revenue_contingency -= sum;
        if (revenue_contingency < 0) {
            //contingency += revenue_contingency;
            revenue_contingency = 0.0;
            $("#revenue_contingency_" + id_arr[0]).val("0.00");
            //$("#contingency_"+id_arr[0]).val(contingency.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        } else {
            $("#revenue_contingency_" + id_arr[0]).val(revenue_contingency.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }
        //$('#valb4edit').val('NA');
        return true;
    }
    var hidden_id = id_arr[0] + "_" + id_arr[1] + "_hidden";
    var old_val = parseFloat(parseFloat($("#valb4edit").val().replace(/,/g, "")).toFixed(2));
    var new_val = parseFloat(parseFloat($("#" + id_s).html().replace(/,/g, "")).toFixed(2));
    var hid_val = parseFloat(parseFloat($("#" + hidden_id).val().replace(/,/g, "")).toFixed(2));
    if (new_val > old_val) {
        var revenue_contingency1 = parseFloat(parseFloat($("#revenue_contingency_" + id_arr[0]).val().replace(/,/g, "")).toFixed(2));
        ;
        var diff = parseFloat((new_val - old_val).toFixed(2));
        new_val = hid_val + (new_val - old_val);
        $("#" + hidden_id).val(new_val);
        if ((revenue_contingency1 - diff) < 0) {
            /*var contingency1 = parseFloat(parseFloat($("#contingency_"+id_arr[0]).val().replace(/,/g, "")).toFixed(2));
             if(id_arr[0] === currentyear.toString()){
             if(confirm("Insufficient Amount in Revenue\n\nUse Contingency?")){
             $("#revenue_contingency_"+id_arr[0]).val("0.00");
             contingency1 -= parseFloat((diff - revenue_contingency1).toFixed(2));
             if(contingency1<0){
             alert("Insufficient Amount in Contingency!!!\n\nThe Amount you entered is more than the Contingency balance");
             $("#" + id_s).html($("#valb4edit").val().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
             return true;
             }else{
             $("#contingency_"+id_arr[0]).val(contingency1.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
             if($('#valb4edit').val()!=="NA"){
             saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
             $('#valb4edit').val('NA');
             }
             }
             }
             }else{*/
            if ((revenue_contingency1 - diff) < 0) {
                alert("Insufficient Amount in Revenue!!!\n\nThe Amount you entered is more than the Revenue balance");
                $("#" + id_s).html($("#valb4edit").val().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                return true;
            } else {
                revenue_contingency1 -= diff;
                $("#revenue_contingency_" + id_arr[0]).val(revenue_contingency1.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                if ($('#valb4edit').val() !== "NA") {
                    saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
                    $('#valb4edit').val('NA');
                }
            }
            //}
        } else {
            revenue_contingency1 -= diff;
            $("#revenue_contingency_" + id_arr[0]).val(revenue_contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
            if ($('#valb4edit').val() !== "NA") {
                saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
                $('#valb4edit').val('NA');
            }
        }
    } else {
        var diff = (old_val - new_val);
        new_val = hid_val + (new_val - old_val);
        $("#" + hidden_id).val(new_val);
        var revenue_contingency1 = parseFloat(parseFloat($("#revenue_contingency_" + id_arr[0]).val().replace(/,/g, "")).toFixed(2));
        /*if(id_arr[0] === currentyear.toString()){
         var contingency1 = parseFloat(parseFloat($("#contingency_"+id_arr[0]).val().replace(/,/g, "")).toFixed(2));
         if((diff + contingency1) <= contingency){
         contingency1 += diff;
         }else if((diff + contingency1) > contingency){
         diff -= (contingency - contingency1);
         contingency1 = contingency;
         revenue_contingency1 += diff;
         }else{
         revenue_contingency1 += diff;
         }
         if($('#valb4edit').val()!=="NA"){
         saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
         $('#valb4edit').val('NA');
         }
         $("#contingency_"+id_arr[0]).val(contingency1.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
         $("#revenue_contingency_"+id_arr[0]).val(revenue_contingency1.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
         }else{*/
        revenue_contingency1 += diff;
        if ($('#valb4edit').val() !== "NA") {
            saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
            $('#valb4edit').val('NA');
        }
        $("#revenue_contingency_" + id_arr[0]).val(revenue_contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        //}
    }
}
function calculateBody(id_s) {
    sum = 0.0;
    var id_arr = id_s.split("_");
    for (var k = 0; k < mdalist.length; k++) {
        var obj = mdalist[k];
        var sectorid = "";
        for (var key in obj) {
            var attrName = key;
            var attrValue = obj[key];
            if (attrValue === null || attrValue === 'null') {
                attrValue = "";
            }
            if (attrName === "0") {
                sectorid = parseInt(attrValue).toString();
            }
        }

        var id = id_arr[0] + "_" + id_arr[1] + "_" + sectorid;
        var val = parseFloat(parseFloat($("#" + id).html().replace(/,/g, "")).toFixed(2));
        if (!isNaN(val)) {
            sum += val;
        }
//console.log("id: "+id+"    val: "+val+"    sum: "+sum);
    }

    var header_id_hidden = id_arr[0] + "_" + id_arr[1] + "_hidden";
    var header_val = $("#" + header_id_hidden).val();
    var header_id = id_arr[0] + "_" + id_arr[1] + "_0";
    var sub_header_id = id_arr[0] + "_" + id_arr[1] + "_total";
    header_val = parseFloat(parseFloat(header_val.replace(/,/g, "")).toFixed(2));
    //if (Math.abs(header_val - sum) < 1) {
//console.log("header_id: "+header_id+"    header_val: "+header_val+"    sum: "+sum);
    if (sum > header_val && Math.abs(sum - header_val) >= 1) {
        //console.log("header_id: " + header_id + "    header_val: " + header_val + "    sum: " + sum)
        toastr["error"]("The Amount entered throws envelope into minus!", "Amount Greater Than Envelope!!!");
        html = $('#valb4edit').val();
        if (html === null || html === "" || isNaN(html.replace(/,/g, ""))) {
            html = '0.00';
        }
        $("#" + id_s).html(html);
        return true;
    }

    //if ((header_val - sum) < 1) {
    //    sum = header_val;
    //}

    if (header_val > 0) {
        header_val = parseFloat((header_val - sum).toFixed(2));
    } else {
        header_val = 0.0;
    }
//console.log("header_id: "+header_id+"    header_val: "+header_val+"    sum: "+sum);

    //if (parseInt(header_val) < 0) {
    var html = "";
    if (isNaN(header_val)) {
        html = "0.00";
    } else {
        html = header_val.toFixed(2).toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    if (html.indexOf('.') === -1) {
        html += '.00';
    }
    $("#" + header_id).val(html);

    var html = sum.toFixed(2).toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    if (html.indexOf('.') === -1) {
        html += '.00';
    }
    $("#" + sub_header_id).val(html);

    if ($('#valb4edit').val() !== "NA") {
        var val = parseFloat($("#" + id_s).html().replace(/,/g, ""));
        saveEnvelope(id_s, val);
        $('#valb4edit').val('NA');
    }
}

function saveEnvelope(id, val, type) {
    var id_arr = id.split("_");
    var mda_id = id_arr[2];
    var budget_type_component_id = id_arr[1];
    var total_amount = val;
    var budget_year_id = id_arr[0];
    var sub_sector_id = $("#sub_sector_id0").val();
    if (type === "state") {
        mda_id = "0";
    }

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#insertx').val(), sub_sector_id: sub_sector_id, mda_id: mda_id, mda_weight: "0.0", budget_type_component_id: budget_type_component_id, total_amount: total_amount, budget_year_id: budget_year_id},
        dataType: "json",
        cache: false,
        async: false,
        success: function () {
            toastr["success"]("Record saved Successfully!", "Record saved Successfully!!!");
        },
        error: function () {
            toastr["error"]("No record saved!", "No record saved!!!");
        }
    });
};

function getEnvelopes() {
    $('#PageLoading').modal('show');
    var budget_year_id = document.getElementById("budget_year").value;
    var sub_sector_id = $("#sub_sector_id0").val();
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#select-by-id').val(), budget_year_id: budget_year_id, sub_sector_id: sub_sector_id},
        dataType: "json",
        cache: false,
        async: false,
        success: getEnvelopeReturnValues,
        error: function () {
            toastr["error"]("No record fetched!", "No Record Fetched!!!");
            $('#PageLoading').modal('hide');
        }
    });
}
function getEnvelopeReturnValues(data) {
    //console.log(data);

    if (data.length > 0) {
        var id = "";
        for (var i = 0; i < data.length; i++) {
            var obj = data[i];
            for (var key in obj) {
                var attrName = key;
                var attrValue = obj[key];
                if (attrValue === null || attrValue === 'null') {
                    attrValue = "";
                }
                if (attrName === "0") {
                    id = attrValue;
                }
                if (attrName === "1") {
                    $("#" + id).html(attrValue.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    var id_arr = id.split("_");
                    if (id_arr[id_arr.length - 1] === "0") {
                        calculateheader(id);
                    } else {
                        calculateBody(id);
                    }
                }
            }
        }
    }
    initializeEditableControls();
    $('#PageLoading').modal('hide');
}


function distributeEnvelopes() {
    $('#PageLoading').modal('show');
    setTimeout(function(){ distributeEnvelopes2(); }, 3000);
}
function distributeEnvelopes2() {
    $('#PageLoading').modal('show');
    var budget_year_id = document.getElementById("budget_year").value;
    var sub_sector_id = $("#sub_sector_id0").val();
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#distribute-envelopes').val(), budget_year_id: budget_year_id, sub_sector_id: sub_sector_id},
        dataType: "text",
        cache: false,
        async: false,
        success: function () {
            toastr["success"]("All Envelopes are successfully distributed!", "Envelopes Distributed!!!");
            getMDAs("sub_sector_id0");
            //getEnvelopes();
            $('#PageLoading').modal('hide');
        },
        error: function () {
            toastr["error"]("Attempt to distribute Envelopes failed!", "Envelopes Distribution Failed!!!");
            $('#PageLoading').modal('hide');
        }
    });
}

/**
 * Method for hiding approvals
 * 
 * @returns
 */
//            function hideApprovals() {
//                //disable things
//                $("#reject-version").hide();
//                $("#approve-version").hide();
//            }

/**
 * Method for showing approvals
 * 
 * @returns
 */
//            function showApprovals() {
//                //enable things
//                $("#reject-version").show();
//                $("#approve-version").show();
//            }

/**
 * Method for hiding submission and save buttons
 * 
 * @returns
 */
//            function hideSubmission() {
//                //disable things
//                $("#submit-final").hide();
//                $("#save-version").hide();
//
//                $("#mybf-version").attr('disabled', true);
//            }

//            function checkWorkflowStatus() {
//                var requestTypeID = parseInt($('#req-type-id').val());
//
//                data = {};
//                data['option'] = $('#check-workflowx').val();
//                data['requestTypeID'] = requestTypeID;
//                data['roles'] = $('#roles-idx').val();
//                data['mdaID'] = $('#mda-idx').val();
//                data['userID'] = $('#v5er-idx').val();
//
//                $.ajax({
//                    type: "POST",
//                    url: $('#site-url').val() + "/RequestApprovalsServlet",
//                    data: data,
//                    dataType: "json",
//                    cache: false,
//                    success: function (response) {
//                        if (response.length > 0) {
//                            $.each(response, function (index, element) {
//                                currentWorkflowStatus = element[0];
//
//                                if (currentWorkflowStatus != -1)
//                                    $('#request-det-id').val(element[1]);
//                            });
//
//                            if (currentWorkflowStatus == -1) {
//                                toastr["error"]("You are not authorized to view this page, please contact admin.", "Failure");
//
//                                gotoLink('/codem00017');
//                            } else if (currentWorkflowStatus == 0 ||
//                                    currentWorkflowStatus == 3) {
//                                hideSubmission();
//                                hideApprovals();
//                            } else if (currentWorkflowStatus == 1) {
//                                hideSubmission();
//                                showApprovals();
//                            } else if (currentWorkflowStatus == 2) {
//                                hideApprovals();
//
//                                canUpdate = true;
//                            } else if (currentWorkflowStatus == 4 ||
//                                    currentWorkflowStatus == 5 ||
//                                    currentWorkflowStatus == 6) {
//                                document.getElementById("submit-final").disabled = true;
//                                document.getElementById("save-version").disabled = true;
//
//                                hideSubmission();
//                                hideApprovals();
//
//                                canUpdate = false;
//
//                                //show generate report
//                                if (currentWorkflowStatus == 4)
//                                    $("#generate-report").show();
//                                else if (currentWorkflowStatus == 5 ||
//                                        currentWorkflowStatus == 6)
//                                    $("#view-report").show();
//                            }
//                        }
//                    },
//                    error: function () {
//                    }
//                });
//            }
function clearForms(arg) {
    $('#clearFormModal').modal('show');
    //$('#clearFormModal').iziModal('open');
    //window.deleteId = arg;
}

function cancelClearForms() {
    $('#clearFormModal').modal('hide');
    //$('#clearFormModal').iziModal('close');
    //window.deleteId = 0;
}

function finishClearForms() {
    $('#clearFormModal').modal('hide');
    $('#PageLoading').modal('show');
    setTimeout(function(){ finishClearForms2(); }, 3000);
}
function finishClearForms2() {
    
    var budget_year_id = document.getElementById("budget_year").value;
    var sub_sector_id = $("#sub_sector_id0").val();
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MDACeilingServlet",
        data: {option: $('#delete-by-id').val(), budget_year_id: budget_year_id, sub_sector_id: sub_sector_id},
        dataType: "text",
        cache: false,
        async: false,
        success: function (data) {
            if (data.indexOf($('#deletedx').val()) !== -1) {
                $('#clearFormModal').modal('hide');
                //$('#clearFormModal').iziModal('close');
                toastr["success"]("All form cleared Successfull!", "Forms Clearing Successfull");
                getMDAs("sub_sector_id0");
            }
            $('#PageLoading').modal('hide');
        },
        error: function () {
            $('#clearFormModal').modal('hide');
            //$('#clearFormModal').iziModal('close');
            toastr["error"]("Clearing of forms Failed!", "Forms Clearing Failed!");
            $('#PageLoading').modal('hide');
        }
    });
}

