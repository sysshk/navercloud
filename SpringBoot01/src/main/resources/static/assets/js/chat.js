// WebSocket을 이용한 Chatting 만들기

// 1. 필요한 변수 미리 준비하기

const einput = document.getElementById("email");
const tinput = document.getElementById("text");

const btn = document.getElementById("btn");

const div = document.getElementById("result");


// 2. WebSocket과 연결
// 전체 경로로만 연결이 가능하다 자스랑연결할 때

let room = "dcx"; // 고유한 방 id
let url = "ws://localhost:8088/chat/" + room;

// 채팅방 >> path variable로 방 id 보내기

// 2-1 웹소켓 객체 생성, 서버와 연결하는 작업
let websocket = new WebSocket(url);

// 3 서버로 메세지 보내기

btn.addEventListener("click", sendMessage)

function sendMessage(){
	// 보내줄 데이터를 담을 객체
	chat = {
		"email" : einput.value,
		"text" : tinput.value
		
	}
	
	// javascipt object --> json string
	var json = JSON.stringify(chat);
	
	// .send("text") : 서버로 메세지 전송
	websocket.send(json);
	
	print("me", chat);
}

// 4. 콜백함수 지정
// 특정 상황에서만 자동으로 실행되는 함수

function onOpen(){
	
}

function onClose(){
	
}

function onMessage(msg){
	// 받은 메세지가  매개변수에 자동으로 담김
	console.log(msg.data);
	
	
	// json string을 다시 javascript object로 바꿈
	var json = JSON.parse(msg.data);
	
	print("other", json);
}

// 웹소켓과 연결됐을 때
websocket.onopen = onOpen; // onopen 키값에 onOpen 함수를 넣어줌

// 웹소켓이 닫혔을 때
websocket.onclose = onClose;

// 메세지를 받았을 때
websocket.onmessage = onMessage;


// 5. 출력
function print(who, json){
	div.innerHTML += `
		<p class="${who}">
			${json.email} : ${json.text}
		</p>
	
	
	`;
}




