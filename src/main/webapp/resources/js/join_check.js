var join = {
	common : {
		empty : {
			code : 'empty',
			desc : '해당칸을 입력하세요'
		},
		space : {
			code : 'space',
			desc : '공백 없이 입력해주세요'
		},
		min : {
			code : 'min',
			desc : '최소 5글자 이상 입력하세요'
		},
		max : {
			code : 'min',
			desc : '최대 10글자 이하 입력하세요'
		}

	},

	id : {
		empty : {
			code : 'empty',
			desc : '아이디를 입력하세요'
		},
		invalid : {
			code : 'invalid',
			desc : '영소문자 숫자만 입력해주세요.'
		},
		valid : {
			code : 'valid',
			desc : '아이디 중복 확인 하세요'
		},
		usable: {
			code: 'usable',
			desc : '사용 가능한 아이디 입니다.'
		},
		unusable: {
			code: 'unusable',
			desc : '이미 사용 중인 아이디 입니다.'
		}

	},
	
	
	email: {
		empty : {
			code : 'empty',
			desc : '이메일을 입력하세요'
		},
		invalid: { code: 'invalid', desc: '이메일을 입력해주세요.' },
		lack: { code: 'lack', desc: '이메일 형식이 아닙니다.' },
		valid: { code: 'valid', desc: '이메일이 입력되었습니다.' },
	},
	
	post: {
		empty : {
			code : 'empty',
			desc : '주소를 입력하세요'
		},
		invalid: { code: 'invalid', desc: '주소를 입력해주세요.' },
		valid: { code: 'valid', desc: '주소가 입력되었습니다.' },
	},
	pw: {
		empty : {
			code : 'empty',
			desc : '비밀번호를 입력하세요'
		},
		invalid: { code: 'invalid', desc: '영문 대소문자, 숫자만 입력해주세요.' },
		lack: { code: 'lack', desc: '영소문자, 숫자 모두 포함하여야 합니다.' },
		valid: { code: 'valid', desc: '사용 가능한 비밀번호입니다.' },
		equal: { code: 'valid', desc: '비밀번호가 일치합니다.' },
		notequal: { code: 'invalid', desc: '비밀번호가 일치하지 않습니다.' }
	},
	pwd_status: function(pw){	//영소문자, 숫자 모두 포함
		var space = /\s/g;
		var reg = /[^a-z0-9]/g;
		var digit = /[0-9]/g;
		if(pw==''){
			return this.pw.empty;
		}else if(pw.match(space)){
			return this.common.space;
		}else if(reg.test(pw)){
			return this.pw.invalid;
		}else if(!digit.test(pw) ) {
			return this.pw.lack;
		}else {
			return this.pw.valid;
		}
	},
	
	email_status: function(email){
		var space = /\s/g;
		var emailForm =   /^([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(email==''){
			return this.email.empty;
		}else if(email.match(space)){
			return this.common.space;
		}else if( !emailForm.test(email) ){
			return this.email.lack;
		}else {
			return this.email.valid;
		}
	},
	
	post_status: function(post){
		if(post==''){
			return this.post.empty;
		}else{
			return this.post.valid;
		}
	},
	
	//아이디 판별 함수 선언
	id_usable: function( data ){
		if (data == 'true') {
			return this.id.usable;
		}else{
			return this.id.unusable;
		}
	},
	
	
	id_status : function(id) { // 영 소문자, 숫자
		var reg = /[^a-z0-9]/g;
		var space = /\s/g;

		if (id == '') {
			return this.id.empty;
		} else if (id.match(space)) {
			return this.common.space;
		} else if (reg.test(id)) {
			return this.id.invalid;
		} else if (id.length < 5) { // 5문자 이상
			return this.common.min;
		} else if (id.length > 10) { // 5문자 이하
			return this.common.max;
		} else {
			return this.id.valid;
		}
	},
	
	pwd_ck_status: function(pwd_ck){
		if (pwd_ck == $('[name=pw]').val()) {
			return this.pw.equal;
		}else if(pwd_ck != $('[name=pw]').val()){
			return this.pw.notequal;
		}
	},
	
		
	
} 