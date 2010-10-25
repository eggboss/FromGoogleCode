/**
 * 整理Javascript好用的Check Method
 * @author Kirk
 * @since 2008.03.04
 */

// 重新定義String的byteLen這個property
String.prototype.byteLen = byteLen;
function byteLen() { // returns the length of str in byte
	return this.length + escape(this).split("%u").length - 1;
}

function chkMaxLength(){
	var srcObj = event.srcElement;
	if (srcObj.value.byteLen() > srcObj.getAttribute('maxLength')){
		//alert(srcObj.getAttribute('caption') + "欄位超過最大長度，請重新輸入");
		srcObj.focus();
		return false;
	}
	return true;
}