<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.jsp" %>
<div class="account-page">
	<div class="container">
		<div class="row">
			<div class="col-2">
				<img src="images/logo.png" width="10%">
			</div>
			<div class="col-2">
				<div class="form-container">
					<div class="form-btn">
						<span onclick="login()">Login</span>
						<span onclick="register()">Register</span>
						<hr id="Indicator">
					</div>

					<form id="LoginForm">
						<input type="email" placeholder="Email">
						<input type="password" placeholder="Password">
						<button type="submit" class="btn">Login</button>
						<a href="">Forget Password</a>
					</form>

					<form id="RegisterForm">
						<input type="email" placeholder="Email">
						<input type="password" placeholder="Password">
						<button type="submit" class="btn">Register</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- js toggle menu -->
<script>
	var LoginForm = document.getElementById("LoginForm");
	var RegisterForm = document.getElementById("RegisterForm");
	var Indicator = document.getElementById("Indicator");
	function register() {
		RegisterForm.style.transform = "translateX(0px)";
		LoginForm.style.transform = "translateX(0px)";
		Indicator.style.transform = "translateX(100px)";
	}

	function login() {
		RegisterForm.style.transform = "translateX(300px)";
		LoginForm.style.transform = "translateX(300px)";
		Indicator.style.transform = "translateX(0px)";
	}

</script>
<%@ include file="/includes/footer.jsp" %>