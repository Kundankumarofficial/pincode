function ajaxPinCodeRequest()
{
    xmlHttp = new XMLHttpRequest();
    //var url="requestHandler.jsp?lat="+lat+"&lng="+lng;
    var url="requestPincode?lat="+lat+"&lng="+lng;
    alert(url);
    if(xmlHttp) {
        xmlHttp.open("GET",url, true);
        xmlHttp.onreadystatechange  = ajaxPinCodeResponse;
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}

function ajaxPinCodeResponse()
{
    alert("Ready state changed: "+xmlHttp.readyState);
    if (xmlHttp.readyState == 4) {
        alert(":"+xmlHttp.responseText+":");
    }
}