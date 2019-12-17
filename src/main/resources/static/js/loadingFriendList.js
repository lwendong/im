debugger
var eng = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
$.ajax({
	type:"POST",
	url:"/getFriends",
	async:true,
	success(result) {
		dispose(result);
	}
});

// var arr = new Array();
	// var arr2 = new Array();
	// var eng = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
	// for(var a=0;a<26;a++){
	// 	var num = Math.random()*10;
	// 	arr2.length = 0;
	// 	for (var i=0;i<num;i++){
	// 		var chatName = "这是第"+(a+1)+"个块里的第"+i+i+i+i+i+i+i+i+i+"个测试用的 好友列表";
	//
	// 		// 最近聊天信息 模板
	// 		var _chatListInn = `<div id=${"friend"+a+i} class="right-area-friends-inn-inn">
	// 								<div id=${"friendPhoto"+a+i} class="right-area-friends-inn-inn-photo"></div>
	// 								<div id=${"friendName"+a+i} class="right-area-friends-inn-inn-name">${chatName}</div>
	// 							</div>`;
	// 		arr2[i] = _chatListInn;
	// 	}
	// 	var listStr = arr2.join(" ");
	// 	var _chatListOut = `<div class="right-area-friends-inn">
	// 							<div class="right-area-friends-inn-eng">${eng[a]}</div>
	// 								${listStr}
	// 							</div>
	// 						</div>`;
	// 	arr[a] = _chatListOut;
	// }
	// var listStr = arr.join(" ");
	// postMessage(listStr);

function dispose(result) {
	var josn = null;
	if( Object.prototype.toString.call(result) === "[object String]"){
		json = JSON.parse(result);
	}else {
		json = result;
	}
	debugger
	var array = json.data;
	for(var b=0;b<array.length;b++){
		var engVal = eng[a];
		var arrayVal = array[b];
		//最近聊天信息 模板
		var _chatListInn = `<div id=${arrayVal.friendId} class="right-area-friends-inn-inn">
								<div id=${arrayVal.friendId+i} class="right-area-friends-inn-inn-photo"></div>
								<div id=${arrayVal.friendId+i} class="right-area-friends-inn-inn-name">${chatName}</div>
							</div>`;

	}
}

function S4() {
   return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}
function guid() {
   return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
}