<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head></head>
<body>
	<table border="0" width="600px">
		<tr>
			<td align="center" style="font-size: 24px; color: #666">部门编辑</td>
		</tr>
		<tr>
			<td align="right">
			 <a href="javascript:document.getElementById('saveForm').submit">保存</a> &nbsp;&nbsp;
			 <a href="javascript:history.go(-1)">退回</a>
			 </td>
		</tr>
	</table>
	<br /><br>
	<s:form id="saveForm" action="department_update" method="post" namespace="/" theme="simple">
	<s:hidden name="did" value="%{model.did}"/>
	<table style="width: 80%">
		<tr>
			<td>部门名称：</td>
			<td><s:textfield name="dname" value="%{model.dname}"/></td>
		</tr>
		<tr>
			<td>部门介绍：</td>
			<td><s:textarea rows="5" cols="50" name="ddesc" value="%{model.ddesc}"/></td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td><textarea cols="50" rows="5">ckeditor</textarea></td>
		</tr>
	</table>
	</s:form>
</body>
</html>
