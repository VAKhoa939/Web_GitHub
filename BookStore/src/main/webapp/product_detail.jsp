<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.jsp" %>
<c:if test="${message!=null||!message.isEmpty()}">
	<p>${message}</p>
</c:if>
<div class="small-container single-product">
	<div class="row">
		<div class="col2">
			<img src="images/${product.getProductCode()}.png" width=100% id="ProductImg">
		</div>
		<div class="col2">
			<h1>${product.getInfor().getBookName()}</h1>
			<h4>${product.getCurrencyFormat()}</h4>
			<form action="cart" method="post">
				<input type="hidden" name="action" value="new">
				<input type="hidden" name="productCode" value="${product.getProductCode()}">
				<input type="hidden" name="addQuantity" value="1">
				<button class="btn" type="submit">Add to cart</button>
			</form>
			<h3>Product Details</h3>
			<p>${product.getInfor().getDetail()}</p>
		</div>
	</div>
</div>

<div class="small-container">
	<h2 class="title">Related Products</h2>
</div>

<div class="row">
	<div class="col4">
		<img src="images/countofmontecristo.jpg">
		<h4>The Count of Monte Cristo</h4>
		<p>523.000 VND</p>
	</div>
	<div class="col4">
		<img src="images/bloodthristy.jpg">
		<h4>Bloodthirsty</h4>
		<p>335.000 VND</p>
	</div>
	<div class="col4">
		<img src="images/themissingqueen.jpg">
		<h4>The Missing Queen</h4>
		<p>452.000 VND</p>
	</div>
	<div class="col4">
		<img src="images/excuseme.jpg">
		<h4>Excuse Me While I Disappear</h4>
		<p>226.000 VND</p>
	</div>
</div>
<script>
	var ProductImg = document.getElementById("ProductImg");
	var SmallImg = document.getElementsByClassName("small-img");

	SmallImg[0].onclick = function () {
		ProductImg.src = SmallImg[0].src;
	}
	mallImg[1].onclick = function () {
		ProductImg.src = SmallImg[1].src;
	}
	mallImg[2].onclick = function () {
		ProductImg.src = SmallImg[2].src;
	}
	mallImg[3].onclick = function () {
		ProductImg.src = SmallImg[3].src;
	}
</script>
<%@ include file="/includes/footer.jsp" %>