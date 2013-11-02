define(["amplify", "common/modules/logger", "common/modules/ServicesManager"],
	function(amplify, logger, ServicesManager) {

		// ID del modulo
		var MODULE_ID = "game-services";

		var base_path = null;

		// boolean: in-memory, integer: (milliseconds) : in-memory con expiration
		var service_cache = true;

		/**
		 * Inicializador
		 */

		function init(product, locale) {

			logger.info("[INFO] Inicializando Common Services." + " (" + MODULE_ID + ")");
			
			// operacion: game initialize
			ServicesManager.addRequest({
				serviceID: "carmen.initialize",
				description:"",
				cache: service_cache,
				type:"ajax",
				params: {
					url: 'initialize',
					dataType:"json",
					type:"GET"
				},
				mocks:{	}
			});

			// operacion: setPlayer
			ServicesManager.addRequest({
				serviceID: "carmen.setPlayer",
				description:"",
				cache: service_cache,
				type:"ajax",
				params: {
					url: base_path + '',
					dataType:"json",
					type:"POST",
					contentType:'application/json;charset=UTF-8',
					dataMap: function ( data ) {
						return JSON.stringify( data );
					}
				},
				mocks:{	}
			});

		}



		// API Publica
		return {

			init: init

		};

	});