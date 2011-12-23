var CommonUtil = {};
	
/**
 * 將字串轉成Date物件(僅支援兩種pattern)
 * yyyy-MM-dd
 * yyyy-MM-dd HH:mm:ss
 */
CommonUtil.parseToDate = function(dateString){
	var dd = new Date();
	if(dateString && dateString.length==10){
		var d = dateString.split('-');
		/*
		dd.setFullYear(parseInt(d[0],10));
		dd.setMonth(parseInt(d[1],10)-1);
		dd.setDate(parseInt(d[2],10));
		dd.setHours(0);
		dd.setMinutes(0);
		dd.setSeconds(0);
		return dd;
		*/
		return new Date(parseInt(d[0],10), (parseInt(d[1],10)-1), parseInt(d[2],10), 0, 0, 0, 0);
	}else if(dateString && dateString.length==19){
		var a = dateString.split(' ');
		var d = a[0].split('-');
		var t = a[1].split(':');
		/*
		dd.setFullYear(parseInt(d[0],10));
		dd.setMonth(parseInt(d[1],10)-1);
		dd.setDate(parseInt(d[2],10));
		dd.setHours(parseInt(t[0],10));
		dd.setMinutes(parseInt(t[1],10));
		dd.setSeconds(parseInt(t[2],10));
		return dd;
		*/
		return new Date(parseInt(d[0],10), (parseInt(d[1],10)-1), parseInt(d[2],10), parseInt(t[0],10), parseInt(t[1],10), parseInt(t[2],10), 0);
	}
}

CommonUtil.getToday = function(){
	var today = new Date();
	var todayString = today.getFullYear() + '-' + CommonUtil.addZero((today.getMonth()+1),2) + '-' + CommonUtil.addZero(today.getDate(),2);
	return todayString;
}

CommonUtil.addZero = function(input, long){
	for(var i=0; i<long; i++){
		input = "0" + input;
	}
	return input.substring(input.length-long, input.length);
}


/**
 * 比較兩日期大小，回傳true表示date1大於date2
 */
CommonUtil.compareTwoDate = function(date1, date2){
	if(date1 > date2){
		return true;
	}else{
		return false;
	}
}

CommonUtil.showSubWindows = function(url,callback){
	// window.open(url, 'xx', 'directories=0, height=400, location=no, menubar=no, status=yes, toolbar=no, width=600, resizable=yes');
	var rc = window.showModalDialog(url, self, 'dialogWidth:600px;dialogHeight:400px;help:no');
	if(!rc){
		rc = window.ReturnValue;
	}
	callback(rc);
}

CommonUtil.showSubWindows2 = function(url,callback,width,height){
	// window.open(url, 'xx', 'directories=0, height=400, location=no, menubar=no, status=yes, toolbar=no, width=600, resizable=yes');
	var rc = window.showModalDialog(url, self, 'dialogWidth:'+width+'px;dialogHeight:'+height+'px;help:no');
	if(!rc){
		rc = window.ReturnValue;
	}
	callback(rc);
}
/* ---callbackFunc Sample-------------------------------
function callbackFunc(rc){
	if(rc){
		var s = rc.split(",");

		$('#targetPublichId').val(s[0]);
		$('#productName').html(s[1]);
		if(s[2] != ''){
			var coverUrl = IntraCoverUrl + '/' + s[2];
			$('#productCover').html('<img src="' + coverUrl + '" border="0" width="160"/>');
		}else{
			var coverUrl = IntraCoverUrl + '/' + 'cover_null.jpg';
			$('#productCover').html('<img src="' + coverUrl + '" border="0" width="160"/>');
		}
		$('#productType').html(s[3]);
		$('#productPrice').html(s[4]);
		targetOnlineDate = s[5];
		targetOfflineDate = s[6];
		$('#targetcover').val(s[2]);
		$('#targetType').val(s[7]);
		$('#targetContentId').val(s[8]);
	}	
}
*/

CommonUtil.getCheckBoxValues = function(ckbObjName){
	var cbkObj = document.getElementsByName(ckbObjName);
	if(cbkObj && cbkObj!=null){
		if(cbkObj.length && cbkObj.length>0){
			var ckbValus = "";
			for(var i=0; i<cbkObj.length; i++){
				if(cbkObj[i].checked){
					ckbValus+=cbkObj[i].value;
					ckbValus+=',';
				}
			}
			if(ckbValus!='' && ckbValus.length>1){
				ckbValus = ckbValus.substring(0, ckbValus.length-1);
			}
			return ckbValus;
		}
	}
}

CommonUtil.trim = function(s) {
	return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}

CommonUtil.selectAllCheckBox = function(ckbObjName, trueOrFalse){
	var ckbObj = document.getElementsByName(ckbObjName);
	if(ckbObj && ckbObj.length && ckbObj.length>0){
		for(var i=0; i<ckbObj.length; i++){
			ckbObj[i].checked = trueOrFalse;
		}
	}
}

CommonUtil.isNumber = function(id){
	var oNum = document.getElementById(id).value;
	if(oNum !== ''){
		var strP=/^\d+(\d+)?$/;
		if(!strP.test(oNum)){
			alert("請輸入整數！");
			document.getElementById(id).value="";
			document.getElementById(id).focus();
			return false;
		}
		return true;
	}else{
		return true;
	}
}

CommonUtil.createOption = function(value, text) {
	 var textChange = parseInt(text,10);
	 if(textChange < 10){
		 textChange = "0" + textChange;
	 }
	 var opt = document.createElement('option');
	 opt.value = (parseInt(value,10)<10?('0'+value):value);
	 opt.innerHTML = textChange;
	 return opt;
};

