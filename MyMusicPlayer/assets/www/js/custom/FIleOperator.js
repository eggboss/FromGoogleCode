//alert('FileOperator');

var FileOperator = {};

// 取得根目錄物件

FileOperator.readFile = function(path){
	$.get(path, function(data) {
		return data;
	});
};