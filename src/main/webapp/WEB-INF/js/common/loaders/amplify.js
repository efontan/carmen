if (!require.defined('amplify')) {
	if (typeof(amplify) === "undefined") {
	
		define("amplify", ["libs.amplify"], function () {
			return amplify;
		});
	
	} else {
	
		define("amplify", [], function () {
			return amplify;
		});
	
	}

}