/**
 * 相關Cross Site Scripting的Method
 
 * @Auther Kirk Hsu
 * @Since 2008/10/06
 */
//alert('import success');

/*
 * 移除可能組成HTML或JAVASCRIPT的字元：  < > " ' % ; ) ( & + -
 * From MicroSoft http://support.microsoft.com/kb/252985
 */
function xssFilter(strTemp) { 
    //alert(strTemp);
    strTemp = strTemp.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g,""); 
    return strTemp;
}

function xssFilter1(strTemp){
    //alert(strTemp);
    strTemp = strTemp.replace(/((\%3C)|<)((\%2F)|\/)*[a-z0-9\%]+((\%3E)|>)/i,''); 
    return strTemp;
}
/*
 * Regex for simple CSS attack
 * From http://www.securityfocus.com/infocus/1768
 */
function xssDetect1(strTemp){
    return strTemp.match(/((\%3C)|<)((\%2F)|\/)*[a-z0-9\%]+((\%3E)|>)/i); 
}

/*
 * Regex for "<img src" CSS attack
 * From http://www.securityfocus.com/infocus/1768
 */
function xssDetect2(strTemp){
    return strTemp.match(/((\%3C)|<)((\%69)|i|(\%49))((\%6D)|m|(\%4D))((\%67)|g|(\%47))[^\n]+((\%3E)|>)/i); 
}

/*
 * Paranoid regex for CSS attacks
 * From http://www.securityfocus.com/infocus/1768
 */
function xssDetect3(strTemp){
    return strTemp.match(/((\%3C)|<)[^\n]+((\%3E)|>)/i); 
}

function removeBadTag(strTemp){
    //strTemp = strTemp .replace(/</g, "&lt;").replace(/>/g, "&gt;");
    strTemp.replace(/[\"\'][\s]*javascript:(.*)[\"\']/g, "\"\"");
    strTemp = strTemp.replace(/<script(.*)/g, "");    
    strTemp = strTemp.replace(/eval\((.*)\)/g, "");
    return strTemp;
}

function convertBadTag(strTemp){
    strTemp = strTemp.replace(/</g, "&lt;").replace(/>/g, "&gt;"); // 過濾角括號
    strTemp = strTemp.replace(/\"/g, "&quot;").replace(/\'/g, "&#039;"); // 過濾單雙引號
	return strTemp;
}