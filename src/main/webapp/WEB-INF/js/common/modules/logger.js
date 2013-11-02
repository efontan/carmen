/**
 * Wrapper de console.log
 */

define([], function () {

	/**
	* Warnings
	* @param {*} data Información a mostrar en la consola
	*/
	function warn(data) {
		_register("warn", data);
	}

	/**
	* Errors
	* @param {*} data Información a mostrar en la consola
	*/
	function error(data) {
		_register("error", data);
	}

	/**
	* Log
	* @param {*} data Información a mostrar en la consola
	*/
	function log(data) {
		_register("log", data);
	}

	/**
	* Info
	* @param {*} data Información a mostrar en la consola
	*/
	function info(data) {
		_register("info", data);
	}

	/**
	* Dir
	* @param {*} data Información a mostrar en la consola
	*/
	function dir(data) {
		_register("dir", data);
	}

	/**
	* Función que dependiendo del tipo de registro que se quiere, 
	* llama al método correspondiente
	* @param {string} type Tipo de registro a usar
	* @param {*} data Información a mostrar en la consola
	*/
	function _register(type, data) {

		if (window.console) {

			if (console[type]) {

				console[type](data);

			} else {

				console.log(data);
			
			}

		}

	}

	// API Publica
	return {
		log		: log,
		error	: error,
		warn	: warn,
		info	: info,
		dir		: dir
	};

});