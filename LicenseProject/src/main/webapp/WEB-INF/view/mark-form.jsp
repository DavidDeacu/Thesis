<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mark Form ${studentId}</title>

<link type="text/css" rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-student-style.css">

</head>
<body>

	<c:url var="saveMark" value="saveMark">
		<c:param name="theStudentId" value="${studentId}" />
	</c:url>
	<c:url var="backLink" value="showMarks">
		<c:param name="studentId" value="${studentId}" />
	</c:url>

	<div id="wrapper">
		<div id="header">
			<h2>Mark Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Mark</h3>
	
		<form:form action="${saveMark}" modelAttribute="mark" method="POST">

			<!-- need to associate this data with mark id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>Subject:</label></td>
						<td><form:input path="subject" /></td>
					</tr>
				
					<tr>
						<td><label>Mark:</label></td>
						<td><form:input path="mark" /></td>
					</tr>

					<tr>
						<td><label>Date:</label></td>
						<td><form:input type="date" path="date" /></td>
					</tr>
					
					<tr>
						<td><label>Remark:</label></td>
						<td><form:input path="remark" /></td>
					</tr>

					<tr>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${backLink}">Back to List</a>
		</p>
	
	</div>


</body>
</html>