            checkLogin();
            CancelManageHeader2();
          $('#deleteModal').iziModal();
          $('#manageHeader1Modal').iziModal();
          $('.is-modal').iziModal();
          
          $('.is-hidden').hide();
            getGeographicSegmentHeader1();
            
            function getGeographicSegments(){
             
                
                let parent = Window.selHeader3;
                if(!parent){getGeographicSegmentHeader1(); return;} 
                   ShowLoading();
                $.ajax({
                    type: "GET",
                    url:$('#site-url').val() + "/GeographicSegmentServlet",
                    data: {option: $('#select-all').val(),entity : "GeographicSegment",parent : parent},
                    dataType: "json",
                    success: geographicSegmentReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Geographic Segment Select Failed!", "No record selected!");}
                });
            }
            
            function geographicSegmentReturnValues(data){
            
            StopLoading();
//                 if(data.length>0){
                    var resp ="<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td></td> </tr>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var recId = 0;
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = " " + attrValue;
                            if(attrName === "0"){
                                resp += "<tr >";
                                recId = attrValue;
                            }
                            if(attrName === "1"){
                                resp += "<td class='line-height-30 w-25'><small><b>"+obj[2]+"</b></small></td>";
                            }
                            if(attrName === "2"){
                                resp += "<td class='line-height-30 w-25'><small><b>"+obj[1]+"</b></small></td>";
                                resp += "<td class='line-height-30 w-50'><button type='button' onclick='EditItem("+recId+")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                                resp += " <button type='button' class='btn btn-danger' onclick='DeleteItem("+recId+")'><i class='fa fa-remove'></i>Delete</button></td></tr>";
                                resp += "";
                            }
                            
                        }
                    }
                    resp += "</tbody></table>";
                    document.getElementById("active").innerHTML=resp;
                    
//                }
                
            }
            
            function getGeographicSegmentHeader1(){
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url:$('#site-url').val() + "/GeographicSegmentServlet",
                    data: {option: $('#select-all').val(), entity : "GeographicSegmentHeader1"},
                    dataType: "json",
                    success: geographicSegmentHeader1ReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Geographic Segment Select Failed!", "No record selected!");}
                });
            }
            
            function geographicSegmentHeader1ReturnValues(data){
            
            StopLoading();
                
                    var table_resp ="<table class='table table-clean table-striped' ><tbody><tr><td>Name</td><td>Code</td><td></td> </tr>";
                    var resp ="";
                    var value = {};
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "1"){
                                value.name = attrValue;
                            }
                            if(attrName === "2"){
                                value.code = attrValue;
                            }
                            if(attrName === "0"){
                                value.id = attrValue;
                            }
                        }
                        table_resp += "<tr> <td class='line-height-30 w-25'><small><b>"+value.name+"</b></small></td>";
                        table_resp += "<td class='line-height-30 w-25'><small><b>"+value.code+"</b></small></td>";
                        table_resp += "<td class='line-height-30 w-50'><button type='button' onclick='EditHeader1("+value.id+")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                        table_resp += " <button type='button' class='btn btn-danger' onclick='DeleteHeader1("+value.id+")'><i class='fa fa-remove'></i>Delete</button></td>";
                        table_resp += "</tr>";
                        
                        resp += "<option value='"+value.id+"'>"+value.name+"</option>";
                    }
                    table_resp += "</tbody></table>";
                    resp += "</select>";
                    $('#selFSHeader1').html("<select class='js-states form-control' onchange='setHeader1(this.value);getGeographicSegmentHeader2();' id='selFSHeader1_set'><option value='0'  class=''>Please select Header 1</option>"+resp);
                    document.getElementById("FSHeader1_table").innerHTML=table_resp;
                    $('#Header1_addH2').html("<select class='js-states form-control' onchange='setHeader1(this.value);getGeographicSegmentHeader2();' id='selHeader1_addH2'><option value='0' class=''>Please select Header 1</option>"+resp);
                    $('#addSegmentH1').html("<select class='js-states form-control' id='sel_addSegmentH1' disabled><option class=''>Please select Header 1</option>"+resp);
