/**
 *  
 * @return Object literal singleton instance of DirectoryListing
 */
var FileSelector = function() {
};

/**
  * @param directory The directory for which we want the listing
  * @param successCallback The callback which will be called when directory listing is successful
  * @param failureCallback The callback which will be called when directory listing encouters an error
  */
FileSelector.prototype.list = function(action, data, successCallback, failureCallback) {
	return PhoneGap.exec(  
	successCallback,        //Success callback from the plugin
	failureCallback,        //Error callback from the plugin
	'FileSelectorPlugin',   //Tell PhoneGap to run "DirectoryListingPlugin" Plugin
	action,                 //Tell plugin, which action we want to perform
	[data]);           //Passing list of args to the plugin
};
 
PhoneGap.addConstructor(function() {
	PhoneGap.addPlugin("FileSelector", new FileSelector());
});