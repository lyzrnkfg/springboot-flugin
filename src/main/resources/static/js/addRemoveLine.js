$(function(){
	
    $(".addBtn").click(function(){
    	$(".addRemoveTable").append($(".addRemoveTable tr:last").clone(true))
       $(".addRemoveTable tr:last").find(".form-control").val("")
    })
    
    $(".removeBtn").click(function(){
    	if ($(this) == null){
      	  return false;  
        }
       $thisTbody = $(this).parents("tbody")
       if ($thisTbody.find("tr").length == 1){
      	  $thisTbody.find("tr").find(".form-control").val("")
       } else {
      	  $(this).parents("tr").remove();  
        }
    })
    
    $(".onlyNumber").keyup(function(){  
		$(this).val($(this).val().replace(/\D|^0/g,'')); 
	}).bind("paste",function(){  
		$(this).val($(this).val().replace(/\D|^0/g,'')); 
	}).css("ime-mode", "disabled"); 
    
})