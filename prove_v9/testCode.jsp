<%
	//
	//	Author	: 	Mai H. Dinh
	// 	Date	:	Oct 24, 2013
	//
	
	// get student code
	String submitCode = "";
	String answer = "";
	
	String stdCode = prototype;
	int id_num = Integer.parseInt(id); //id_num is the id number of the problem
	
	
	int count = Integer.parseInt(request.getParameter("count"));
	int j = 0;
	for (j = 0; j < count; j++)
	{
		submitCode = request.getParameter("code" + String.valueOf(j));
		stdCode = stdCode.replaceFirst ("//TODO", submitCode);
	}
	
	String stdCode2 = stdCode;
	
	int strFirst = stdCode2.indexOf("#");
	int strEnd = stdCode2.indexOf("\n");
	if (strFirst != -1)
	{
		String strPrag = stdCode2.substring(strFirst, strEnd);
		stdCode2 = stdCode2.replace(strPrag, "");
	}

	strFirst = stdCode2.indexOf("#");
	if (strFirst != -1)
	{
		String strPrag2 = stdCode2.substring(strFirst, strFirst+32);
		stdCode2 = stdCode2.replace(strPrag2, "");
	}

	while (true)
	{
		strFirst = stdCode2.indexOf("/*");
		strEnd = stdCode2.indexOf("*/");
		if (strFirst == -1 || strEnd == -1)
			break;
		String comment = stdCode2.substring(strFirst, strEnd+4);
		stdCode2 = stdCode2.replace(comment, "");
	}	
	
	String newCode = stdCode;
	
	newCode = newCode.replaceAll ("&", "&amp;");
	newCode = newCode.replaceAll ("<", "&lt;");
	
	// CTGE_DSFL folder path
	String folderPath = base_url + "CTGE_DSFL/";
	folderPath = getServletContext().getRealPath(folderPath);
	
	// student.c
	String output2_c = folderPath + "/CTGE/student.c";
	try {   
		FileOutputStream file_c = new FileOutputStream(output2_c);
		PrintWriter prw = new PrintWriter(file_c);		
		prw.println(stdCode2);		
		prw.close();
	} catch(IOException e) {out.println(e);}
	
	// solution.c
	String solution_c = folderPath + "/CTGE/solution.c";
	try {   
		FileOutputStream file_c = new FileOutputStream(solution_c);
		PrintWriter prw = new PrintWriter(file_c);		
		prw.println(teacher_solution);		
		prw.close();
	} catch(IOException e) {out.println(e);}
	
	// run script
	String relativeCTGE_DSFL = base_url + "shellscript/run_CTGE_DSFL.sh";
	String realCTGE_DSFL = getServletContext().getRealPath(relativeCTGE_DSFL);	
	String scriptPath = realCTGE_DSFL + " " + folderPath;
	int result = runScript(scriptPath);
	//out.println(scriptPath);
%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> CTG-FL </title>
<link href="libs/jquery-easyui/themes/metro-blue/easyui.css" rel="stylesheet" type="text/css" />
<link href="libs/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="libs/jquery-easyui/themes/metro-blue/datagrid.css" rel="stylesheet" type="text/css" />
<script src="libs/jquery-easyui/jquery.min.js" type="text/javascript"></script>
<script src="libs/jquery-easyui/jquery.easyui.min.js" type="text/javascript" ></script>
<script>

var rowIdx = 0;

