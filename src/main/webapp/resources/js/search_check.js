function fnCheck(){
	if($('#keyword').val().trim() == ''){
		alert('검색어를 입력하세요');
		$(this).val('');
		$(this).focus();
		return false;
	}
}