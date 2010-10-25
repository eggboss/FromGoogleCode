/**
 * 整理INPUT物件好用的METHOD
 * @author kirk
 */

/**
 * 動態的新增一個Hidden到Form裡
 * @param {Object} formName Form的name 
 * @param {Object} name Hidden的name
 * @param {Object} value Hidden的值
 */
function addHiddenToForm(formName,hiddenName, hiddenValue){
	formObj = document.getElementsByName(formName)[0];
	oIpt = document.createElement("INPUT");
	oIpt.type = "hidden";
	oIpt.name = hiddenName;
	oIpt.value = hiddenValue;
	formObj.appendChild(oIpt);
}