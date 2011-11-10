function numcheck(id){
	var re = /^[0-9]+$/;
	if(re.test(document.getElementById(id).value)){
		return true;
	}else{
		document.getElementById(id).value='';
		return false;
	}
}