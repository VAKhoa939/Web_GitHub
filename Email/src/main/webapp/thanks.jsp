<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
<%-- <c:import url="/includes/header.html" /> --%>

<h1>Thanks for joining our email list</h1>

<p>Here is the information that you entered:</p>
		
<label><b>- Email:</b></label>
<span><c:out value='${sessionScope.user.email}'/></span><br>
		
<label><b>- First Name:</b></label>
<span><c:out value='${sessionScope.user.firstName}'/></span><br>
		
<label><b>- Last Name:</b></label>
<span><c:out value='${sessionScope.user.lastName}'/></span><br>

<p>The current date is <c:out value='${requestScope.curDate}'/></p>
<%--
<p>The first 2 users in the ArrayList of User objects:</p>
<span>- <c:out value='${sessionScope.users[0].firstName}'/></span><br>
<span>- <c:out value='${sessionScope.users[1].firstName}'/></span><br>
 --%>
<p>To enter another email address, click on the Back button in 
your browser or the Return button shown below.</p>

<form action="" method="post">
	<input type="hidden" name="action" value="join">
	<input type="submit" value="Return">
</form>

<p><a href="/">Click me to return to the main page.</a></p><br>

<%@ include file="/includes/footer.jsp" %>
<%-- <c:import url="/includes/footer.jsp"/> --%>