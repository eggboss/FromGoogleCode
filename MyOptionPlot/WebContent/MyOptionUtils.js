/*********************************************************
 * 
 *
 *********************************************************/
var opym = getNewestOptionYearMonth();
/**
 * 取得今日的年月，若已超過本月結算日，則會取下個月的
 */
function getNewestOptionYearMonth(){
	var today = new Date();
	if(today.getDate() < getThirdWednesdate(today)){ // 是否在本月結算之前
		return today.getFullYear() + fill(today.getMonth()+1, 'left', '00', 2);
	}else if(today.getDate() == getThirdWednesdate(today) && today.getHours()<14){
		return today.getFullYear() + fill(today.getMonth()+1, 'left', '00', 2);
	}else{
		var nextMonth = new Date(today.getFullYear(), today.getMonth()+1, 1);
		return nextMonth.getFullYear() + fill(nextMonth.getMonth()+1, 'left', '00', 2);
	}
}

/*
function getNewestOptionMonth(){
	var today = new Date();
	if(today.getDate() <= getThirdWednesdate(today)){ // 是否在本月結算之前
		return fill(today.getMonth()+1, 'left', '00', 2);
	}else{
		var nextMonth = new Date(today.getFullYear(), today.getMonth()+1, 1);
		return fill(nextMonth.getMonth()+1, 'left', '00', 2);
	}
}*/

function getNextOptionYearMonth(){
	var today = new Date();
	if(today.getDate() < getThirdWednesdate(today)){ // 是否在本月結算之前
		if(today.getMonth()==11){
			return (today.getFullYear()+1) + '01';
		}else{
			return today.getFullYear() + fill(today.getMonth()+1+1, 'left', '00', 2);
		}
	}else if(today.getDate() == getThirdWednesdate(today) && today.getHours()<14){
		if(today.getMonth()==11){
			return (today.getFullYear()+1) + '01';
		}else{
			return today.getFullYear() + fill(today.getMonth()+1+1, 'left', '00', 2);
		}
	}else{
		var nextMonth = new Date(today.getFullYear(), today.getMonth()+1, 1);
		return nextMonth.getFullYear() + fill(nextMonth.getMonth()+1+1, 'left', '00', 2);
	}
}
/*
function getNextOptionMonth(){
	var today = new Date();
	if(today.getDate() <= getThirdWednesdate(today)){ // 是否在本月結算之前
		return fill(today.getMonth()+1+1, 'left', '00', 2);
	}else{
		var nextMonth = new Date(today.getFullYear(), today.getMonth()+1, 1);
		return fill(nextMonth.getMonth()+1+1, 'left', '00', 2);
	}
}*/

/**
 * 取得inputDate所在年月的第三個星期三的日期
 * @param inputDate
 * @returns {Number}
 */
function getThirdWednesdate(inputDate){
	//var today = new Date();
	var firstDateOfThisMonth = new Date(inputDate.getFullYear(), inputDate.getMonth(), 1);
	var firstDay = firstDateOfThisMonth.getDay(); // 取得星期幾，會是 0 ~ 6 的值，0:星期日，1:星期一..6:星期六
	if(firstDay<=3){
		return 1 + 14 + (3 - firstDay);
	}else{
		return 1 + 21 + (3 - firstDay);
	}
}

function getFirstDay(inputDate){
	// 取得這個第一天是星期幾
	var firstDayOfThisMonth = new Date(inputDate.getFullYear(), inputDate.getMonth(), 1).getDay();
	return firstDayOfThisMonth;
}

function getLastDate(inputDate){
	// 取得這個第一天是星期幾
	var lastDayOfThisMonth = new Date(inputDate.getFullYear(), inputDate.getMonth()+1, 0).getDate();
	return lastDayOfThisMonth;
}

