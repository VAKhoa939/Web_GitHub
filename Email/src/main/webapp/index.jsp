<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
<%-- <c:import url="/includes/header.html" /> --%>

<c:if test="${sessionScope.user.firstName != null}">
	<h2>Hello <c:out value='${sessionScope.user.firstName}'/></h2>
</c:if>

<h1>Join our email list</h1>

<p>To join our email list, enter your name and email address below.</p>
		
<p><i><c:out value='${requestScope.message}'/></i></p>

<%-- <form action="test" method="post"> --%>
<form action="emailList" method="post">
	<input type="hidden" name="action" value="add">
		
	<label><b>Email:</b></label>
	<input type="email" name="email" value="<c:out value='${sessionScope.user.email}'/>"><br>
	
	<label><b>First Name:</b></label>
	<input type="text" name="firstName" value="<c:out value='${sessionScope.user.firstName}'/>"><br>
		
	<label><b>Last Name:</b></label>
	<input type="text" name="lastName" value="<c:out value='${sessionScope.user.lastName}'/>"><br>
	
	<label>&nbsp;</label>	
	<input type="submit" value="Join Now" class="margin_left">
	
</form>

<form action="emailList" method="post">
	<input type="hidden" name="action" value="manage">
	<label>&nbsp;</label>	
	<input type="submit" value="Go To User Page" class="margin_left">
</form>

<%@ include file="/includes/footer.jsp" %>
<%-- <c:import url="/includes/footer.jsp"/> --%>