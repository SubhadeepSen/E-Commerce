(function() {
	$(document).ready(function() {
		let productId = $('#productId').val();
		$('#addToCartBtn').on('click', function() {
			let quantity = parseInt($('#quantity').val());
			if(quantity > 0){
				$('#error').attr('hidden', 'true');
				window.location.href = '../addToCart/' + productId + 'qnty' + quantity;
			}else{
				$('#error').removeAttr('hidden');
			}
		});
		$('#checkoutBtn').on('click', function() {
			let value = $('#prodId').val() + $('#quantity').val();
			$('#prodId').val(value);
			$('#checkout').submit();
		});
	});
})();