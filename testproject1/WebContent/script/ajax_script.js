
// Ajax 요청 함수
function startRequest(){
	// 폼 객체 가져오기
	var frm = document.frm;
	
	// XMLHttpRequset 객체 생성
	createXMLHttpRequest();
	
	// 비동기 콜백 함수 설정
	xhr.onreadystatechange = ajaxOk;
	
	// ajax 요청 URL 및 파라미터 설정
	var url = "test.do?mode=ajax&id=" + frm.id.value;
	
	// 요청 정보 설정
	xhr.open("get", url, true);
	
	// 요청 전송
	xhr.send(null);
}

function ajaxOk(){
	if(xhr.readyState == 4){
		if(xhr.status == 200){
			var txt = xhr.responseText;
			document.getElementById("ajaxok").innerHTML = txt;
		}
	}
}