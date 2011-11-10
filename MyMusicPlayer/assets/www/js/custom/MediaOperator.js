//alert('MediaOperator');

var MediaOperator = {};

MediaOperator.media = null;
MediaOperator.playSuccessCallback = null;
MediaOperator.playErrorCallback = null;

MediaOperator.play = function(args){
	if(args){
		MediaOperator.media = new Media(args,
	        // success callback
	        function() {
				JsBridge.log("playAudio():Audio Success");
				if(MediaOperator.playSuccessCallback){
					MediaOperator.playSuccessCallback();
				}
	        },
	        // error callback
	        function(err) {
	            JsBridge.log("playAudio():Audio Error: "+err);
	            if(MediaOperator.playErrorCallback){
	            	MediaOperator.playErrorCallback();
	            }
	    });
		
		MediaOperator.media.play();
	}else{
		if(MediaOperator.media){
			MediaOperator.media.play();
		}
	}
	
	$.mobile.changePage("#main", { transition: "slideup"} );
};

MediaOperator.setPlaySuccessCallBack = function(callback){
	MediaOperator.playSuccessCallback = callback;
};

MediaOperator.setPlayErrorCallback = function(callback){
	MediaOperator.playErrorCallback = callback;
};

MediaOperator.stop = function(){
	if(MediaOperator.media){
		MediaOperator.media.stop();
	}
};

MediaOperator.pause = function(){
	MediaOperator.media.pause();
};