CommonUtil.createNormalOption = function(value, text) {
	 var opt = document.createElement('option');
	 opt.value = value;
	 opt.innerHTML = text;
	 return opt;
};

CommonUtil.checkImgExtName = function(v){ 
	var ar=v.split(".");   
	if(!/gif|GIF|jpg|JPG|bmp|BMP|JPEG|jpeg/.test(ar[ar.length-1])){      
		$('#uploadFile').val('');
		alert('您選擇的文件副檔名為   '+ar[ar.length-1]+'\n\n   不合要求，請重新選擇！');
		return false;
	}else{
		return true;
	}
};

CommonUtil.getChildTextNodeValue = function(id){
	return document.getElementById(id).childNodes.item(0).nodeValue;
};

//計算天數的函數
CommonUtil.dateDiff = function(beginDate, endDate){
	// beginDate和endDate都是2007-8-10格式
	var Date1, Date2, iDays;
	// 轉換為2007-8-10格式
	Date1=  CommonUtil.parseToDate(beginDate);
	Date2 = CommonUtil.parseToDate(endDate);
    // 轉換為天數
	iDays = parseInt(Math.abs(Date1-Date2)/1000/60/60/24, 10);
	return  iDays;
};  


CommonUtil.getDateForAddMonth = function(d, c){
	//var d = new Date();
	var m = d.getMonth();
	m = (m + c)%11;
	d.setMonth(m);
	return d;
};


CommonUtil.GetMonthDayCount = function(date){
	var m=date.getMonth() + 1;
	if(m==12){
		m=1;
	}else{
		m+=1;
	}
	switch(m){
		case   1:case   3:case   5:case   7:case   8:case   10:case   12:
		return   31;
		case   4:case   6:case   9:case   11:
		return   30;
	}
	//feb:
	date=new Date(date);
	var lastd=28;
	date.setDate(29);
	while(date.getMonth()==1){
		lastd++;
		CommonUtil.AddDays(date,1);
	}
	return lastd;
};

CommonUtil.AddDays = function(date,value)
{
	date.setDate(date.getDate()+value);
};

CommonUtil.setKEEPENDDATE = function(str,p){
	var s;
	var days = 0;
	var date = new Date(str.substring(0,4),	str.substring(5,7)-1, str.substring(8,10));
	for(var i=0;i<p;i++){
		days = CommonUtil.GetMonthDayCount(date);
		date.setDate(date.getDate()+days);
	}
	s = date.getYear() + '-';     //取年份
	s += date.getMonth()+1 + '-'; //取月份
	s += date.getDate();          //取日期
	return s;
};


CommonUtil.getDateForAddMonth2 = function(d, c){
	//alert("input:"+d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate());
	var inputMonthValue = d.getMonth();
	var outputMonthValue = (inputMonthValue + c) % 12;
	var addYear = Math.floor((inputMonthValue + c) / 12);
	d.setFullYear(d.getFullYear() + addYear, outputMonthValue, d.getDate());
	//alert("input:"+d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate());
	return d;
};

/*
 * 最後一天會對應到最後一天
 * 1月31日 --> 2月28日
 *  
 * but
 * 1月30日 --> 2月28日
 * 1月29日 --> 2月28日
 * 1月28日 --> 2月28日
 * 1月27日 --> 2月27日
 * 
 * 2月28日 --> 3月31日
 * 2月27日 --> 3月27日
 * 
 * not support chrome!
 */
CommonUtil.getDateForAddMonth3 = function(d, c){
	//alert("input:"+d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate());
	var inputMonthValue = d.getMonth();
	var outputMonthValue = (inputMonthValue + c) % 12;
	var addYear = Math.floor((inputMonthValue + c) / 12);
	var outputYear = d.getFullYear() + addYear;
	
	var inputDate = d.getDate(); // 原輸入的日期
//	var inputEndDateOfMonth = CommonUtil.tdayend(d.getFullYear(), inputMonthValue+1); // 原月份的最後一天
//	var outputEndDateOfMonth = CommonUtil.tdayend(d.getFullYear(), (outputMonthValue+1)); // 指定月份的最後一天
	var inputEndDateOfMonth = CommonUtil.getLastDateOfMonth(d.getFullYear(), inputMonthValue+1); // 原月份的最後一天
	alert('inputEndDateOfMonth=' + inputEndDateOfMonth);
	var outputEndDateOfMonth = CommonUtil.getLastDateOfMonth(d.getFullYear(), (outputMonthValue+1)); // 指定月份的最後一天
	alert('outputEndDateOfMonth=' + outputEndDateOfMonth);
	var outputDate = inputDate;
	
	if(inputDate == inputEndDateOfMonth){ // 是月底
		outputDate = outputEndDateOfMonth;
	}else{
		if(inputEndDateOfMonth > outputEndDateOfMonth){
			if(inputDate >= outputEndDateOfMonth){
				outputDate = outputEndDateOfMonth;
			}
		}
	}
	
	return new Date(outputYear, outputMonthValue, outputDate, 0, 0, 0, 0);
};

/*
 * 那年那月的最後一日
 * 參數month:1~12
 */
CommonUtil.tdayend = function(year,month){
	month = parseInt(month,10)+1; 
	var temp = new Date(year+"/"+month+"/0"); 
	return temp.getDate();
};

CommonUtil.getLastDateOfMonth = function(year, month) {
	return new Date(new Date(year, month, 1).getTime() - 1000 * 60 * 60 * 24).getDate();
};





