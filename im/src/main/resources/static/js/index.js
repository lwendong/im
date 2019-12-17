 $(function (){
	 var _regis = $(".regis");
	 var _login = $(".login");
	 var _line = $(".line-parent");
	 var _loginArea = $(".login-area");
	 var _regisArea = $(".regis-area");
	 // var _loginAreaE = document.querySelector('#login-area');
	 // var _regisAreaE = document.querySelector('#regis-area');
	 var _loginOperation = $(".login-operation");
	 var _regisOperation = $(".regis-operation");
	 var _regisBtn = $("#regis-btn");
	 var _regisUser = $("#regis-user");
	 var _regisPassword = $("#regis-password");
	 var _regisCode = $("#regis-code");
	 var _regisCodeBtn= $("#regis-code-btn");
	 var _loginBtn = $("#login-btn");
	 var _loginUser = $("#login-user");
	 var _loginPassword = $("#login-password");
	 var isHiddenLogin = false;
	 var isHiddenRegis = false;
	 var loginOrRegis = null;
	 var isSendMail = true;

	 _regisBtn.click(function (){
	 	debugger
		 var user = _regisUser.val();
		 var password = _regisPassword.val();
		 var regisCode = _regisCode.val();
		 var regisCodeV = sessionStorage.getItem("regisCode");
		 var isUser = validatEmail(user);
		 var isPassword= validatePassword(password);
		 if(!isUser){
			 _regisUser.parent().css("border-bottom","1px red solid");
			 return;
		 }
		 if(regisCode != regisCodeV || regisCode == ""){
			 _regisCode.parent().css("border-bottom","1px red solid");
			 return;
		 }
		 if(!isPassword){
			 _regisPassword.parent().css("border-bottom","1px red solid");
			 return;
		 }
		 $.ajax({
			 type:"POST",
			 url:"/regis",
			 async:true,
			 data:{
				 "email":user,
				 "password":password
			 },
			 success(result){
			 	debugger
				 var josn = null;
			 	if( Object.prototype.toString.call(result) === "[object String]"){
					json = JSON.parse(result);
				}else {
					json = result;
				}
				 _regisBtn.css("padding-left","34px");
				 _regisBtn.css("letter-spacing","4px");
				 _regisBtn.text(json.msg);
				 if(json.success){
					 sessionStorage.setItem("userName",json.username);
					 sessionStorage.setItem("email",json.email);
					 sessionStorage.setItem("loginId",json.loginId);
				 	setTimeout(function () {
						window.location.href = "/home";
					},750)
				 }else {
					 _regisUser.parent().css("border-bottom","1px red solid");
				 }
			 }
		 });
	 });

	 _loginBtn.click(function (){
		 var user = _loginUser.val();
		 var password = _loginPassword.val();
		 var isUser = validateUersname(user);
		 var isPassword= validatePassword(password);
		 if(!isUser){
			 _loginUser.parent().css("border-bottom","1px red solid");
		 }
		 if(!isPassword){
			 _loginPassword.parent().css("border-bottom","1px red solid");
		 }

		 $.ajax({
			 type:"POST",
			 url:"/login",
			 async:true,
			 data:{
				 "email":user,
				 "password":password
			 },
			 success(result){
			 	debugger
				 var josn = null;
				 if( Object.prototype.toString.call(result) === "[object String]"){
					 json = JSON.parse(result);
				 }else {
					 json = result;
				 }
				 _loginBtn.text(json.msg);
				 if(json.success){
					 _loginBtn.css("padding-left","28px");
					 _loginBtn.css("letter-spacing","7px");
					 setTimeout(function () {
						 sessionStorage.setItem("userName",json.username);
						 sessionStorage.setItem("email",json.email);
						 sessionStorage.setItem("loginId",json.loginId);
						 window.location.href = "/home";
					 },750)

				 }else {
					 _loginBtn.css("padding-left","2px");
					 _loginBtn.css("letter-spacing","0px");
					 _loginBtn.css("width","237px");
					 _loginUser.parent().css("border-bottom","1px red solid");
					 _loginPassword.parent().css("border-bottom","1px red solid");
				 }
			 }
		 });
	 });

	 _regisCodeBtn.click(function () {
	 	if(isSendMail){
			var  time = 60;
			var toMailAccount = _regisUser.val();
			$.ajax({
				type: "POST",
				url: "/sendCode",
				async: true,
				data:{
					"toMailAccount":toMailAccount
				},
				success(result) {
					debugger
					sessionStorage.setItem("regisCode",result);
				}
			});
			isSendMail = false;
			var interval =  setInterval(function(){
				_regisCodeBtn.text(time);
				if(time < 0){
					clearInterval(interval);
					_regisCodeBtn.text("获取验证码");
					isSendMail = true;
				}
				time = time - 1;
			},1000);
	 	}
	 });
	 
	  //注册  鼠标移入事件
	 _regis.hover(function(){
		 _line.removeClass("regis-tilt-restore");
		 _line.removeClass("login-tilt-restore");
		 _regis.removeClass("regis-icon-restore");
		 _regis.removeClass("login-regis-icon-restore");
		 _login.removeClass("regis-login-icon-restore");
		 _line.addClass("regis-tilt-change");
		 _regis.addClass("regis-icon-change");
		 _login.addClass("regis-login-icon-change");
	 },function(){
		 _line.removeClass("regis-tilt-change");
		 _regis.removeClass("regis-icon-change");
		 _login.removeClass("regis-login-icon-change");
		 _line.addClass("regis-tilt-restore");
		 _regis.addClass("regis-icon-restore");
		 _login.addClass("regis-login-icon-restore");
	 });
	 
	  //登录  鼠标移入事件
	 _login.hover(function(){
		 _line.removeClass("login-tilt-restore");
		 _line.removeClass("regis-tilt-restore");
		 _login.removeClass("login-icon-restore");
		 _login.removeClass("regis-login-icon-restore");
		 -_regis.removeClass("login-regis-icon-restore");
		 _line.addClass("login-tilt-change");
		 _login.addClass("login-icon-change");
		  _regis.addClass("login-regis-icon-change");
	 },function(){
		 _line.removeClass("login-tilt-change");
		 _login.removeClass("login-icon-change");
		 _regis.removeClass("login-regis-icon-change");
		 _line.addClass("login-tilt-restore");
		 _login.addClass("login-icon-restore");
		 _regis.addClass("login-regis-icon-restore");
	 });
	 //登录 点击登录图标 登录区域显示
	 _login.click(function (){
		 loginOrRegis = "login";
		_loginArea.css("display","block");
		_loginArea.removeClass("login-loginArea-hidden");
		_loginArea.addClass("login-loginArea-display");
		_regisArea.removeClass("regis-regisArea-display");
		_regisArea.addClass("regis-regisArea-hidden");
		 _regisUser.val("");
		 _regisPassword.val("");
// 		setTimeout(function(){
// 			_regisArea.css("display","none");
// 		},1000);
	 });
	 //登录 隐藏按钮点击 登录区域隐藏
	 _loginOperation.click(function (){
		 isHiddenLogin = true;
		_loginArea.removeClass("login-loginArea-display");
		_loginArea.addClass("login-loginArea-hidden");
		 _loginUser.val("");
		 _loginPassword.val("");
	 });
	//注册 点击注册图标 注册区域显示
	 _regis.click(function (){
		loginOrRegis = "regis";
		_regisArea.css("display","block");
		_regisArea.removeClass("regis-regisArea-hidden");
		_regisArea.addClass("regis-regisArea-display");
		_loginArea.removeClass("login-loginArea-display");
		_loginArea.addClass("login-loginArea-hidden");
		 _loginUser.val("");
		 _loginPassword.val("");
// 			setTimeout(function(){
// 				_loginArea.css("display","none");
// 			},1000);
	 });
	 //注册 隐藏按钮点击 注册区域隐藏
	 _regisOperation.click(function (){
		isHiddenRegis = true;
		_regisArea.removeClass("regis-regisArea-display");
		_regisArea.addClass("regis-regisArea-hidden");
		 _regisUser.val("");
		 _regisPassword.val("");
	 });

	 $(document).on('click',function(e){
		 if(e.target.className == "regis-regisBtn-input"){
			return
		 }
		 if(e.target.className == "login-loginBtn-input"){
			 return
		 }
		 _regisUser.parent().css("border-bottom","1px #ECECF8 solid");
		 _regisPassword.parent().css("border-bottom","1px #ECECF8 solid");
		 _regisCode.parent().css("border-bottom","1px #ECECF8 solid");
		 _loginUser.parent().css("border-bottom","1px #ECECF8 solid");
		 _loginPassword.parent().css("border-bottom","1px #ECECF8 solid");
		 _regisBtn.css("padding-left","60px");
		 _regisBtn.css("letter-spacing","16px");
		 _regisBtn.text("注册");
		 _loginBtn.css("padding-left","60px");
		 _loginBtn.css("letter-spacing","16px");
		 _loginBtn.css("width","206px");
		 _loginBtn.text("登录");
	 });

	 $(document).keydown(function(event){
		 if(event.keyCode == 13){
			if(loginOrRegis == "login"){
				_loginBtn.trigger('click');
			}else {
				_regisBtn.trigger('click');
			}
		 }
	 });
		 //监听函数 动画结束后执行
// 	 _loginAreaE.addEventListener("animationed", function () { 
// 	 	if(isHiddenLogin){
// 	 		_loginArea.css("display","none");
// 			isHiddenLogin = false;
// 	 	}
// 	 }, false);
// 	 _loginAreaE.addEventListener("webkitAnimationEnd", function () { 
// 	 	if(isHiddenLogin){
// 	 		_loginArea.css("display","none");
// 			 isHiddenLogin = false;
// 	 	}
// 	 }, false);
// 	 
// 	 _regisAreaE.addEventListener("animationed", function () { 
// 	 	if(isHiddenRegis){
// 	 		_regisArea.css("display","none");
// 			isHiddenRegis = false;
// 	 	}
// 	 }, false);
// 	 _regisAreaE.addEventListener("webkitAnimationEnd", function () { 
// 	 	if(isHiddenRegis){
// 	 		_regisArea.css("display","none");
// 			isHiddenRegis = false;
// 	 	}
// 	 }, false);
	 sessionStorage.setItem("regisCode","");
	 var TimeToClear =  setInterval(function(){
		 sessionStorage.setItem("regisCode","");
	 },60000);
 });