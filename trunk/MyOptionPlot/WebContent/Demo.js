
function demo05(){
	empty();
	$('#plan_name').text('05 買權多頭價差');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY',  'CALL', nearByExcercisePrice+100);
	setting(2, 'SELL', 'CALL', nearByExcercisePrice+200);
	renewReportTable();
}

function demo06(){
	empty();
	$('#plan_name').text('06 賣權多頭價差');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'PUT', nearByExcercisePrice-100);
	setting(2, 'BUY',  'PUT', nearByExcercisePrice-200);
	renewReportTable();
}

function demo07(){
	empty();
	$('#plan_name').text('07 買權空頭價差');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice+500);
	setting(2, 'SELL','CALL', nearByExcercisePrice+200);
	renewReportTable();
}

function demo08(){
	empty();
	$('#plan_name').text('08 賣權空頭價差');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'PUT', nearByExcercisePrice+400);
	setting(2, 'SELL','PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function demo09(){
	empty();
	$('#plan_name').text('09 作多價外逆轉組合');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice+400);
	setting(2, 'SELL', 'PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function demo10(){
	empty();
	$('#plan_name').text('10 作空價外逆轉組合');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice+400);
	setting(2, 'BUY', 'PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function demo11(){
	empty();
	$('#plan_name').text('11 買進跨式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice);
	setting(2, 'BUY', 'PUT', nearByExcercisePrice);
	renewReportTable();
}

function demo12(){
	empty();
	$('#plan_name').text('12 買進勒式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice+300);
	setting(2, 'BUY', 'PUT', nearByExcercisePrice-300);
	renewReportTable();
}

function demo13(){
	empty();
	$('#plan_name').text('13 賣出跨式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice);
	setting(2, 'SELL', 'PUT', nearByExcercisePrice);
	renewReportTable();
}

function demo14(){
	empty();
	$('#plan_name').text('14 賣出勒式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice+300);
	setting(2, 'SELL', 'PUT', nearByExcercisePrice-300);
	renewReportTable();
}

function demo15(){
	empty();
	$('#plan_name').text('15 買進買權蝶式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY',  'CALL', nearByExcercisePrice-200);
	setting(2, 'SELL', 'CALL', nearByExcercisePrice);
	setting(3, 'SELL', 'CALL', nearByExcercisePrice);
	setting(4, 'BUY', 'CALL', nearByExcercisePrice+200);
	renewReportTable();
}

function demo16(){
	empty();
	$('#plan_name').text('16 買進賣權蝶式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY',  'PUT', nearByExcercisePrice-200);
	setting(2, 'SELL', 'PUT', nearByExcercisePrice);
	setting(3, 'SELL', 'PUT', nearByExcercisePrice);
	setting(4, 'BUY',  'PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function demo17(){
	empty();
	$('#plan_name').text('17 買進買權兀鷹部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice+100);
	setting(2, 'SELL', 'CALL', nearByExcercisePrice);
	setting(3, 'BUY',  'CALL', nearByExcercisePrice+400);
	setting(4, 'BUY',  'CALL', nearByExcercisePrice-300);
	renewReportTable();
}

function demo18(){
	empty();
	$('#plan_name').text('18 買進賣權兀鷹部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY',  'PUT', nearByExcercisePrice-200);
	setting(2, 'SELL', 'PUT', nearByExcercisePrice);
	setting(3, 'SELL', 'PUT', nearByExcercisePrice+100);
	setting(4, 'BUY',  'PUT', nearByExcercisePrice+300);
	renewReportTable();
}

function demo19(){
	empty();
	$('#plan_name').text('19 賣出買權蝶式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice-200);
	setting(2, 'BUY',  'CALL', nearByExcercisePrice);
	setting(3, 'BUY',  'CALL', nearByExcercisePrice);
	setting(4, 'SELL', 'CALL', nearByExcercisePrice+200);
	renewReportTable();
}

function demo20(){
	empty();
	$('#plan_name').text('20 賣出賣權蝶式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'PUT', nearByExcercisePrice-200);
	setting(2, 'BUY',  'PUT', nearByExcercisePrice);
	setting(3, 'BUY',  'PUT', nearByExcercisePrice);
	setting(4, 'SELL', 'PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function demo21(){
	empty();
	$('#plan_name').text('21 賣出買權兀鷹部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice-200);
	setting(2, 'BUY',  'CALL', nearByExcercisePrice);
	setting(3, 'BUY',  'CALL', nearByExcercisePrice+100);
	setting(4, 'SELL', 'CALL', nearByExcercisePrice+300);
	renewReportTable();
}

function demo22(){
	empty();
	$('#plan_name').text('22 賣出賣權兀鷹部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'PUT', nearByExcercisePrice-200);
	setting(2, 'BUY',  'PUT', nearByExcercisePrice);
	setting(3, 'BUY',  'PUT', nearByExcercisePrice+100);
	setting(4, 'SELL', 'PUT', nearByExcercisePrice+300);
	renewReportTable();
}

function demo23(){
	empty();
	$('#plan_name').text('23 買進買權調整蝶式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice-200);
	setting(2, 'SELL','CALL', nearByExcercisePrice);
	setting(3, 'SELL','CALL', nearByExcercisePrice);
	setting(4, 'BUY', 'CALL', nearByExcercisePrice+100);
	renewReportTable();
}

function demo24(){
	empty();
	$('#plan_name').text('24 買進賣權調整蝶式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'PUT', nearByExcercisePrice-100);
	setting(2, 'SELL','PUT', nearByExcercisePrice);
	setting(3, 'SELL','PUT', nearByExcercisePrice);
	setting(4, 'BUY', 'PUT', nearByExcercisePrice+300);
	renewReportTable();
}

function demo25(){
	empty();
	$('#plan_name').text('25 買進鐵蝴蝶部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice);
	setting(2, 'BUY',  'CALL', nearByExcercisePrice+200);
	setting(3, 'SELL', 'PUT',  nearByExcercisePrice);
	setting(4, 'BUY',  'PUT',  nearByExcercisePrice-200);
	renewReportTable();
}

function demo26(){
	empty();
	$('#plan_name').text('26 賣出鐵蝴蝶部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice);
	setting(2, 'SELL','CALL', nearByExcercisePrice+200);
	setting(3, 'BUY', 'PUT',  nearByExcercisePrice);
	setting(4, 'SELL','PUT',  nearByExcercisePrice-200);
	renewReportTable();
}

function demo31(){
	empty();
	$('#plan_name').text('31 買進組合式期貨');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice);
	setting(2, 'SELL','PUT',  nearByExcercisePrice);
	renewReportTable();
}

function demo32(){
	empty();
	$('#plan_name').text('32 賣出組合式期貨');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL','CALL', nearByExcercisePrice);
	setting(2, 'BUY', 'PUT',  nearByExcercisePrice);
	renewReportTable();
}

function demo33(){
	empty();
	$('#plan_name').text('33 買權比率價差部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice);
	setting(2, 'SELL','CALL', nearByExcercisePrice+100);
	setting(3, 'SELL','CALL', nearByExcercisePrice+100);
	renewReportTable();
}

function demo34(){
	empty();
	$('#plan_name').text('34 賣權比率價差部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'PUT', nearByExcercisePrice);
	setting(2, 'SELL', 'PUT', nearByExcercisePrice);
	setting(3, 'SELL', 'PUT', nearByExcercisePrice);
	setting(4, 'BUY',  'PUT', nearByExcercisePrice+200);
	setting(5, 'BUY',  'PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function demo35(){
	empty();
	$('#plan_name').text('35 買權逆比率價差部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'SELL', 'CALL', nearByExcercisePrice);
	setting(2, 'SELL', 'CALL', nearByExcercisePrice);
	setting(3, 'SELL', 'CALL', nearByExcercisePrice);
	setting(4, 'BUY',  'CALL', nearByExcercisePrice+200);
	setting(5, 'BUY',  'CALL', nearByExcercisePrice+200);
	setting(6, 'BUY',  'CALL', nearByExcercisePrice+200);
	setting(7, 'BUY',  'CALL', nearByExcercisePrice+200);
	setting(8, 'BUY',  'CALL', nearByExcercisePrice+200);
	renewReportTable();
}

function demo36(){
	empty();
	$('#plan_name').text('36 賣權逆比率價差部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'PUT', nearByExcercisePrice);
	setting(2, 'BUY', 'PUT', nearByExcercisePrice);
	setting(3, 'SELL','PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function demo37(){
	empty();
	$('#plan_name').text('37 買進篇空跨式部位');
	var nearByExcercisePrice = getNearByExcercisePrice();
	setting(1, 'BUY', 'CALL', nearByExcercisePrice+200);
	setting(2, 'BUY', 'PUT', nearByExcercisePrice+200);
	setting(3, 'BUY', 'PUT', nearByExcercisePrice+200);
	renewReportTable();
}

function empty(){
	$('#plan_name').text('');
	autoReCal = 0;
	for(var i=1; i<=8; i++){
		var obj1Id = 'buy_sell_' + i + '_select';
		var obj2Id = 'call_put_' + i + '_select';
		var obj3Id = 'excercise_price_' + i + '_select';
		$('#'+obj1Id).val('');
		$('#'+obj2Id).val('');
		$('#'+obj3Id).val('');
		renewPremium(i);
	}
}

function setting(idx, buySale, callPut, price){
	$('#buy_sell_'+idx+'_select').val(buySale);
	$('#call_put_'+idx+'_select').val(callPut);
	$('#excercise_price_'+idx+'_select').val(price);
	renewPremium(idx);
}