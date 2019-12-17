	var arr = new Array();
	for (var i=0;i<12;i++){
		var chatName = "这是第"+i+i+i+i+i+i+i+i+i+i+"个测试用的 最近聊天";
		var chatInfo = "这是第"+i+i+i+i+i+i+i+i+i+i+i+"个测试用的 最近聊天信息";
		
		// 最近聊天信息 模板
		var _chatList = `<div id=${guid()} class='right-area-list-inn'>
							<div id=${guid()} class="head-photo"></div>
							<div id=${guid()} class="chat-info-name">${chatName}</div>
							<div id=${guid()} class="chat-info-info" >${chatInfo}</div>
							<div id=${guid()} class="chat-info-time "+${"borderBottom"}>23:55</div>
						</div>`;
		arr[i] = _chatList;
	}
	var listStr = arr.join(" ");
	postMessage(listStr);
	
function S4() {
   return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}
function guid() {
   return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
}