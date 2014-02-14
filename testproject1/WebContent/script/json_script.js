function jsonTest(){
	alert("start");
	createXMLHttpRequest();
	var frm = document.frm;
	xhr.onreadystatechange = jsonOk;
	var url = "test.do?mode=json&id=" + frm.id.value;
	xhr.open("get", url, true);
	xhr.send(null);
}

function jsonOk(){
	if(xhr.readyState == 4){
		if(xhr.status == 200){
			var jsonData = eval('(' + xhr.responseText + ')');
			alert(jsonData.obj);
			alert(jsonData.arr);
		}
	}
}