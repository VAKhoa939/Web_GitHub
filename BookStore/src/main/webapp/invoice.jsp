<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.jsp" %>
<div class = "wrapper">
	<div class ="invoice-header">
		<div class = "logo">
			<img src="images/pngLogo.png" width="125">
		</div>
		<div class ="title">
			Your Invoice
		</div>
	</div>	
	<label><b>Date created: </b></label>
	<c:out value="${invoice.getInvoiceDate()}"/><br>
	
	<label><b>Estimate date arrival: </b></label>
	<c:out value="${invoice.getEstArrivalDate()}"/><br>
	
	<label><b>Ship to: </b></label>
	<c:out value="${invoice.getCart().getUser().getAddr()}"/><br>
	
	<label><b>Cart: </b></label><br>
		<table>
		<tr>
			<th>Product</th>
			<th>Quantity</th>
			<th>Subtotal</th>
		</tr>
		<c:forEach var="item" items="${invoice.getCart().getItems()}">
			<tr>
				<td>
					<div class="cart-info">
						<img src = "images/${item.getItemCode()}.jpg">
						<div>
							<p><c:out value="${item.getProduct().getInfor().getBookName()}"/></p>
							<small>Price: <c:out value="${item.getProduct().getCurrencyFormat()}"/></small><br>
						</div>
					</div>
				</td>
				<td><c:out value="${item.getQuantity()}"/></td>
				<td><c:out value="${item.getTotalCurrencyFormat()}"/></td>
			</tr>
		</c:forEach>
		<tr>
			<td>Total</td>
			<td></td>
			<td><c:out value="${invoice.getCart().getTotalCurrencyFormat()}"/></td>
		</tr>
	</table>
</div>
	 <div class="footer">
	 </div>
<%@ include file="/includes/footer.jsp" %>