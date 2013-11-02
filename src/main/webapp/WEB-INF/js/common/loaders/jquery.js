if (typeof(jQuery) === "undefined") {

	define("jquery", ["libs.jquery"], function () {
		return jQuery;
	});

} else {

	define("jquery", [], function () {
		return jQuery;
	});

}