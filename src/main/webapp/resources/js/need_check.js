function necessary(){
	//alert("123123");
	var need = true;
	$('.need').each( function(){
		if( $(this).val().trim() =='' ){
			alert( $(this).attr('title') + " 입력하세요");
			$(this).val('');
			$(this).focus();
			need = false;
			return need;
		}
	} );
	return need;
}
