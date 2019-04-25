(function(){
	$(document).ready(function(){
		$('.remove-cart').on('click', function(){
			window.location.href = 'remove/' + $(this).attr('id');
		});
	});
})();