$(function() {
	// grid ctg-fl
	$('#dg_fl').datagrid({
        title: 'Suspicious Error (click to navigate to code)',
        striped: true, rownumbers: true, singleSelect: true, collapsible: true,
        rowStyler: function(index, row) {
            if (row.color == 0)
                return 'color:red;font-weight:bold;';
            else if (row.color == 1)
				return 'color:green;font-weight:bold;';
        },
        columns: [[
				{field: 'id_fl', hidden: true},
                {field: 'code', title: 'Code', width: 300, formatter: formatUrl},
                {field: 'rank', title: 'Rank'},
            ]],
        sortName: "rank", sortOrder: "desc", remoteSort:false,
    });
    
	// get data for grid ctg-fl
	$.ajax({
		async: false, timeout: 180000,
		url: 'CTGE_DSFL/ctg_fl.jsp?m=dsfl',
		success: function(result) {
			$('#dg_fl').datagrid({
				data: $.parseJSON(result)
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			$.messager.alert('Notice', textStatus , 'error');
		}
	});
	

	// grid student code
    $('#dg').datagrid({
        title: 'Student code',
        striped: true, rownumbers: true, singleSelect: true, collapsible: true,
        rowStyler: function(index, row) {
            if (row.color == 0)
                return 'color:red;font-weight:bold;';
            else if (row.color == 1)
				return 'color:green;font-weight:bold;';
        },
        columns: [[
                {field: 'code', title: 'Code', width: 300},
                {field: 'rank', title: 'Rank'},
                {field: 'color', hidden: true},
                {field: 'color_origin', hidden: true},
            ]],
    });
    
    var data_cb = null;
	// get testcase
	$.ajax({
		async: false, timeout: 180000,
		url: 'CTGE_DSFL/ctg_fl.jsp?m=combotestcase',
		success: function(result) {
			data_cb = $.parseJSON(result)
		},
		error: function(jqXHR, textStatus, errorThrown) {
			$.messager.alert('Notice', textStatus , 'error');
		}
	});
	// combo test case
	$('#cb').combobox({
		data: data_cb,
		valueField:'id',
		textField:'text'
	});
	// set default value for combo test case
	$('#cb').combobox('setValue', data_cb[0]['id']);
	doClick(data_cb[0]['id']);
	
	$('#btn').linkbutton({iconCls: 'icon-search'});
	$('#btn').bind('click', function(){
		doClick($('#cb').combobox('getValue'));
	});
	
	$('#btn_back').bind('click', function(){
		rowIdx--;
		doColorCode();
	});
	$('#btn_next').bind('click', function(){
		rowIdx++;
		doColorCode();
	});
	
	
});

function doClick(testId){
	$.ajax({
		timeout: 180000,
		url: 'CTGE_DSFL/ctg_fl.jsp?m=fl&testId='+ testId,
		success: function(result) {
			$('#dg').datagrid({
				data: $.parseJSON(result)
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			$.messager.alert('Notice', textStatus , 'error');
		}
	});
}

function formatUrl(value, rowData, rowIndex) {
    return '<a href="#dg" onClick="navigateCode(' + rowIndex + ','+ rowData['id_fl'] +')">' + value + '</a>';
}

function navigateCode(rowIndex, id_fl) {
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++)
        if (rows[i]['color'] == 0){
            rows[i]['color'] = rows[i]['color_origin'];
            $('#dg').datagrid('refreshRow', i);
		}
            
    rows[id_fl-1]['color'] = 0;
    $('#dg').datagrid('refreshRow', id_fl-1);
    
    rowIdx = rowIndex;
}

function doColorCode() {   
	var row_fl = $('#dg_fl').datagrid('getRows')[rowIdx];
	if(row_fl){
		var rank = row_fl.rank;
	   	var rows = $('#dg').datagrid('getRows');
		for (var i = 0; i < rows.length; i++)
			if (rows[i]['rank'] == rank){
				rows[i]['color'] = 0;
				$('#dg').datagrid('refreshRow', i);
			}
			else if (rows[i]['color'] == 0){
				rows[i]['color'] = rows[i]['color_origin'];
				$('#dg').datagrid('refreshRow', i);
			}
	}
	else rowIdx = 0;
}

</script>
</head>
<body>
<br/>
Testcases : <input id="cb" name="cb">
<a id="btn" href="#dg"> Show path </a>
<a id="btn_back" href="#dg" class="easyui-linkbutton"><font size="4"> < </font></a>
<a id="btn_next" href="#dg" class="easyui-linkbutton"><font size="4"> > </font></a>
<br/><br/>
<table>
<tr>
<td style="width:400px" valign="top"><table id="dg"></table></td>
<td style="width:40px"></td>
<td style="width:400px" valign="top"><table id="dg_fl"></table></td>
</tr>
</table>
</body>
</html>
