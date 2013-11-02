/**
 * Modulo para cargar datos extra de la aplicacion
 */

define([], function () {

	var _extraData = {};


	function setExtraData(name, value) {
		
		_extraData[name] = value;
		
	}
	
	/**
	 * Obtiene todos los datos extra para un campo dado
	 */
	function getAllExtraData() {
		return _extraData;
	}
	
	/**
	 * Devuelve los datos extra para un campo dado o null si no existe
	 * @param name : string con el nombre del campo
	 */
	function getExtraData(name) {
		
		if (_extraData[name]) {
			
			return _extraData[name];
			
		} else {
			
			return false;
			
		}
		
	}
	
	function setOnce(name, value) {
		if (!getExtraData(name)) {
			setExtraData(name, value);
		}
		
	}
	
	// API Publica
	return {

		get					: getExtraData,
		getAllExtraData		: getAllExtraData,
		set					: setExtraData,
		setOnce				: setOnce
	
	};

});