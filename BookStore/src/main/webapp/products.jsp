<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.jsp" %>
<div class="small-container">
	<h2 class="title">All Products</h2>
	<div class="row row2">
		<select name="sort">
			<option>Default Sorting</option>
			<option>Sort by price</option>
			<option>Sort by popularity</option>
			<option>Sort by sale</option>
		</select>
	</div>
	<div class="row">
		<c:forEach var="product" items="${products}">
			<div class="col4">
				<form action="detail" method="post">
					<input type="hidden" name="productCode" value="${product.getProductCode()}">
					<button type="submit">
						<img src="images/${product.getProductCode()}.jpg">
						<h4>${product.getInfor().getBookName()}</h4>
						<p>>${product.getCurrencyFormat()}</p>
					</button>
				</form>
			</div>
		</c:forEach>
	</div>
	<div class="page-btn">
		<span>1</span>
		<span>2</span>
		<span>3</span>
		<span>4</span>
		<span>&#8594</span>
	</div>
</div>
<%@ include file="/includes/footer.jsp" %>