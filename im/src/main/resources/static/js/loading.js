//最近聊天列表加载
function loadingChat(_list){
	var loginId = sessionStorage.getItem("loginId");
	$.ajax({
		type:"POST",
		url:"/getChat",
		async:true,
		data:{
			"loginId":loginId
		},
		success(result) {
			var divStr = disposeChat(result);
			_list.html(divStr);
		}
	});
	//暂时不用 worker 的方式加载列表 先注掉
	// var worker = new Worker("js/loadingChatList.js");
	// worker.onmessage = function(event){
	// 	_list.html(event.data);
	// }
}

//好友列表加载
function loadingFriends(_list){
	debugger
	var loginId = sessionStorage.getItem("loginId");
	$.ajax({
		type:"POST",
		url:"/getFriends",
		async:true,
		data:{
			"loginId":loginId
		},
		success(result) {
			var divStr = disposeFriends(result);
			_list.html(divStr);
		}
	});

}

function disposeFriends(result) {
	//var eng = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
	var arr = new Array();
	var arr2 = new Array();
	var josn = null;
	var pySzmZc = "";
	if( Object.prototype.toString.call(result) === "[object String]"){
		json = JSON.parse(result);
	}else {
		json = result;
	}
	debugger
	var data = json.data;
	var py  = json.py;
	for(var p=0;p<py.length;p++){
		for(var d=0;d<data.length;d++){
			debugger
			var eng = py[p];
			var aData = data[d];
			if(py[p] == aData.pySzm){
				var id = aData.friendId;
				// 最近聊天信息 模板
				var _chatListInn = `<div id=${id} class="right-area-friends-inn-inn">
										<div id=${id+d} class="right-area-friends-inn-inn-photo"></div>
										<div id=${id+d} class="right-area-friends-inn-inn-name">${aData.userName}</div>
										<input value=${aData.nickName} hidden="hidden" class="right-area-friends-inn-inn-nick-name">
										<input value=${aData.signTure} hidden="hidden" class="right-area-friends-inn-inn-signture">
										<input value=${aData.friendId} hidden="hidden" class="right-area-friends-inn-inn-friendId">
									</div>`;
				arr2[d] = _chatListInn;
			}
		}
		var listStr = arr2.join(" ");
		var _chatListOut = `<div class="right-area-friends-inn">
									<div class="right-area-friends-inn-eng">${py[p]}</div>
										${listStr}
									</div>
								</div>`;
		arr[p] = _chatListOut;
		arr2.length = 0;
	}
	var listStr = arr.join(" ");
	return listStr;
}
function disposeChat(result) {

}