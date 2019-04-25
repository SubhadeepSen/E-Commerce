var totalAmount;
(function() {
	$(document).ready(function() {
		var applied = false;
		$("#couponCode").change(function() {
			applied = false;
			let couponCode = $('#couponCode').val();
			if(couponCode != undefined && couponCode != '' && !applied){
				$.get("coupon/" + couponCode, function(data, status) {
					if(status === 'success' && data != ''){
						handleDiscount(data);
						applied = true;
					}else{
						if(applied)
							$('#totalAmount').html('Total: &#8377; ' + totalAmount);
						handleErrorMessage();
					}
				});
			}else if(couponCode == undefined || couponCode === ''){
				$('#totalAmount').html('Total: &#8377; ' + totalAmount);
				handleErrorMessage();
				applied = false;
			}
		});
	});

	function handleDiscount(data){
		let res = data.split(':');
		let percentage = parseFloat(res[0]);
		let maxLimit = parseFloat(res[1]);
		$('#coupon').html('Maximum discount is ' + percentage + '% with max limit of &#8377;' + maxLimit + '.');
		$('#coupon').removeAttr('hidden');
		let total = parseFloat($('#totalAmount').html().split(':')[1].substring(2));
		totalAmount = total;
		let discount = (total*percentage)/100;
		let msg;
		if(discount > maxLimit){
			msg = 'Discount ' + '-&#8377; ' + maxLimit;
			total = total - maxLimit;
		}else{
			msg = 'Discount ' + '-&#8377; ' + discount;
			total = total - discount;
		}
		$('#coupon-msg').html(msg);
		$('#coupon-msg').removeAttr('hidden');
		$('#totalAmount').html('Total: &#8377; ' + total);
	}

	function handleErrorMessage(){
		$('#coupon').html('Invalid coupon code.');
		$('#coupon').removeAttr('hidden');
		$('#coupon-msg').html('');
		$('#coupon-msg').attr('hidden', 'true');
	}
})();