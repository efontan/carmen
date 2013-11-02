if (!require.defined('handlebars')) {
	
	if (typeof(Handlebars) === "undefined") {

		define("handlebars", ["libs.handlebars"], function () {
			return Handlebars;
		});

	} else {

		define("handlebars", [], function () {
			return Handlebars;
		});

	}
	
}
