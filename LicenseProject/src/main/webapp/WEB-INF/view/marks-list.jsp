<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Student Marks</title>

<link type="text/css" rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
		  
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>${student.firstName} ${student.lastName}`s marks</h2>
		</div>
	</div>
	<!-- construct an "add" link with student id -->
	<c:url var="addLink" value="showFormForAddMark">
		<c:param name="studentId" value="${student.id}" />
	</c:url>	
	<div id="container">
		<div id="content">
			<!-- put new button: Add Mark -->
			<input type="button" value="Add Mark"
				   onclick="window.location.href='${addLink}'; return false;"
				   class="add-button"/>
				   
			<!--  add html table here -->
			<table>
				<tr>
					<th>Subject</th>
					<th>Mark</th>
					<th>Date</th>
					<th>Remark</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our students -->
				<c:forEach var="tempMark" items="${marks}">
				
					<!-- construct an "update" link with mark id -->
					<c:url var="updateLink" value="/student/showFormForUpdateMark">
						<c:param name="markId" value="${tempMark.id}" />
						<c:param name="studentId" value="${student.id}" />
					</c:url>					

					<!-- construct an "delete" link with student id -->
					<c:url var="deleteLink" value="/student/deleteMark">
						<c:param name="markId" value="${tempMark.id}" />
						<c:param name="studentId" value="${student.id}" />
					</c:url>												
					
					<tr>
						<td> ${tempMark.subject} </td>
						<td> ${tempMark.mark} </td>
						<td> ${tempMark.date} </td>
						<td> ${tempMark.remark} </td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this mark?'))) return false">Delete</a>
						</td>	
					</tr>
				</c:forEach>		
			</table>	
		</div>
		<p>
			<a href="${pageContext.request.contextPath}/student/list">Back to List</a>
		</p>
	</div>
</body>
</html>