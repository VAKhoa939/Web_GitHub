<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.jsp" %>
<h1>Account's information:</h1>
		
<label><b>- User Name:</b></label>
<span><c:out value='${sessionScope.user.userName}'/></span><br>

<label><b>- Email:</b></label>
<span><c:out value='${sessionScope.user.email}'/></span><br>
		
<label><b>- Address:</b></label>
<span><c:out value='${sessionScope.user.addr}'/></span><br>

<label><b>- Phone Number:</b></label>
<span><c:out value='${sessionScope.user.phoneNo}'/></span><br>

<form action="account" method="post">
	<input type="hidden" name="action" value="logout">
	<input type="submit" value="Log out">
</form>

<%@ include file="/includes/footer.jsp" %>