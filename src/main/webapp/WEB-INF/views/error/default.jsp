<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css">
	p{
		font-size: .7em;
	}
	.all{min-height: 600px; max-height: 100%; padding-bottom: 200px}
	.a{
		width: 580px;
		margin: 0 auto;
		padding-top: 50px;
		padding-bottom: 50px;
	}
	
	.b{
		width: 800px;
		border: 1px solid #cccccc;
		color: #cccccc;
		padding: 10px;
		margin: 0 auto;
	}
</style>
<div class="all">
	<div class="a">    
		<h3>내부적인 오류가 발생했습니다</h3>
		<p>빠른 시일내에 복구시키도록 하겠습니다.<br>
		관련 문의 사항는 고객센터에  알려주시면 친절하게 안내해 드리겠습니다.</p>
		<p>감사합니다.</p> 
	</div>
	
	<div class="b">   
		${msg}
	</div>
</div>