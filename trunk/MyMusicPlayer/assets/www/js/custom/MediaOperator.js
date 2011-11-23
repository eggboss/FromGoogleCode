//alert('MediaOperator');

var MediaOperator = {};

MediaOperator.media = null;
MediaOperator.mediaTimer = null;
MediaOperator.playSuccessCallback = null;
MediaOperator.playErrorCallback = null;

MediaOperator.showLyric = false;

MediaOperator.play = function(args){
	if(args){
		MediaOperator.media = new Media(args,
	        // success callback
	        function() {
				// 播完時會進入
				JsBridge.log("playAudio():Audio Success");
				if(MediaOperator.playSuccessCallback){
					MediaOperator.playSuccessCallback();
				}
				
				if(MediaOperator.mediaTimer != null ){
					clearInterval(MediaOperator.mediaTimer);
				}
	        },
	        // error callback
	        function(err) {
	            JsBridge.log("playAudio():Audio Error: "+err);
	            if(MediaOperator.playErrorCallback){
	            	MediaOperator.playErrorCallback();
	            }
	            
	            if(MediaOperator.mediaTimer != null ){
					clearInterval(MediaOperator.mediaTimer);
				}
	    });
		
		MediaOperator.media.play();
	}else{
		if(MediaOperator.media){
			MediaOperator.media.play();
		}
	}
	
	var lyricList = null;
	if(MediaOperator.showLyric) lyricList = MediaOperator.parseLyric("lyrics");    
	
	if (MediaOperator.mediaTimer == null) {
		MediaOperator.mediaTimer = setInterval(function() {
            // get my_media position
        	MediaOperator.media.getCurrentPosition(
                // success callback
                function(position) {
                    if (position > -1) {
                    	MediaOperator.setAudioPosition((position) + " sec");
                    	
                    	// 動態歌詞
                    	if(MediaOperator.showLyric){
                    		MediaOperator.showLyric(lyricList, (position * 1000));
                    	}
                    }
                },
                // error callback
                function(e) {
                    alert("Error getting pos=" + e);
                    MediaOperator.setAudioPosition("Error: " + e);
                }
            );
        }, 1000);
    }else{
    	clearInterval(MediaOperator.mediaTimer);
    }
	
	$.mobile.changePage("#main", { transition: "slideup"} );
};

MediaOperator.setAudioPosition = function(position) {
	$('#audio_position').html(position);
    //document.getElementById('audio_position').innerHTML = position;
}

MediaOperator.setPlaySuccessCallBack = function(callback){
	MediaOperator.playSuccessCallback = callback;
};

MediaOperator.setPlayErrorCallback = function(callback){
	MediaOperator.playErrorCallback = callback;
};

MediaOperator.stop = function(){
	if(MediaOperator.media){
		MediaOperator.media.stop();
		MediaOperator.media.release();
		if(MediaOperator.mediaTimer != null ){
			clearInterval(MediaOperator.mediaTimer);
		}
	}
};

MediaOperator.pause = function(){
	MediaOperator.media.pause();
};








//--Lyric-------------------------------------------------------------------------------------
MediaOperator.currentLyricItem = null;
MediaOperator.maxwidth = 300;

// 將歌詞轉成array
MediaOperator.parseLyric = function(lyricsId){
	//var lyric = $("#"+lyricsId).val();
	var lyric = FileOperator.readFile("/mnt/sdcard/MyMediaPlayer/02. 聲聲慢.lrc");
	JsBridge.log(lyric);
	var items = lyric.match(/\[[0-9]{2}:[[0-9]{2}\.[[0-9]{2}\].*/gi);
	var item = [] ;
	$.each(items,function(ind, line){
	    var items = line.split(/[\[\]]/);
	    item.push({time: MediaOperator.parseTime(items[1]), txt:items[2] });
	});
	return item;
}


/**
*  test case alert(parseTime("03:09.30")); //189300
*/
MediaOperator.parseTime = function(time){
	var tokens = time.split(/[:\.]/);
	return parseInt( tokens[0], 10) *60000 +
	      parseInt( tokens[1], 10) *1000 +
	      parseInt( tokens[2], 10) * 10 ;
}

/**
* Assume that lyricList is sorted list , here we use binary search.
*/
MediaOperator.findPreviousItem = function(lyricList, currentMsIndex){
	var len = lyricList.length ;
	
	if(len == 0 ) return null;
	if(currentMsIndex < lyricList[0].time) return lyricList[0];
	var high = len - 1;
	var low = 0;
	
	while (low <= high) {
	    mid = parseInt((low + high) / 2)
	    if (lyricList[mid ].time > currentMsIndex) {
	        high = mid - 1;
	    } else if (currentMsIndex > lyricList[mid ].time) {
	        low = mid + 1;
	    } else {
	        return lyricList[mid];
	    }
	}
	
	return lyricList[low -1 ];
}

MediaOperator.showLyric = function(lyricList, currentMsIndex){
	MediaOperator.currentLyricItem = MediaOperator.findPreviousItem(lyricList, currentMsIndex);
	if(MediaOperator.currentLyricItem){
		JsBridge.log('Time='+MediaOperator.currentLyricItem.time+',txt='+MediaOperator.currentLyricItem.txt);
	}
	$('#lyricArea').html(MediaOperator.currentLyricItem.txt);
}
