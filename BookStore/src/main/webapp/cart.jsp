<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.jsp" %>
<div class="small-container cart-page">
	<table>
		<tr>
			<th>Product</th>
			<th>Quanlity</th>
			<th>Subtotal</th>
		</tr>

		<tr>
			<td>
				<div class="cart-info">
					<img src = "images/Adventuretime.jpg">
					<div>
						<p>The Adventure Time</p>
						<small>Price: $5</small>
						<br>
						<a href="">Remove</a>
					</div>
				</div>
			</td>
			<td><input type="number" value="1"></td>
			<td>$5</td>
		</tr>
	</table>
</div>
<%@ include file="/includes/footer.jsp" %>