function getOptionWeekOfMonth(inputDate){
	//var inputDate = new Date();
	var firstDayOfThisMonth = getFirstDay(inputDate); // 本月第一天星期幾
	var lastDate = getLastDate(inputDate); // 本月最後一天幾號
	var thisDate = inputDate.getDate(); // 今天幾號
	var thisWeekOfMonth = Math.ceil((thisDate-(7-firstDayOfThisMonth))/7)+1; // 今天是第周
	var thisWeek = inputDate.getDay(); // 取得星期幾，會是 0 ~ 6 的值，0:星期日，1:星期一..6:星期六
	var thisMonth = fill(inputDate.getMonth()+1, 'left', '00', 2);
	//alert(whatWeek + month);
	
	// 判斷是否為最後一周
	//alert(lastDate-thisDate+(thisWeek-3));
	if(thisWeek>3){
		if(thisWeekOfMonth!=1){
			if((lastDate-thisDate+(thisWeek-3))<7){
				// 星期三過後剩不到7天，即下次結算已不在本月
				return 1 + fill((inputDate.getMonth()+1)%12+1, 'left', '00', 2);
			}else{
				return (thisWeekOfMonth+1) + thisMonth;
			}
		}else{
			return (thisWeekOfMonth+1) + thisMonth;
		}
	}else{
		return thisWeekOfMonth + thisMonth;
	}
}

/**
 * 填充
 * @param source 原始物件
 * @param side left:填在左邊；right:填在右邊
 * @param fillString 要填入的字串
 * @param totalLength 填完後的總長度
 * @returns
 */
function fill(source, side, fillString, totalLength){
	if(side=='right'){
		source = source + fillString;
		return source.substr(0, totalLength);
	}else{
		source = fillString + source;
		return source.substring(source.length-totalLength, source.length);
	}
	// String.substr(N1,N2) ：從指定的位置(N1)截取指定長度(N2)的字串。
	// String.substring(N1,N2) ：從指定的位置(N1)到指定的位置(N2)的字串。
}


var htmlObj;
var taiex;
var autoReCal = 0;
var targetAmtArray = new Array();
var callAmtArray = new Array();
var putAmtArray = new Array();

function getTAIEX(){
	//var $find = $(htmlObj).find(".real-plus-tit").next();
	//return $find.html().substr(0, 4);
	return taiex;
}

function getNearByExcercisePrice(){
	return Math.round(getTAIEX()/100) * 100;
}

function initOptionDataArray(htmlObj){
	var $find = $(htmlObj).find("tbody");
	$find.each(function( index ) {
		if(index==1){
			var $find2 = $(this).find("tr");
			$find2.each(function( index2 ) {
				if(index2 > 0){
					targetAmtArray[index2-1] = $(this).find("td:eq(7)").text().trim();
					callAmtArray[index2-1] = $(this).find("td:eq(2)").text().trim();
					putAmtArray[index2-1] = $(this).find("td:eq(10)").text().trim();
					//console.log( index2 + ": " + $(this).find("td:eq(7)").text() + ", call: " + $(this).find("td:eq(2)").text() + ", put: " + $(this).find("td:eq(10)").text() );
				}
			});
		}
	});
	$find = $(htmlObj).find(".real-plus-tit").next();
	taiex = $find.html().substr(0, 4);
}

function renewData(){
	//var yearMonth = getNewestOptionYearMonth();
	//console.log('yearMonth=' + yearMonth);
	$.ajax({
		//url:"http://tw.screener.finance.yahoo.net/future/aa03?opmr=optionfull&opcm=WTXO&opym=201307",
		url:"WTXO_"+opym+".html",
		//url:"updateWTXO.jsp?opym=" + opym,
		//url:"NewMarket.html",
		async:false,
		type: 'get',
		dataType:'text',
		context: document.body,
		cache: false,
		success: function(data){
	        htmlObj = data;
	        //alert(data);
	        // init
	        initOptionDataArray(htmlObj);
	    },
	    error: function (xhr, ajaxOptions, thrownError){
	    	//console.log('status='+xhr.status);
	        //console.log('responseText='+xhr.responseText);
	        //console.log('thrownError='+thrownError);
	    }
	});
}

