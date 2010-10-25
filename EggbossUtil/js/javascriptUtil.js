/**
 * 整理Javascript好用的Method
 * @author Kirk
 * @since 2008.03.04
 */


/**
* 用ID取得物件集合
* @param {Object} id 指定的ID
* @param {Object} formName FORM的NAME
* @return Array 物件集合
*/
function getElementsById(id, formName){
	var field_list=new Array();
	try{
	  	var col=document.all.item(formName);
		for(var i=0;i<col.length;i++){
			field = col[i];
			if(field.id==id){
				field_list.push(col[i]);
			}
		}
	}catch(err){
		alert(err);
	}
	return field_list;
}