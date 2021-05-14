$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "UserAPI",
type : type,
data : $("#formUser").serialize(),
dataType : "text",
complete : function(response, status)
{
onItemSaveComplete(response.responseText, status);
}
});
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidUserIDSave").val($(this).closest("tr").find('#hidUserIDUpdate').val());
 $("#Fname").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Lname").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Email").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Address").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Phone_n").val($(this).closest("tr").find('td:eq(4)').text());
 $("#username").val($(this).closest("tr").find('td:eq(5)').text());
 $("#Address").val($(this).closest("tr").find('td:eq(6)').text());
});
// CLIENT-MODEL================================================================
function validateUserForm()
{
// Fname
if ($("#Fname").val().trim() == "")
 {
 return "Insert User Fname.";
 }
// Lname
if ($("#Lname").val().trim() == "")
 {
 return "Insert User Lname.";
 } 
9
// Email
if ($("#Email").val().trim() == "")
 {
 return "Insert User Email.";
 } 
 // Address
if ($("#Address").val().trim() == "")
 {
 return "Insert User Address.";
 } 
 // Phone_n
if ($("#Phone_n").val().trim() == "")
 {
 return "Insert User Phone_n.";
 } 
// username
if ($("#username").val().trim() == "")
 {
 return "Insert user username.";
 }
 // password
if ($("#password").val().trim() == "")
 {
 return "Insert user username.";
 }
return true;
}

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
14
 $("#hidUserIDSave").val("");
 $("#formUser")[0].reset();
}
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "UserAPI",
		 type : "DELETE",
		 data : "Id=" + $(this).data("Id"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onUserDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}