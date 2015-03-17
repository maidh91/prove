function dynamicTextArea(id, obj,event)
{
	if (id == 1) {
		var array;
		array = obj.value.split('\n');
		obj.rows = array.length ;		
	}
	if (id == 0) {
		if (event.keyCode == 13)
			obj.rows = obj.rows + 1;		
	}
}
function showResultField()
{
	document.getElementById('codefield').style.display = 'none';	
	document.getElementById('verifyresult').style.display = 'block';
}
function showCodeField()
{
	document.getElementById('codefield').style.display = 'block';	
	document.getElementById('verifyresult').style.display = 'none';
}
function showVarInitField(objname)
{
	var obj = document.getElementById(objname);
	if (obj.style.display == 'block')	
		obj.style.display = 'none';
	else
		obj.style.display = 'block';
}
function loadTextArea(id)
{
	var i = 0;
	var objname;
	var array;
	var obj;
	var num = document.getElementById(id).value;
	while (i < num)
	{
		objname = "code"+i;
		obj = document.getElementById(objname);
		array = obj.value.split('\n');
		obj.rows = array.length + 1;
		if (obj.value != "// Enter your code here.")
		{
			obj.style.color = '#000000';
			obj.rows = array.length;
		}
		i = i + 1;
	}
	
}
function focusTextArea(obj)
{
	if (obj.value == "// Enter your code here.")
	{
		obj.value = "";
		obj.style.color = '#000000';
	}
}
function load_radio_button(deValue, webValue, exValue, num_ex)
{
	var i = 0;
	while (i < 3)
	{
		if (editMessage.desresult[i].value == deValue)
		{
			editMessage.desresult[i].checked = true;
			break;
		}
		i++;
	}
	i = 0;
	while (i < 5)
	{
		if (editMessage.webresult[i].value == webValue)
		{
			editMessage.webresult[i].checked = true;
			break;
		}
		i++;
	}
	i = 0;
	while (i < num_ex)
	{
		if (editMessage.exid.options[i].text == exValue)
		{
			editMessage.exid.selectedIndex = i;
			break;
		}
		i++;
	}
}
function formValidate()
{
	var theForm = document.forms['addMessage'];
	var errMsg = "";
	var setfocus = "";
	var i = 0;
	var selectedItem = addMessage.exid.selectedIndex;
	
	while (i < 3)
	{
		if (addMessage.desresult[i].checked == true) break;
		i++;
	}
	if (i == 3)
	{
		errMsg = "Please choose your desired result\. ";
		setfocus = "['exid']";
	}
	i = 0;
	while (i < 5)
	{
		if (addMessage.webresult[i].checked == true) break;
		i++;
	}
	if (i == 5)
	{
		errMsg = "Please choose the web result\. ";
		setfocus = "['exid']";
	}
	
	if (theForm['stdcode'].value == "")
	{
		errMsg = "Please enter your C++ code\. ";
		setfocus = "['stdcode']";
	}
	
	
	if (selectedItem == 0)
	{
		errMsg = "Please choose your exercise ID\. ";
		setfocus = "['exid']";
	}
	
	if (errMsg != ""){
		alert(errMsg);
		eval("theForm" + setfocus + ".focus()");
	}
	else 
	{
		theForm.submit();
	}
}

function show(id) {
	var name_img = 'img';
	
	if (document.getElementById(id).style.display == 'block') {
		document.getElementById(id).style.display = 'none';
		document.getElementById(name_img+id).style.backgroundImage = 'url(js_css/mcImg/plus.gif)';
	}
	else {
		document.getElementById(id).style.display = 'block';
		document.getElementById(name_img+id).style.backgroundImage = 'url(js_css/mcImg/minus.gif)';
	}
}