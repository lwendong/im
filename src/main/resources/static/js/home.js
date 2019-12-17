$(function(){
	var _searchIcon = $('.right-area-inn-top-search');//右侧查询区域 搜索框 最外层容器
	var _inn = $('.right-area-inn-top-inn');//右侧查询区域 搜索框 容器
	var _clean = $('.right-area-inn-top-search-clean');//右侧查询区域 搜索框 清楚内容图标
	var _searchInput = $('.right-area-inn-top-search-input');//右侧查询区域 搜索框
	var _rightArea = $('.right-area');//右侧操作区域
	var _charList = $('#chatList');//右侧 最近聊天列表
	var _friensList = $('#friensList');//右侧 最近聊天列表
	var _chat = $('#chat');//左侧 聊天图标
	var _friends = $('#friends');//左侧 好友列表图标
	var _send = $('#send');//发送按钮
	var _chatInput = $("#chatInput");
	var _chatArea = $('#chatArea');
	var _friendArea = $('#friendArea');
	var _mask = $('#mask');//遮罩
	var _addLoginNameBtn = $('#addLoginNameBtn');//设置昵称按钮
	var _addLoginNameInput = $('.add_login_name_input');//设置昵称input
	var _chatOldId = null;
	var _friendsOldId = null;
	var _chatloadingOver = false;
	var _friendsloadingOver = false;
	debugger
	var userName = sessionStorage.getItem("userName");
	if(!userName || typeof(userName)!="undefined" ){
		_mask.css("height",$(document).height());
		_mask.css("width",$(document).width());
		_mask.show();
	}

	//点击聊天图标
	_chat.click(function(e){
		_friends.css('background','');
		_friends.css('color','#B6BCD6');
		_chat.css('background','#F5F5F5');
		_chat.css('color','#535353');
		_friensList.css('display','none');
		_friendArea.css('display','none');
		_charList.css('display','inline-block');
		_chatArea.css('display','inline-block');
		if(!_chatloadingOver){
			loadingChat(_charList);
		}
		_chatloadingOver=true;
	});
	
	//点击好友列表图标
	_friends.click(function(){
		_chat.css('background','');
		_chat.css('color','#B6BCD6');
		_friends.css('background','#F5F5F5');
		_friends.css('color','#535353');
		_charList.css('display','none');
		_chatArea.css('display','none');
		_friensList.css('display','inline-block');
		_friendArea.css('display','inline-block');
		if(!_friendsloadingOver){
			loadingFriends(_friensList);
		}
		_friendsloadingOver=true;
	});
	
	//页面加载默认打开 最近聊天列表
	_chat.trigger('click');
	
	//点击搜索
	_searchInput.click(function (){
		_inn.css('background','#FFFFFF');
		_searchInput.css('background','#FFFFFF');
		_clean.css('display','inline-block');
	});
	
	//点击搜索框清除按钮
	_clean.click(function(){
		_searchInput.val('');
		_searchInput.focus();
	});

	_addLoginNameBtn.click(function () {
		var userName = _addLoginNameInput.val();
		$.ajax({
			type:"POST",
			url:"/addUserName",
			async:true,
			data:{
				"userName":userName,
				"email":sessionStorage.getItem("email")
			},
			success() {
				_mask.hide();
			}
		});
	});

	
	$(document).on('click',function(e){  
	   //点击除搜索图标 搜索框 搜索清除按钮意外的地方 清除搜索框相关
	  if( !(e.target.className == 'right-area-inn-top-search-input' || e.target.className == 'fa fa-times-circle-o right-area-inn-top-search-clean' ||  e.target.className == 'fa fa-search right-area-inn-top-search')){  
		_inn.css('background','#DBD9D8');
		_searchInput.css('background','#DBD9D8');
		_clean.css('display','none');
		_searchInput.val('');
	  }
	  //点击最近聊天 
	  if(e.target.className == 'head-photo' || e.target.className == 'chat-info-name' || e.target.className == 'chat-info-info' || e.target.className == 'chat-info-time' || e.target.className == 'right-area-list-inn'){
		  var _node = null;
		  if(e.target.className == 'right-area-list-inn'){
			  if(_chatOldId != null){
				_chatOldId.css("background","#EEEAE8");
			  }
			_chatOldId = $(document.getElementById(e.target.id));
			_node = _chatOldId;
			_chatOldId.css("background","#C5C5C5");
		  }else{
			  var _id  = $(document.getElementById(e.target.id));
			  if(_chatOldId != null){
			  	_chatOldId.css("background","#EEEAE8");
			  }
			  _chatOldId = _id.parent();
			  _node = _chatOldId;
			  _chatOldId.css("background","#C5C5C5");
		  }
		  var _name = _node.children(".chat-info-name").text();
		  var _chatTemplate = `<div class="center-operation-area">
								<div class="center-operation-area-top">
									<div class="center-operation-area-font">${_name}</div>
									<i class="fa fa-ellipsis-h center-operation-area-operation"></i>
								</div>
								<!-- 聊天区域 -->
								<div class="center-operation-area-chat">
									
								</div>
								<div class="center-operation-area-input-div">
									<textarea id="chatInput" class="center-operation-area-input" autofocus="autofocus"></textarea>
									<div id="send" class="center-operation-area-send-btn" onmouseover="sendOnmouseover(this)" onmouseout="sendOnmouseout(this)" onclick="sendClick(chatInput)">发送</div>
								</div>
							</div>`;
		_chatArea.css("display","inline-block");
		_chatArea.html(_chatTemplate);
		}
		
		 //点击好友列表
		if(e.target.className == 'right-area-friends-inn-inn' || e.target.className == 'right-area-friends-inn-inn-photo' || e.target.className == 'right-area-friends-inn-inn-name'){
			var _node = null;
			if(e.target.className == 'right-area-friends-inn-inn'){
				  if(_friendsOldId != null){
					_friendsOldId.css("background","#EEEAE8");
				  }
				_friendsOldId = $(document.getElementById(e.target.id));
				_node = _friendsOldId;
				_friendsOldId.css("background","#C5C5C5");
			}else{
				  var _id  = $(document.getElementById(e.target.id));
				  if(_friendsOldId != null){
					_friendsOldId.css("background","#EEEAE8");
				  }
				  _friendsOldId = _id.parent();
				  _node = _friendsOldId;
				  _friendsOldId.css("background","#C5C5C5");
			}
			
			var _name = _node.children(".right-area-friends-inn-inn-name").text();
			var _nickName = _node.children(".right-area-friends-inn-inn-nick-name").val();
			var _signTure = _node.children(".right-area-friends-inn-inn-signture").val();
			var _friendId = _node.children(".right-area-friends-inn-inn-friendId").val();
			var _friendTemplate = `<div class="center-area-friend-inn">
										<div class="center-area-friend-inn-name">${_nickName}</div>
										<div class="center-area-friend-inn-sex"></div>
										<div class="center-area-friend-inn-photo"></div>
										<div class="center-area-friend-inn-signature">${_signTure}</div>
										<div class="center-area-friend-inn-area">
											<div class="center-area-friend-inn-area-remake">
												<div class="center-area-friend-inn-area-remake-font">备注</div>
												<input id="remakeInput" class="center-area-friend-inn-area-remake-input" value=${_name} placeholder="点击添加备注" onclick="changeRemake(this)" onblur="ablur(this)"/>
											</div>
											<div class="center-area-friend-inn-area-region">
												<div class="center-area-friend-inn-area-region-font">地区</div>
												<div class="center-area-friend-inn-area-region-val">辽宁 盘锦</div>
											</div>
											<div class="center-area-friend-inn-area-imid">
												<div class="center-area-friend-inn-area-imid-font">im号</div>
												<div id="imid" class="center-area-friend-inn-area-imid-val">${_friendId}</div>
											</div>
										</div>
										<div id="jumpSendBtn" class="center-area-friend-inn-area-send" onclick="friendSendMsg(imid,chat)">发送信息</div>
									</div> `;
			_friendArea.css("display","inline-block");
			_friendArea.html(_friendTemplate);
		}
		
	}); 
	
});
function sendOnmouseover(e){
	e.style.background="#129611";
	e.style.color="#FFFFFF";
}
function sendOnmouseout(e){
	e.style.background="";
	e.style.color="";
}
function sendClick(e){
	var _val = e.value;
	var py = zw2py(_val);
	e.value = py;
}
function changeRemake(e){
	e.placeholder = "";
	e.style.borderBottom = "1px #000000 solid";
	e.style.cursor = "text";
}
function ablur(e){
	e.placeholder = "点击添加备注";
	e.style.borderBottom = "";
}
function  friendSendMsg(e,v) {
	debugger
	var imid = e.innerText;
	var loginId = sessionStorage.getItem("loginId");
	sessionStorage.setItem("chatId",imid);
	$.ajax({
		type:"POST",
		url:"/initChat",
		async:true,
		data:{
			"fromUserId":loginId,
			"toUserId":imid
		},
		success() {
			$(document.getElementById(v.id)).trigger('click');
		}
	});
}