//                  $('#selMda3').html("<select class='js-states form-control' id='selMda_s' onchange='getDepartments();setMdas(this)'><option class=''>Please select MDA</option>"+resp);
                StopLoading();
                
            }
            
            function setHeader1(value){
                Window.selHeader1 = value;
                $('#sel_addSegmentH1')[0].value  = value;
//                $('#selHeader1_addH2')[0].value  = value;
                $('#selFSHeader1_set')[0].value  = value;
                 
            }
            
            function setHeader2(value){
                Window.selHeader2 = value;
                
                $('#sel_addSegmentH2')[0].value  = value;
                
            }
            
            function setHeader3(value){
                Window.selHeader3 = value;
                $('#sel_addSegmentH3')[0].value  = value;
            }
            
            
            
            function getGeographicSegmentHeader2(){
                ShowLoading();
                parent_id = Window.selHeader1;
                $.ajax({
                    type: "GET",
                    url:$('#site-url').val() + "/GeographicSegmentServlet",
                    data: {option: $('#select-all').val(), entity : "GeographicSegmentHeader2",parent : parent_id},
                    dataType: "json",
                    success: geographicSegmentHeader2ReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Geographic Segment Header Select Failed!", "No record selected!");}
                });
            }
            
            function geographicSegmentHeader2ReturnValues(data){
            StopLoading();
            var table_resp_h2 ="<table class='table table-clean table-striped' ><tbody><tr><td>Name</td><td>Code</td><td></td> </tr>";
                    var resp ="";
                    var value = {};
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "2"){
                                value.code =  attrValue;
                            }
                            if(attrName === "1"){
                                value.name = attrValue;
                            }
                            if(attrName === "0"){
                                value.id = attrValue;
                            }
                        }
                        resp += "<option value='"+value.id+"'>"+value.name+"</option>";
                        table_resp_h2 += "<tr> <td class='line-height-30 w-25'><small><b>"+value.name+"</b></small></td>";
                        table_resp_h2 += "<td class='line-height-30 w-25'><small><b>"+value.code+"</b></small></td>";
                        table_resp_h2 += "<td class='line-height-30 w-50'><button type='button' onclick='EditHeader2("+value.id+")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                        table_resp_h2 += " <button type='button' class='btn btn-danger' onclick='DeleteHeader2("+value.id+")'><i class='fa fa-remove'></i>Delete</button></td>";
                        table_resp_h2 += "</tr>";
                    }
                    resp += "</select>";
                    table_resp_h2 += "</tbody></table>";
                    $('#selFSHeader2').html("<select class='js-states form-control' onchange='setHeader2(this.value);getGeographicSegmentHeader3();' id='selFSHeader2_set'><option value='0'  class=''>Please select Header 2</option>"+resp);
                    document.getElementById("FSHeader2_table").innerHTML=table_resp_h2;
                    $('#addSegmentH2').html("<select class='js-states form-control' id='sel_addSegmentH2' disabled ><option value='0' class=''>Please select Header 2</option>"+resp);
//                    $('#selMda4').html("<select class='js-states form-control' id='selMda_HODe' readonly disabled><option class=''>Please select MDA</option>"+resp);
//                    $('#selMda3').html("<select class='js-states form-control' id='selMda_s' onchange='getDepartments();setMdas(this)'><option class=''>Please select MDA</option>"+resp);
                $('.H2SelContainer').removeClass('hidden');
                StopLoading();
                
            }
            
            function getGeographicSegmentHeader3(){
                ShowLoading();
                parent_id = Window.selHeader2;
                $.ajax({
                    type: "GET",
                    url:$('#site-url').val() + "/GeographicSegmentServlet",
                    data: {option: $('#select-all').val(), entity : "GeographicSegmentHeader3",parent : parent_id},
                    dataType: "json",
                    success: geographicSegmentHeader3ReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Geographic Segment Header Select Failed!", "No record selected!");}
                });
            }
            
            function geographicSegmentHeader3ReturnValues(data){
            StopLoading();
            var table_resp_h3 ="<table class='table table-clean table-striped' ><tbody><tr><td>Name</td><td>Code</td><td></td> </tr>";
                    var resp ="";
                    var value = {};
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "2"){
                                value.code =  attrValue;
                            }
                            if(attrName === "1"){
                                value.name = attrValue;
                            }
                            if(attrName === "0"){
                                value.id = attrValue;
                            }
                        }
                        resp += "<option value='"+value.id+"'>"+value.name+"</option>";
                        table_resp_h3 += "<tr> <td class='line-height-30 w-35'><b>"+value.name+"</b></td>";
                        table_resp_h3 += "<td class='line-height-30 w-35'><small><b>"+value.code+"</b></small></td>";
                        table_resp_h3 += "<td class='line-height-30 w-30'><button type='button' onclick='EditHeader3("+value.id+")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                        table_resp_h3 += " <button type='button' class='btn btn-danger' onclick='DeleteHeader3("+value.id+")'><i class='fa fa-remove'></i>Delete</button></td>";
                        table_resp_h3 += "</tr>";
                    }
                    resp += "</select>";
                    table_resp_h3 += "</tbody></table>";
                    
                    $('#selFSHeader3').html("<select class='js-states form-control' onchange='setHeader3(this.value);getGeographicSegments();' id='selFSHeader3_set'><option value='0' class=''>Please select Header 3</option>"+resp);
                    document.getElementById("FSHeader3_table").innerHTML=table_resp_h3;
                    $('#addSegmentH3').html("<select class='js-states form-control' id='sel_addSegmentH3' disabled ><option value='0' class=''>Please select Header 3</option>"+resp);
