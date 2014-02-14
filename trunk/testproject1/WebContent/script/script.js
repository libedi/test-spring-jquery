var xhr;

// XMLHttpRequest 객체 생성
function createXMLHttpRequest(){
	if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	} else{
		xhr = new XMLHttpRequest();
	}
}

