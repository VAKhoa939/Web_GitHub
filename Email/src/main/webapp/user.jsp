<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>

<p><i><c:out value='${requestScope.message}'/></i></p>

<p><b>- List of users:</b></p>

<table>
	<tr>
		<th>First name</th>
		<th>Last name</th>
		<th>Email</th>
	</tr>
	<c:forEach var="user" items="${requestScope.users}">
		<tr>
			<td><c:out value='${user.firstName}'/></td>
			<td><c:out value='${user.lastName}'/></td>
			<td><c:out value='${user.email}'/></td>
		</tr>
	</c:forEach>
</table>

<form action="emailList" method="post">
	<input type="hidden" name="action" value="update">
	<p><b>- Update an user:</b></p>
	
	<label>His/Her email:</label>
	<input type="email" name="email"><br>
	
	<label>His/Her new name (leave blank for not changing):</label><br>
	<label>New first name:</label>
	<input type="text" name="firstName"><br>
			
	<label>New last name:</label>
	<input type="text" name="lastName"><br>
	
	<label>&nbsp;</label>	
	<input type="submit" value="Confirm update" class="margin_left">
</form>

<form action="emailList" method="post">
	<input type="hidden" name="action" value="delete">
	<p><b>- Delete an user:</b></p>
	
	<label>His/Her email:</label>
	<input type="email" name="email"><br>
	
	<label>&nbsp;</label>	
	<input type="submit" value="Confirm delete" class="margin_left">
</form>

<form action="emailList" method="post">
	<input type="hidden" name="action" value="insert">
	<p><b>- Insert a new user:</b></p>
	
	<label>Email:</label>
	<input type="email" name="email"><br>
	
	<label>First name:</label>
	<input type="text" name="firstName"><br>
			
	<label>Last name:</label>
	<input type="text" name="lastName"><br>
	
	<label>&nbsp;</label>	
	<input type="submit" value="Confirm insert" class="margin_left">
</form>

<p><b>- Click Return button to go back to the join page.</b></p><br>

<form action="" method="post">
	<input type="hidden" name="action" value="join">
	<input type="submit" value="Return">
</form>

<p><a href="/">Click me to return to the main page.</a></p><br>

<%@ include file="/includes/footer.jsp" %>