function renewData_cnyes(){
	// http://www.cnyes.com/twoption/GetOptionDataSource.aspx?stock=TXO&tradedate=201308
	$.ajax({
		url:"updateWTXO_cnyes.jsp?tradedate=" + opym,
		async:false,
		type: 'get',
		dataType:'text',
		context: document.body,
		cache: false,
		success: function(data){
	        htmlObj = data;
	        var $find = $(htmlObj).find("body > div:eq(1) > table > tbody >tr");
			$find.each(function( index2 ) {
				targetAmtArray[index2] = $(this).find("td:eq(6)").text().trim();
				callAmtArray[index2] = $(this).find("td:eq(2)").text().trim();
				putAmtArray[index2] = $(this).find("td:eq(9)").text().trim();
				//console.log( index2 + ": " + targetAmtArray[index2] + ", call: " + callAmtArray[index2] + ", put: " + putAmtArray[index2] );
			});
	    	$find = $(htmlObj).find("body > div:eq(1) > table > tbody >tr");
	    	taiex = $find.html().substr(0, 4);
	    },
	    error: function (xhr, ajaxOptions, thrownError){
	    	//console.log('status='+xhr.status);
	        //console.log('responseText='+xhr.responseText);
	        //console.log('thrownError='+thrownError);
	    }
	});
}

function renewData_yam(){
	// http://www.cnyes.com/twoption/GetOptionDataSource.aspx?stock=TXO&tradedate=201308
	$.ajax({
		url:"updateWTXO_yam.jsp?pmonth=" + opym,
		async:false,
		type: 'get',
		dataType:'text',
		context: document.body,
		cache: false,
		success: function(data){
	        htmlObj = data;
	        //alert(data)
	        var arrIdx = 0;
	        /*
	        var $find = $(htmlObj).find("body > div:eq(1) > div > div > div > table:eq(5) > tbody > tr");
			$find.each(function( index2 ) {
				if(index2>=2){
					targetAmtArray[arrIdx] = $(this).find("td:eq(5) > b > a").text().trim();
					callAmtArray[arrIdx] = $(this).find("td:eq(2)").text().trim();
					putAmtArray[arrIdx] = $(this).find("td:eq(8)").text().trim();
					alert( arrIdx + ": " + targetAmtArray[arrIdx] + ", call: " + callAmtArray[arrIdx] + ", put: " + putAmtArray[arrIdx] );
					arrIdx++;
				}
			});
			*/
	    	$find = $(htmlObj).find("#main_left > div > table:eq(4) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr:eq(1) > td:eq(1)");
	        //$find = $(htmlObj).find('//div[@id="main_left"]/div/table[5]/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]');
	    	alert($find.text());
	    	taiex = $find.text().substr(0, 4);
	    	//alert(taiex);
	    },
	    error: function (xhr, ajaxOptions, thrownError){
	    	//console.log('status='+xhr.status);
	        //console.log('responseText='+xhr.responseText);
	        //console.log('thrownError='+thrownError);
	    }
	});
}

function renewData2(){
	//var month = getNewestOptionMonth();
	var month = opym.substr(4, 2);
	//console.log('month=' + month);
	$.ajax({
		//url:"updateWTXO_capital.jsp?Sname=" + 'TXO'+ month +';台選' + month,
		url:"updateWTXO_capital.jsp",
		async:false,
		//type: 'get',
		dataType:'text',
		context: document.body,
		cache: false,
		success: function(data){
			htmlObj = data;
	        // 指數
	        var $find = $(htmlObj).find("div.dr_content > table:eq(1) > tbody > tr > td:eq(3) > table > tbody > tr > td");
	    	taiex = $find.html().replace(',','').substr($find.html().indexOf("</font>")+7, 4);
	        // 買權
			var $find2 = $(htmlObj).find("div.dr_content > table:eq(2) > tbody > tr > td:eq(0) > table > tbody > tr");
			var arrInx = 0;
			$find2.each(function( index ) {
				if(index >= 5){
					var targetAmt = $(this).find("td:eq(6)").text().trim().replace(',','');
					var callAmt = $(this).find("td:eq(2)").text().trim().replace(',','');
					if(targetAmt!=''){
						targetAmtArray[arrInx] = targetAmt;
						callAmtArray[arrInx] = callAmt;
						arrInx++;
					}
				}
			});
			// 賣權
			arrInx = 0;
			var $find3 = $(htmlObj).find("div.dr_content > table:eq(2) > tbody > tr > td:eq(1) > table > tbody > tr");
			$find3.each(function( index ) {
				if(index >= 5){
					var putAmt = $(this).find("td:eq(3)").text().trim().replace(',','');
					if(putAmt!=''){
						putAmtArray[arrInx] = putAmt;
						arrInx++;
					}
				}
			});
	    },
	    error: function (xhr, ajaxOptions, thrownError){
	    	//console.log('status='+xhr.status);
	        //console.log('responseText='+xhr.responseText);
	        //console.log('thrownError='+thrownError);
	    }
	});
}

