$(function(){
	
	$('#checkAll').on("click", function() {
		if ($(this).is(':checked')) {
			$('.mIfLockCheckBox').each(function() {
				$(this).prop("checked", true);
			});
		} else {
			$('.mIfLockCheckBox').each(function() {
				$(this).prop("checked", false);
			});
		}
	});

	$('#table :checkbox').click(function() {
		allchk();
	});
		
	function allchk() {
	   var chknum = $('.mIfLockCheckBox').length;
	   var chk = 0;
	   $('.mIfLockCheckBox').each(function() {
		   if ($(this).is(':checked') == true) {
		  		chk++;
		    }
	    });
	   if (chknum == chk) {
	      $('#checkAll').prop("checked", true);
	   } else {
	      $('#checkAll').prop("checked", false);
	   }
	}
	
	
})

