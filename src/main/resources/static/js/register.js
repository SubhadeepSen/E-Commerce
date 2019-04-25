(function(){
	$(document).ready(function(){
		$('#registerBtn').on('click', function(){
			let isValid = true;
			$('input').each(function(){
				let msg = validateInput($(this));
				if(msg != ''){
					isValid = false;
					$(this).attr('placeholder', msg);
					$(this).focus();
					return false;
				}
			});
			if(isValid){
				$('#register').submit();
			}
		});
	});
	
	function validateInput($this){
		switch ($this.attr('id')) {
		case 'firstname':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter a valid Firstname';
		case 'lastname':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter a valid Lastname';
		case 'emailAddress':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter a valid Email Address';
		case 'dob':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter in yyyy-mm-dd format';
		case 'username':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter a valid Username';
		case 'password':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter a valid Password';
		case 'phoneNumber':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter a valid Phone Number';
		case 'address':
			if($this.val() == undefined || $this.val() === '')
				return 'Please enter a valid Address';
		default:
			return '';
		}
	}
})();