function renewData_capital(param){
	//var month = getNewestOptionMonth();
	//var month = opym.substr(4, 2);
	//console.log('month=' + month);
	$.ajax({
		url:"updateWTXO_capital_w.jsp?" + param,
		async:false,
		//type: 'get',
		dataType:'text',
		context: document.body,
		cache: false,
		success: function(data){
			htmlObj = data;
	        // 指數
	        var $find = $(htmlObj).find("div.dr_content > table:eq(1) > tbody > tr > td:eq(3) > table > tbody > tr > td");
	    	taiex = $find.html().replace(',','').substr($find.html().indexOf("</font>")+7, 4);
	        // 買權
			var $find2 = $(htmlObj).find("div.dr_content > table:eq(2) > tbody > tr > td:eq(0) > table > tbody > tr");
			var arrInx = 0;
			$find2.each(function( index ) {
				if(index >= 5){
					var targetAmt = $(this).find("td:eq(6)").text().trim().replace(',','');
					var callAmt = $(this).find("td:eq(2)").text().trim().replace(',','');
					if(targetAmt!=''){
						targetAmtArray[arrInx] = targetAmt;
						callAmtArray[arrInx] = callAmt;
						arrInx++;
					}
				}
			});
			// 賣權
			arrInx = 0;
			var $find3 = $(htmlObj).find("div.dr_content > table:eq(2) > tbody > tr > td:eq(1) > table > tbody > tr");
			$find3.each(function( index ) {
				if(index >= 5){
					var putAmt = $(this).find("td:eq(3)").text().trim().replace(',','');
					if(putAmt!=''){
						putAmtArray[arrInx] = putAmt;
						arrInx++;
					}
				}
			});
	    },
	    error: function (xhr, ajaxOptions, thrownError){
	    }
	});
}

function renewDataByOpenWindow(){
	var yearMonth = getNewestOptionYearMonth();
	//console.log('yearMonth=' + yearMonth);
	//var url = 'http://tw.screener.finance.yahoo.net/future/aa03?opmr=optionfull&opcm=WTXO&opym=' + yearMonth;
	var url = 'WTXO.html';
	var popup = window.open(url, '_blank', 'width=500,height=500');
	popup.onload = function() {
		//alert('popup.onload');
		//alert(popup.document.documentElement.outerHTML);
        setTimeout(function(){
        	//console.log(popup.document.documentElement.outerHTML);
        	htmlObj = popup.document.documentElement.outerHTML;
        	initOptionDataArray(htmlObj);
        }, 2000);
    };
}

function renewDataByIFrame(){
	var iframe=document.createElement('IFRAME');

	iframe.src='http://tw.screener.finance.yahoo.net/future/aa03?opmr=optionfull&opcm=WTXO&opym=201307';
	iframe.setAttribute('id','testframe');
	iframe.setAttribute('name','testframe');

	if (iframe.attachEvent){
	    iframe.attachEvent("onload", function(){
	        //alert("Local iframe is now loaded.");
	        //alert($(iframe).contents());
	    	iframeOnLoadedCallback();
	    });
	} else {
	    iframe.onload = function(){
	        //alert("Local iframe is now loaded.");
	        //alert($(iframe).contents());
	    	iframeOnLoadedCallback();
	    };
	}
	
	document.body.appendChild(iframe);
}