//                    $('#selMda4').html("<select class='js-states form-control' id='selMda_HODe' readonly disabled><option class=''>Please select MDA</option>"+resp);
//                    $('#selMda3').html("<select class='js-states form-control' id='selMda_s' onchange='getDepartments();setMdas(this)'><option class=''>Please select MDA</option>"+resp);
                $('.H3SelContainer').removeClass('hidden');
                StopLoading();
                
            }
            
            function saveGeographicSegment(){
                let name = document.getElementById("name_addSegment").value;
                let code = document.getElementById("code_addSegment").value;
                let parent = Window.selHeader3;
                
                if(!parent) {toastr["error"]("Please select Header parent!", "Field cannot be empty!"); return;};
                if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
                $.ajax({
                    type: "GET",
                    url:$('#site-url').val() + "/GeographicSegmentServlet",
                    data: {option: $('#insertx').val() , name: name,parent: parent,code: code, entity : "GeographicSegment"},
                    dataType: "json",
                    success: geographicSegmentInsertReturnValues,
                    error: function(){ toastr["error"]("Geographic Segment Insert Failed!", "No record inserted!");}
                });
                //
            }
            
            function geographicSegmentInsertReturnValues(data){
                        document.getElementById("name_addSegment").value = '';
                        document.getElementById("code_addSegment").value = '';
                       if(data.indexOf($('#insertedx').val()) !== -1){
                           
                           toastr["success"]("Geographic Segment Insert Successful!", "New record successfully inserted!");
                           
                           getGeographicSegments();
                           ReturnToList();
                       }else{
                           toastr["error"]("Geographic Segment with that name already exist", "Duplicate Entry!");
                       }
                   }

            function EditItem(arg){
                createCookie("formtype", "editGeographicSegment");
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#selectx').val(), id: arg,entity : "GeographicSegment"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 if(data !== null || data !== "null"){ 
                     data = data[0];
                      document.getElementById("name2").value = data[1];
                      document.getElementById("code2").value = data[2];
                      document.getElementById("updateid").value = data[0];
                     }
                     toastr["success"]("Geographic Segment successfully fetched!", "Geographic Segment Successfull!!!");
                     $('#MainSection').hide();
                     $('#EditSection').fadeIn();
                     $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li onclick='closeEdit()'> Geographic Segment</li><li class='active'>Edit Geographic Segment</li>");
                 }
             ,
             error: function(e,b,c){
                 toastr["error"]("Record with id " + arg + " is not found!", "Geographic Segment Select Failed!!!");
             }
         });                
            }

            function FinishEdit(){
            let name = document.getElementById("name2").value;
            let code = document.getElementById("code2").value;
            let id = document.getElementById("updateid").value;
            
            if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
            if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#updatex').val() , name: name,code:code,id:id,entity : "GeographicSegment"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 if(data === $('#updatedx').val()){
                 toastr["success"]("Geographic Segment update Successfull!", "Update Successfull");
                 cancelEdit();
             }
             if(data === $('#existsx').val()){
             toastr["error"]("Geographic Segment with that name already exists", "Update Failed");
             }},
             error: function(a,b,c){ 
             toastr["error"]("Geographic Segment Update Failed!", "No record updated!");}
         });
            }

            function cancelEdit(){
             $('#EditSection').hide();
             $('#MainSection').fadeIn();
             getGeographicSegments();
             document.getElementById("name2").value = '';
             document.getElementById("updateid").value = '';

            }

            function DeleteItem(arg,entity){
                if(entity == undefined || entity == '') entity = "GeographicSegment";
                $('#deleteModal').iziModal('open');
                window.deleteId = arg;
                window.deleteEntity = entity;
            }
            
            function DeleteHeader1(id){
                DeleteItem(id,"GeographicSegmentHeader1");
            }

            function DeleteHeader2(id){
                DeleteItem(id,"GeographicSegmentHeader2");
            }
            
            function DeleteHeader3(id){
                DeleteItem(id,"GeographicSegmentHeader3");
            }
            
            
            
            function cancelDelete(){
                $('#deleteModal').iziModal('close');
                window.deleteId = 0;
            }

            function FinishDelete(){
                let id = window.deleteId;
                let entity = window.deleteEntity;
                 

                $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#deletex').val() ,id:id,entity : entity},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                  
                 if(data === $('#deletedx').val()){

                     $('#deleteModal').iziModal('close');
                 toastr["success"]("Geographic Segment deleted Successfull!", "Delete Successfull");
                 
                 getGeographicSegments();
             }
             if(data === $('#deletedx').val()){
                 $('#deleteModal').iziModal('close');
                 toastr["error"]("Geographic Segment delete Failed!", "No record deleted!");
             }
             },
             error: function(a,b,c){ 

             toastr["error"]("Geographic Segment delete Failed!", "No record deleted!");}
         });
            }

            function manageGeographicSegmentHeader1(){
             $('#EditSection').hide();
             $('#MainSection').hide();
             $('#addH1Section').fadeIn();
            }

            function manageGeographicSegmentHeader2(){
             $('#EditSection').hide();
             $('#MainSection').hide();
             $('#addH2Section').fadeIn();
            }
            
            function manageGeographicSegmentHeader3(){
             $('#EditSection').hide();
             $('#MainSection').hide();
             $('#addH3Section').fadeIn();
            }
            

            function CancelManageHeader1(){
                $('#EditSection').hide();
             $('#addH1Section').hide();
             $('#MainSection').fadeIn();
            }

            function CancelManageHeader2(){
                $('#EditSection').hide();
             $('#addH2Section').hide();
             $('#MainSection').fadeIn();
            }
            
            function CancelManageHeader3(){
                 $('#EditSection').hide();
             $('#addH3Section').hide();
             $('#MainSection').fadeIn();
            }


            function FinishAddHeader1(){
                var name_addh1 = document.getElementById("name_addh1").value;
                var code_addh1 = document.getElementById("code_addh1").value;
                
                if(name_addh1.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code_addh1.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
                
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#insertx').val() , name: name_addh1, code: code_addh1,entity : "GeographicSegmentHeader1"},
             dataType: "json",
             success: Header1InsertReturnValues,
             error: function(){ toastr["error"]("Geographic Segment Insert Failed!", "No record inserted!");}
         });
            }
            
            function Header1InsertReturnValues(data){
            document.getElementById("name_addh1").value = '';
            document.getElementById("code_addh1").value = '';
                if(data.indexOf($('#insertedx').val()) !== -1){
                    toastr["success"]("Geographic Segment Header 1 Insert Successful!", "New record successfully inserted!");
                    getGeographicSegmentHeader1();               
                    ReturnToList();
                }else{
                    toastr["error"]("Geographic Segment with that name already exist", "Duplicate Entry!");
                }
            }
            
            function FinishAddHeader2(){
                let parent = Window.selHeader1;
                var name_addh2 = document.getElementById("name_addh2").value;
                var code_addh2 = document.getElementById("code_addh2").value;
                
                if(!parent) {toastr["error"]("Please select Header parent!", "Field cannot be empty!"); return;};
                if(name_addh2.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code_addh2.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
                    
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#insertx').val() , name: name_addh2, code: code_addh2,entity : "GeographicSegmentHeader2",parent : parent},
             dataType: "json",
             success: Header2InsertReturnValues,
             error: function(){ toastr["error"]("Geographic Segment Insert Failed!", "No record inserted!");}
         });
            }
            
            function Header2InsertReturnValues(data){
            document.getElementById("name_addh2").value = '';
            document.getElementById("code_addh2").value = '';
                if(data.indexOf($('#insertedx').val()) !== -1){
                    toastr["success"]("Geographic Segment Header 1 Insert Successful!", "New record successfully inserted!");
                    getGeographicSegmentHeader2();               
                    ReturnToList();
                }else{
                    toastr["error"]("Geographic Segment with that name already exist", "Duplicate Entry!");
                }
            }
            
            function FinishAddHeader3(){
                let parent = Window.selHeader2;
                var name_addh3 = document.getElementById("name_addh3").value;
                var code_addh3 = document.getElementById("code_addh3").value;
                
                if(!parent) {toastr["error"]("Please select Header parent!", "Field cannot be empty!"); return;};
                if(name_addh3.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code_addh3.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
                    
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#insertx').val() , name: name_addh3, code: code_addh3,entity : "GeographicSegmentHeader3",parent : parent},
             dataType: "json",
             success: Header3InsertReturnValues,
             error: function(){ toastr["error"]("Geographic Segment Insert Failed!", "No record inserted!");}
         });
            }
            
            function Header3InsertReturnValues(data){
            document.getElementById("name_addh3").value = '';
            document.getElementById("code_addh3").value = '';
                if(data.indexOf($('#insertedx').val()) !== -1){
                    toastr["success"]("Geographic Segment Header 1 Insert Successful!", "New record successfully inserted!");
                    getGeographicSegmentHeader3();               
                    ReturnToList();
                }else{
                    toastr["error"]("Geographic Segment with that name already exist", "Duplicate Entry!");
                }
            }
            
            
            function EditHeader1(arg){
                createCookie("formtype", "editGeographicSegmentHeader1");
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#selectx').val(), id: arg,entity : "GeographicSegmentHeader1"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                  
                 if(data !== null || data !== "null"){ 
                     data = data[0];
                      document.getElementById("code_edith1").value = data[2];
                      document.getElementById("name_edith1").value = data[1];
                      Window.updateid_h1= data[0];
                      $(".addHeader1Form").addClass("hidden");
                      $("#addHeader1Btn").addClass("hidden");
                      
                      $("#cancelEditH1Btn").removeClass("hidden");
                      $("#editHeader1Btn").removeClass("hidden");
                      $(".editHeader1Form").removeClass("hidden");
                     }
                     toastr["success"]("Geographic Segment successfully fetched!", "Geographic Segment Successfull!!!");
                    
                 }
             ,
             error: function(e,b,c){
                 toastr["error"]("Record with id " + arg + " is not found!", "Geographic Segment Select Failed!!!");
             }
         });                
            }

            function FinishHeader1Edit(){
            
            var name = document.getElementById("name_edith1").value;
            var code = document.getElementById("code_edith1").value;
            var id = Window.updateid_h1;
            
            if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#updatex').val() , name: name,id:id,code:code,entity : "GeographicSegmentHeader1"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                  
                 if(data === $('#updatedx').val()){
                 toastr["success"]("Geographic Segment update Successfull!", "Update Successfull");
                 CancelHeader1Edit();
                 getGeographicSegmentHeader1();
             }
             if(data === $('#existsx').val()){
             toastr["error"]("Geographic Segment with that name already exists", "Update Failed");
             }
             if(data === $('#no-recordx').val()){
             toastr["error"]("Geographic Segment with id "+id+" doesnt exist", "Update Failed");
             }
             
},
             error: function(a,b,c){ 
             toastr["error"]("Geographic Segment Update Failed!", "No record updated!");}
         });
            }

            function CancelHeader1Edit(){
                      $(".addHeader1Form").removeClass("hidden");
                      $("#cancelManageH1Btn").removeClass("hidden");
                      $("#addHeader1Btn").removeClass("hidden");
                      $("#editHeader1Btn").addClass("hidden");
                      $("#cancelEditH1Btn").addClass("hidden");
                      $(".editHeader1Form").addClass("hidden");
            }
            
            function EditHeader2(arg){
                 
                createCookie("formtype", "editGeographicSegmentHeader2");
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#selectx').val(), id: arg,entity : "GeographicSegmentHeader2"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                  
                 if(data !== null || data !== "null"){ 
                     data = data[0];
                      document.getElementById("code_edith2").value = data[2];
                      document.getElementById("name_edith2").value = data[1];
                      Window.updateid_h2= data[0];
                      $(".addHeader2Form").addClass("hidden");
                      $("#addHeader2Btn").addClass("hidden");
                     
                      $("#cancelEditH2Btn").removeClass("hidden");
                      $("#editHeader2Btn").removeClass("hidden");
                      $(".editHeader2Form").removeClass("hidden");
                     }
                     toastr["success"]("Geographic Segment successfully fetched!", "Geographic Segment Successfull!!!");
                    
                 }
             ,
             error: function(e,b,c){
                 toastr["error"]("Record with id " + arg + " is not found!", "Geographic Segment Select Failed!!!");
             }
         });                
            }

            function FinishHeader2Edit(){
            
            var name = document.getElementById("name_edith2").value;
            var code = document.getElementById("code_edith2").value;
            var id = Window.updateid_h2;
            
            if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#updatex').val() , name: name,id:id,code:code,entity : "GeographicSegmentHeader2"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                  
                 if(data === $('#updatedx').val()){
                 toastr["success"]("Geographic Segment update Successfull!", "Update Successfull");
                 CancelHeader2Edit();
                 getGeographicSegmentHeader2();
             }
             if(data === $('#existsx').val()){
             toastr["error"]("Geographic Segment with that name already exists", "Update Failed");
             }
             if(data === $('#no-recordx').val()){
             toastr["error"]("Geographic Segment with id "+id+" doesnt exist", "Update Failed");
             }
             
},
             error: function(a,b,c){ 
             toastr["error"]("Geographic Segment Update Failed!", "No record updated!");}
         });
            }

            function CancelHeader2Edit(){
                      $(".addHeader2Form").removeClass("hidden");
                      $("#cancelManageH2Btn").removeClass("hidden");
                      $("#addHeader2Btn").removeClass("hidden");
                      $("#editHeader2Btn").addClass("hidden");
                      $("#cancelEditH2Btn").addClass("hidden");
                      $(".editHeader2Form").addClass("hidden");
            }
            
            function EditHeader3(arg){
                 
                createCookie("formtype", "editGeographicSegmentHeader3");
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#selectx').val(), id: arg,entity : "GeographicSegmentHeader3"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                  
                 if(data !== null || data !== "null"){ 
                     data = data[0];
                      document.getElementById("code_edith3").value = data[2];
                      document.getElementById("name_edith3").value = data[1];
                      Window.updateid_h3= data[0];
                      $(".addHeader3Form").addClass("hidden");
                      $("#addHeader3Btn").addClass("hidden");
                
                      $("#cancelEditH3Btn").removeClass("hidden");
                      $("#editHeader3Btn").removeClass("hidden");
                      $(".editHeader3Form").removeClass("hidden");
                     }
                     toastr["success"]("Geographic Segment successfully fetched!", "Geographic Segment Successfull!!!");
                    
                 }
             ,
             error: function(e,b,c){
                 toastr["error"]("Record with id " + arg + " is not found!", "Geographic Segment Select Failed!!!");
             }
         });                
            }

            function FinishHeader3Edit(){
            
            let name = document.getElementById("name_edith3").value;
            let code = document.getElementById("code_edith3").value;
            let id = Window.updateid_h3;
            
            if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
         $.ajax({
             type: "GET",
             url:$('#site-url').val() + "/GeographicSegmentServlet",
             data: {option: $('#updatex').val() , name: name,id:id,code:code,entity : "GeographicSegmentHeader3"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                  
                 if(data === $('#updatedx').val()){
                 toastr["success"]("Geographic Segment update Successfull!", "Update Successfull");
                 CancelHeader3Edit();
                 getGeographicSegmentHeader3();
             }
             if(data === $('#existsx').val()){
             toastr["error"]("Geographic Segment with that name already exists", "Update Failed");
             }
             if(data === $('#no-recordx').val()){
             toastr["error"]("Geographic Segment with id "+id+" doesnt exist", "Update Failed");
             }
             
},
             error: function(a,b,c){ 
             toastr["error"]("Geographic Segment Update Failed!", "No record updated!");}
         });
            }

            function CancelHeader3Edit(){
                      $(".addHeader3Form").removeClass("hidden");
                      $("#cancelManageH3Btn").removeClass("hidden");
                      $("#addHeader3Btn").removeClass("hidden");
                      $("#editHeader3Btn").addClass("hidden");
                      $("#cancelEditH3Btn").addClass("hidden");
                      $(".editHeader3Form").addClass("hidden");
            }
           
            
            function addSegment(){
                if( Window.selHeader3 && Window.selHeader2 
                        && Window.selHeader1 && Window.selHeader3 > 0 
                        && Window.selHeader2 > 0 && Window.selHeader1 > 0)
                    showCreate();
                else
                  toastr["error"]("Please select headers ", "Geographic Segment Add Failed!!!");  
            }
        