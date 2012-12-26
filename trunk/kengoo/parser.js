
function DataParser(config){
	this.config = config; 
	this.stocks = {};
	this.consumedList = [];
	this.updateTimer = 0;
	this.weight = null;
	
	/*
	 * {callback:, interval:, data: };
	 */
	this.begin = function() {
		
		if (this.config.interval > 0) {
			var This = this;
			this.timer = setInterval(function(){
				This.getWeightedTrend();
				This.updateTimer = This.updateTimer + 1;
				This.consumeList(This.config.callback);								
			}, this.config.interval * 1000);
			return;
		}
		
		this.getWeightedTrend();
		this.consumeList(this.config.callback);
	}
	
	this.consumeList = function(callback) {
		var list = this.config.list;
		var idx = this.consumedList.length;
		if (idx >= list.length) { // all parsed 
			this.consumedList = [];
			if (callback) {
				this.config.list = callback('finished');
			}
			return;
		}
		var id = list[idx];
		console.log("consume " + id);
		this.consumedList.push(id);
		
		// delay the call
		var This = this;
		setTimeout(function(){
			This.parseInfo(id, function(){
				This.consumeList(callback);
				if (callback) {
					callback('one record finished');
				}
			}); // pass function as callback
		}, 100);
	}
	
	this.parseInfo = function(id, callback) {
		var This = this;
		$.get('http://tw.stock.yahoo.com/q/q?s='+id, function(data){
			var ev = $(data);
			var entry = This.parseHTMLData(ev, null);
			This.stocks[id] = entry;
			if (callback) { 
				callback();
			}
		})
		.error(function() {
			if (callback) {
				callback();
			}
			console.log("error parsing");
		});		  
	}
	
	this.getStockName = function(id, callback) {
		var This = this;
		$.get('http://tw.stock.yahoo.com/q/q?s='+id, function(data){
			var ev = $(data);
			var entry = This.parseHTMLData(ev, null);
			if (callback) { 
				callback(entry);
			}
		})
		.error(function() {
			if (callback) {
				callback('error');
			}
			console.log("error parsing");
		});	
	}
	
	this.parseHTMLData = function (data, callback) {	
		var stkname = data.find("input[name$='stkname']").val();
	    if (!stkname) {
	        if (callback) callback("error");
	        return;
	    }
	    
	    var stock = {};
	    var stkid = data.find("input[name$='stkid']").val();
	    var quotes = [];
	    var quotePanel = data.find('table[border=2]');
	    var quoteCells = quotePanel.find('td[width=105]').siblings();
	    quoteCells.each(function(i, elm) {        
	    	quotes[i] = jQuery.trim($(elm).text());          
	    });  
	    quotes.push(stkid);
	    quotes.push(stkname);
	    stock.id = stkid;
	    stock.name = stkname;
	    stock.quotes = quotes;
	    
	    var bd = data.find("div[class=bd]");
	    var lis = bd.find("li").siblings();
	    var news = [];
	    lis.each(function(i, elm){
	    	//console.log(elm);
	    	var a = $(elm).find("a");
	    	var meta = $(elm).find("span");
	    	news_item = {
	    		'href': $(a).attr('href'),
	    		'text': $(a).text(),
	    		'source': $(meta).text()
	    	};
	    	news.push(news_item);
	    });
	    stock.news = news;
	       
	    return stock;
	}
	
	this.parseWeightedValue = function(data) {
		var currentWeighted = $(data.find("div[class$=tbd0]").find("table[class$=o]")[0]).find("td[class$=dx]").text();
		var currentTrend = $(data.find("div[class$=tbd0]").find("table[class$=o]")[0]).find("td[class$=im]").find("i").text();
		return {
			weighted: currentWeighted,
			trend: currentTrend
		};
	};
	
	this.getWeightedTrend = function(callback){
		var This = this;
		$.get('http://tw.stock.yahoo.com', function(data){
			var ev = $(data);
			This.weight = This.parseWeightedValue(ev);			
			if (callback) { 
				callback(This.weight);
			}
		})
		.error(function() {
			if (callback) {
				callback();
			}			
		});
	}
}