function iframeOnLoadedCallback(){
	htmlObj = $(iframe).contents();
}

function getOptionTxAmount(target, callOrPut){
	var index = indexOf(targetAmtArray, target);
	if(callOrPut=='CALL'){
		return callAmtArray[index];
	}else{
		return putAmtArray[index];
	}
}

function indexOf(array, value){
	if(array){
		for(var i=0; i<array.length; i++){
			if(array[i]==value){
				return i;
			}
		}
	}
	return -1;
}

function appendBuySaleSelect(parentId){
	var newSelectId = parentId + '_select';
	$('#' + parentId).append('<select id=\'' + newSelectId + '\'></select>');
	$('#' + newSelectId).append('<option value=\'\'></option>');
	$('#' + newSelectId).append('<option value=\'BUY\'>BUY</option>');
	$('#' + newSelectId).append('<option value=\'SELL\'>SELL</option>');
}

function appendCallPutSelect(parentId){
	var newSelectId = parentId + '_select';
	var index = parentId.substr(parentId.length-1, 1);
	var onChangeString = ' onChange=\'renewPremium(' + index + ')\' ';
	var onClickString = ' onClick=\'setAutoReCal()\' ';
	$('#' + parentId).append('<select id=\'' + newSelectId + '\' ' + onChangeString + onClickString + ' ></select>');
	$('#' + newSelectId).append('<option value=\'\'></option>');
	$('#' + newSelectId).append('<option value=\'CALL\'>CALL</option>');
	$('#' + newSelectId).append('<option value=\'PUT\'>PUT</option>');
}

function appendExcercisePriceSelect(parentId){
	var newSelectId = parentId + '_select';
	var index = parentId.substr(parentId.length-1, 1);
	var onChangeString = ' onChange=\'renewPremium(' + index + ')\' ';
	var onClickString = ' onClick=\'setAutoReCal()\' ';
	$('#' + parentId).append('<select id=\'' + newSelectId + '\' ' + onChangeString + onClickString + ' ></select>');
	$('#' + newSelectId).append('<option value=\'\'></option>');
	for(var i=0; i<targetAmtArray.length; i++){
		$('#' + newSelectId).append('<option value=\'' + targetAmtArray[i] + '\'>' + targetAmtArray[i] + '</option>');
	}
}

function renewPremium(index){
	var callPutValue = $('#call_put_' + index + '_select').val();
	var excercisePriceValue = $('#excercise_price_' + index + '_select').val();
	if(callPutValue!='' && excercisePriceValue!=''){
		$('#premium_' + index).text(getOptionTxAmount(excercisePriceValue, callPutValue));
	}else{
		$('#premium_' + index).text('');
	}
	// 即時試算
	if(autoReCal==1){
		//alert('即時試算');
		renewReportTable();
	}
}

function setAutoReCal(){
	autoReCal=1;
}

function initReportTable(){
	var nearExcercisePrice = Math.round(getTAIEX()/100) * 100;
	var excercisePrice = nearExcercisePrice - 600;
	for(var i=1; i<=13; i++){
		$('#report_' + i + '_0').text(excercisePrice + (i-1)*100);
	}
}

