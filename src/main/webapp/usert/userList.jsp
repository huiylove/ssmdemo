<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
<title>测试</title>  
<meta content="IE=EmulateIE8" http-equiv="X-UA-Compatible">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<meta charset="utf-8">
<c:set var="base" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/js/easyui/1.4.2/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/resources/js/easyui/1.4.2/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/resources/js/easyui/1.4.2/themes/icon.css">
<script type="text/javascript" src="${base}/resources/js/easyui/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/easyui/1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/easyui/1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){  
// 	$('#dg').datagrid({     
// 		    url:'${base}/user/getAll.action',  
// 		    fit:true,//自动补全  
// 		    fitColumns:true,  
// 		    iconCls:"icon-save"//图标     
// 		  
// 	});  
	   //datagrid初始化  
	    $('#dg').datagrid({  
	        title:'用户列表',  
// 	        iconCls:'icon-edit',//图标  
	        width: 700,  
	        height: 'auto',  
	        nowrap: false,  
	        striped: true,  
	        border: true,  
	        collapsible:false,//是否可折叠的  
// 	        fit: true,//自动大小  
	        url:'${base}/user/getAll.action',  
	        //sortName: 'code',  
	        //sortOrder: 'desc',  
	        remoteSort:false,   
	        idField:'id',  
	        singleSelect:false,//是否单选  
	        pagination:true,//分页控件  
	  	    pageSize: 2,//每页显示的记录条数，默认为10  
	        pageList: [2,4,6],//可以设置每页记录条数的列表  、
	        rownumbers:true,//行号  
	        frozenColumns:[[  
                {field:'ck',checkbox:true}  
            ]] 
	    });  
	
	   //设置分页控件  
	    var p = $('#dg').datagrid('getPager');  
	    $(p).pagination({  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	    }); 
});


  

</script>
</head>  
  <body>  
   <div style="padding:100px;" >
    <table id="dg"  cellpadding="0" cellspacing="0">
      <thead>
		<tr>
		    <th data-options="field:'id',width:100">id</th>
			<th data-options="field:'userName',width:120">姓名</th>
			<th data-options="field:'age',width:120">年龄</th>
			<th data-options="field:'password',width:120">密码</th>
		</tr>
      </thead>
	</table>
	</div>
  </body>  
</html>  
