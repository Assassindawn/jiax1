//轮播
var box = document.getElementById("box");
var aUl = box.getElementsByTagName("ul");
var aPic = aUl[0].getElementsByTagName("li");
var aDot = aUl[1].getElementsByTagName("li");
var aBtn = aUl[2].getElementsByTagName("li");
var num = 0;
var timer = null;

var arrUrl = [_contextPath+"/statics/plugin/setup/images/carousel_1.jpg",_contextPath+"/statics/plugin/setup/images/carousel_2.jpg",_contextPath+"/statics/plugin/setup/images/carousel_3.jpg",_contextPath+"/statics/plugin/setup/images/carousel_4.jpg"];

var str="";
var dot = "";
for(var i=0;i<arrUrl.length;i++){
	str += "<li style='background-size:100% 100%;width:100%;height:660px;background:url("+arrUrl[i]+") no-repeat;'></li>"
	dot += "<li></li>";
}
aUl[0].innerHTML = str;
aUl[1].innerHTML = dot;

for(var i=0;i<aDot.length;i++){
	aDot[i].index = i;
	aDot[i].onclick = function (){
		num = this.index;
		show();
	}
}

aBtn[1].onclick = get;

function get(){
	num++;
	num %= aPic.length;
	show();
}
aBtn[0].onclick = function (){
	num--;
	if( num < 0 ){ num = aPic.length-1; }
	show();
}
function show(){
	for(var k=0;k<aDot.length;k++){
		aDot[k].className = "";
		aPic[k].className = "";
	}
	aDot[num].className = "first";
	aPic[num].className = "opa";
}
show();
function autoPlay(){
	clearInterval( timer );
	timer = setInterval(get,5000);
}
autoPlay();
box.onmouseover = function (){
	clearInterval( timer );
}
box.onmouseout = autoPlay;

//启动等待
function _startLoading(str){
    $.bootstrapLoading.start({ loadingTips: str });
}
//结束等待
function _endLoading(){
    $.bootstrapLoading.end();
}