function renewReportTable(){
	//alert('renewReportTable');
	//parseInt(str, 10)
	var taiex = parseInt($('#taiex').text(), 10);
	//console.log('taiex=' + taiex);
	for(var i=1; i<=8; i++){
		var buySaleValue = $('#buy_sell_' + i + '_select').val();
		var callPutValue = $('#call_put_' + i + '_select').val();
		var excercisePriceValue = $('#excercise_price_' + i + '_select').val();
		//var permiumValue = parseInt($('#premium_' + i).text(), 10);
		var permiumValue = $('#premium_' + i).text()=='-'?'':formatFloat($('#premium_' + i).text(), 2);
		//console.log('permiumValue=' + permiumValue);
		if(buySaleValue!='' && callPutValue!='' && excercisePriceValue!=''){
			for(var j=1; j<=13; j++){
				var objId = 'report_' + j + '_' + i;
				var tempExcercisePrice = parseInt($('#report_' + j + '_0').text(), 10);
				//console.log('tempExcercisePrice=' + tempExcercisePrice);
				var tempCalPrice = 0;
				
				if(callPutValue=='CALL'){
					if(buySaleValue=='BUY'){
						if(tempExcercisePrice <= excercisePriceValue){
							tempCalPrice = -1*permiumValue;
						}else{
							tempCalPrice = tempExcercisePrice-excercisePriceValue-permiumValue;
						}
					}else{
						if(tempExcercisePrice <= excercisePriceValue){
							tempCalPrice = permiumValue;
						}else{
							tempCalPrice = (-1)*(tempExcercisePrice-excercisePriceValue-permiumValue);
						}
					}
				}else{
					if(buySaleValue=='BUY'){
						if(tempExcercisePrice >= excercisePriceValue){
							tempCalPrice = -1*permiumValue;
						}else{
							tempCalPrice = excercisePriceValue-tempExcercisePrice-permiumValue;
						}
					}else{
						if(tempExcercisePrice >= excercisePriceValue){
							tempCalPrice = permiumValue;
						}else{
							tempCalPrice = (-1)*(excercisePriceValue-tempExcercisePrice-permiumValue);
						}
					}
				}
				
				$('#' + objId).text(tempCalPrice);
				if(tempCalPrice<0){
					$('#' + objId).css('color', 'red');
				}else{
					$('#' + objId).css('color', 'black');
				}
			}
		}else{
			for(var j=1; j<=13; j++){
				var objId = 'report_' + j + '_' + i;
				$('#' + objId).text('');
				$('#' + objId).css('color', 'black');
			}
		}
	}
	
	// TOTAL
	calculateTotal();
	
	showLineChart();
	
	totalAmt();
}

function calculateTotal(){
	for(var i=1; i<=13; i++){
		var totalId = 'report_' + i + '_9';
		var totalValue = 0;
		
		// summary
		for(var j=1; j<=8; j++){
			var tempId = 'report_' + i + '_' + j;
			var tempValue = $('#' + tempId).text()=='' ? 0 : formatFloat($('#' + tempId).text(), 2);
			
			totalValue += tempValue;
		}
		
		$('#' + totalId).text(formatFloat(totalValue,2));
		if(totalValue<0){
			$('#' + totalId).css('color', 'red');
		}else{
			$('#' + totalId).css('color', 'black');
		}
	}
	
}

function showLineChart(){
	$('#chartdiv').empty();
	var myvalues = [];
	for(var i=1; i<=13; i++){
		myvalues.push([parseInt($('#report_'+i+'_0').text(), 10), formatFloat($('#report_'+i+'_9').text(), 2)]); 
	}
	$.jqplot('chartdiv', [myvalues], {
		//title:'Exponential Line',
		//axes:{yaxis:{min:-10, max:240}},
		axes:{
			xaxis:{
				pad: 1.2
	        },
			yaxis:{
				//renderer: $.jqplot.LogAxisRenderer
				tickOptions:{
		            formatString:'$%.2f'
		        }
			}},
		//series:[{color:'#5FAB78'}]
		highlighter: {
	        show: true,
	        sizeAdjust: 7.5
	    }
	});
}

function totalAmt(){
	if($('#total_amt')){
		var totalAmt = 0.00;
		for(var i=1; i<=8; i++){
			var buySaleValue = $('#buy_sell_' + i + '_select').val();
			if(buySaleValue!=''){
				var id = 'premium_' + i;
				var amt = $('#'+id).text();
				if(amt=='-') amt = '0';
				if(buySaleValue=='BUY'){
					amt = '-' + amt;
				}
				totalAmt += formatFloat(amt, 2);
			}
		}
		if(totalAmt<0){
			$('#total_amt').html("總和：<font color='red'>" + totalAmt + " (NT$ " + Math.round(totalAmt*50,0) + ")</font>" );
		}else{
			$('#total_amt').html("總和：" + totalAmt + " (NT$ " + Math.round(totalAmt*50,0) + ")" );
		}
	}
}