
// Ajax ��û �Լ�
function startRequest(){
	// �� ��ü ��������
	var frm = document.frm;
	
	// XMLHttpRequset ��ü ����
	createXMLHttpRequest();
	
	// �񵿱� �ݹ� �Լ� ����
	xhr.onreadystatechange = ajaxOk;
	
	// ajax ��û URL �� �Ķ���� ����
	var url = "test.do?mode=ajax&id=" + frm.id.value;
	
	// ��û ���� ����
	xhr.open("get", url, true);
	
	// ��û ����
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