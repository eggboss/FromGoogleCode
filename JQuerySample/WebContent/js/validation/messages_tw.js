jQuery.extend(jQuery.validator.messages, {
	required: "必選字段",
	remote: "請修正該字段",
	email: "請輸入正確格式的電子郵件",
	url: "請輸入合法的網址",
	date: "請輸入合法的日期",
	dateISO: "請輸入合法的日期 (ISO).",
	number: "請輸入合法的數字",
	digits: "只能輸入整數",
	creditcard: "請輸入合法的信用卡號",
	equalTo: "請再次輸入相同的值",
	accept: "請輸入擁有合法後綴名的字符串",
	maxlength: jQuery.validator.format("請輸入一個長度最多是 {0} 的字符串"),
	minlength: jQuery.validator.format("請輸入一個長度最少是 {0} 的字符串"),
	rangelength: jQuery.validator.format("請輸入一個長度介於 {0} 和 {1} 之間的字符串"),
	range: jQuery.validator.format("請輸入一個介於 {0} 和 {1} 之間的值"),
	max: jQuery.validator.format("請輸入一個最大為 {0} 的值"),
	min: jQuery.validator.format("請輸入一個最小為 {0} 的值"),
	endDate: "結束日期必需大於開始日期",
	//greaterThan: jQuery.validator.format("必需大於 {0}")
	//greaterThan: "必需大於 " + $('#'+jQuery.validator.format("{0}")).val()
	greaterThan: "必需大於 "
});