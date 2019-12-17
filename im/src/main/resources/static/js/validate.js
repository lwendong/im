//验证用户名
function validateUersname(username) {
	if(username == ""){
		return false;
	}
	var reg = /^[a-zA-Z0-9_]{2,20}$/;
	return reg.test(username);
}
//验证手机号
function validateTel(tel) {
	if(tel == ""){
		return false;
	}
	var reg = /^[1-9]{2}[0-9]{9}$/;
	return reg.test(tel);
}
//验证邮箱
function validatEmail(email){
	if(email == ""){
		return false;
	}
	var reg=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	return reg.test(email);
}
//验证密码
function validatePassword(password){
	if(password == ""){
		return false;
	}
	var reg=/^[a-zA-Z0-9]{4,20}$/
	return reg.test(password);
}
