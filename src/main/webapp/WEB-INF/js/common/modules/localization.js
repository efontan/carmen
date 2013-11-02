/**
 * Modulo de localizacion de mensajes
 */
define(["jquery", "underscore", "require",

		"common/modules/extraData", "common/modules/logger"], 

		function($, _, require, extraData, logger) {

	// mensajes localizados
	var _messages = {};
	var base_path = null;
	var namespace = null;
	var lang = null;
	var country = null;
	var fileStack = [];
	var jsPath = require.toUrl('');

	//@regla matcheo product con java
	var productRule = {
		"CAR": "cars",
		"HOTEL": "hotel",
		"FLIGHT": "flight",
		"COMMON" : "common"
	};

	var exclusionMap = {
		/*'ES' : [
			'suggestion.cars.json'
		]*/
	};

	var i18n = {
		'invoice': ['ar', 'br', 'us', 'bo', 'cl', 'co', 'cr', 'do', 'ec', 'gt', 'hn', 'mx', 'ni', 'pa', 'pe', 'pr', 'py', 'rd', 'sv', 'uy', 've']
	};

	function init(product, locale) {

		lang = extraData.get('global-info').language.toUpperCase();
		country = extraData.get('global-info').country.toLowerCase();
		namespace = productRule[product];
		//base_path = "/" + locale + "/resources/js/localization/" + lang;
		base_path = jsPath + "localization/" + lang;
	}

	function fileDegrade(actualFile, module_id) {

		//si el archivo actual posee el namespace lo degrada al nombre del módulo. ej:
		//suggestion.cars.json -> degrada a -> suggestion.json (donde 'cars' es el namespace)

		//alert('degradando...');
		//alert(actualFile);
		//alert("el archivo tiene country: "+_.contains(actualFile, '.'+country+'.'));
		//alert("el archivo tiene namespace: "+_.contains(actualFile, '.'+namespace+'.'));
		//alert("el archivo no esta excluido: "+!_.contains(exclusionMap[lang], actualFile));
		fileStack.push(actualFile);

		var degradedFile = base_path + '/common/common.json';

		if (_.contains(actualFile, '.' + country + '.') && !_.contains(exclusionMap[lang], actualFile)) {
			//alert(base_path+'/'+module_id+'/'+module_id+'.'+namespace+'.json');
			degradedFile = base_path + '/' + module_id + '/' + module_id + '.json';
		} else if (_.contains(actualFile, '.' + namespace + '.') && !_.contains(exclusionMap[lang], actualFile)) {
			//alert(base_path+'/'+module_id+'/'+module_id+'.json');
			degradedFile = base_path + '/' + module_id + '/' + module_id + '.json';
		}

		return degradedFile;

	}

	function jsonPopulate(localizationFile, callback) {
		$.ajax({
			url: localizationFile,
			type: 'GET',
			async: false,
			success: function(data) {
				callback(data);
			},
			error: function(data) {
				logger.info("[ERROR - LOCALIZATION] no se encontró el archivo: '" + (localizationFile.substring(localizationFile.lastIndexOf("/") + 1)) + "'.");
				data = {};
				callback(data);
			},
			dataType: 'json'
		});

	}

	function recursiveDegrade(module_id, key) {


		while (!_.has(_messages[module_id].messages, key) && !_.contains(_messages[module_id].file, 'common.json')) {


			localizationFile = fileDegrade(_messages[module_id].file, module_id);

			jsonPopulate(localizationFile, function(data){

				//alert('entre a popular');

				var stack = {
					'file': localizationFile.substring(localizationFile.lastIndexOf("/") + 1),
					'messages': _.assign(data, _messages[module_id].messages)
				};

				setMessage(module_id, stack);

				//alert(_messages[module_id].file);
				//alert('tengo la clave: '+_.has(_messages[module_id].messages, key));
				//alert('el mensaje es: '+_.pick(_messages[module_id].messages, key)[key]);

				if (_.has(_messages[module_id].messages, key)) {
					//alert('toma el mensaje gil!');
					return true;
				}

			});

		}

		//retorna y muestra error
		return;

	}

	function jsonGet(module_id, key) {

		var localizationFile = '';

		//si no existe el módulo localizado, lo trae y lo popula
		if (_.isEmpty(_messages[module_id])) {
			//alert('vacio');
			var file = module_id + '.' + namespace + '.json';

			if (_.indexOf(i18n[module_id], country) >= 0) {
				file = module_id + '.' + country + '.json';
			}

			if (_.isEqual(module_id, "common") || _.isUndefined(namespace)) {
				file = module_id + '.json';
			}

			localizationFile = base_path + '/' + module_id + '/' + file;

			if (_.contains(exclusionMap[lang], file)) {
				localizationFile = fileDegrade(file, module_id);
			}

			jsonPopulate(localizationFile, function(data) {

				var stack = {
					'file': localizationFile.substring(localizationFile.lastIndexOf("/") + 1),
					'messages': data
				};

				setMessage(module_id, stack);
			});

			if (_.has(_messages[module_id].messages, key)) {
				//alert('lo encontre de una');
				return _.pick(_messages[module_id].messages, key)[key];
			} else {
				//alert('lo llevo a degradar');
				recursiveDegrade(module_id, key);
			}

			//si el módulo existe, pero no encuentra la clave, lo degrada y popula
		} else {
			//alert(_messages[module_id].file);
			//alert('clave: '+key);
			//alert('tengo la clave: '+_.has(_messages[module_id].messages, key));
			//alert('el mensaje es: '+_.pick(_messages[module_id].messages, key)[key]);
			if (_.has(_messages[module_id].messages, key)) {
				return _.pick(_messages[module_id].messages, key)[key];
			} else {
				//alert('else');
				recursiveDegrade(module_id, key);
			}
		}

		if (_.has(_messages[module_id].messages, key)) {
			return _.pick(_messages[module_id].messages, key)[key];
		} else {
			var listFiles = '';
			_.forEach(fileStack, function(file) {
				listFiles += "'" + file + "', ";
			});
			logger.info("[ERROR - LOCALIZATION] no se encontró la clave: '" + key + "' en: " + listFiles + "'common.json'.");
			return '?? ' + key + ' ??';
		}
	}

	/**
	 * Devuelve un mensaje localizado
	 * @param module_id : id del modulo
	 * @param key : llave del mensaje
	 * @return : localizedMessage (string) con el texto localizado
	 */

	function getMessage(module_id, key) {

		var localizedMessage = "";

		if (_messages[module_id] && _messages[module_id][key]) {
			localizedMessage = _messages[module_id][key];
		}

		return localizedMessage;
	}

	function getAllMessage() {


		return _messages;
	}

	function setMessage(name, value) {

		_messages[name] = value;

	}

	function getMessageParams(module_id, key, params) {

		var localizedMessage = getMessage(module_id, key);

		// reemplazar cada parametro en el texto
		for (var i = 0; i < params.length; i++) {
			var replace = "{" + i.toString() + "}";
			localizedMessage = localizedMessage.replace(replace, params[i]);
		}
		return localizedMessage;
	}


	// API Publica
	return {
		init: init,
		get: getMessage,
		getAll: getAllMessage,
		getMsgParams: getMessageParams,
		set: setMessage,
		jsonGet: jsonGet

	};

});