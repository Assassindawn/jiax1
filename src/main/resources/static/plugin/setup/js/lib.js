/*
function bsStep(i) {
	$('.step').each(function() {
		var a, $this = $(this);
		if(i > $this.find('li').length) {
			console.log('您输入数值已超过步骤最大数量' + $this.find('li').length + '！！！');
			a=$this.find('li').length;
		} else if(i == undefined && $this.data('step') == undefined) {
			a = 1
		} else if(i == undefined && $this.data('step') != undefined) {
			a = $(this).data('step');
		} else {
			a = i
		}
		$(this).find('li').removeClass('active');
		$(this).find('li:lt(' + a + ')').addClass('active');
	})
}
*/

function bsStep(idName,i) {
	var a, setup = $("#"+idName);
	if(i > setup.find('li').length) {
		console.log('您输入数值已超过步骤最大数量' + setup.find('li').length + '！！！');
		a=setup.find('li').length;
	} else if(i == undefined && setup.data('step') == undefined) {
		a = 1;
	} else if(i == undefined && setup.data('step') != undefined) {
		a = setup.data('step');
	} else {
		a = i;
	}
	setup.find('li').removeClass('active');
	setup.find('li:lt(' + a + ')').addClass('active');
}