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
					url: 'player/new',
					dataType:"json",
					type:"POST",
					contentType:'application/json;charset=UTF-8',
					dataMap: function ( data ) {
						return JSON.stringify( data );
					}
				},
				mocks:{	}
			});
			
			// operacion: game status
			ServicesManager.addRequest({
				serviceID: "carmen.getStatus",
				description:"",
				cache: service_cache,
				type:"ajax",
				params: {
					url: 'status/{token}/',
					dataType:"json",
					type:"GET"
				},
				mocks:{	}
			});
			
			// operacion: getClue
			ServicesManager.addRequest({
				serviceID: "carmen.getClue",
				description:"",
				cache: service_cache,
				type:"ajax",
				params: {
					url: 'clue/{token}/{cityOid}/{hotelId}',
					dataType:"json",
					type:"GET"
				},
				mocks:{	}
			});
			
			// operacion: nextdestinations
			ServicesManager.addRequest({
				serviceID: "carmen.nextDestinations",
				description:"",
				cache: service_cache,
				type:"ajax",
				params: {
					url: 'nextdestinations/{token}/{cityCode}',
					dataType:"json",
					type:"GET"
				},
				mocks:{	}
			});
			
			// operacion: flights
			ServicesManager.addRequest({
				serviceID: "carmen.getFlights",
				description:"",
				cache: service_cache,
				type:"ajax",
				params: {
					url: 'flights/{from}/{to}',
					dataType:"json",
					type:"GET"
				},
				mocks:{	}
			});
			
			// operacion: setTravel
			ServicesManager.addRequest({
				serviceID: "carmen.setTravel",
				description:"",
				cache: service_cache,
				type:"ajax",
				params: {
					url: 'travel/{token}/{cityCode}/{price}/{hours}',
					dataType:"json",
					type:"GET"
				},
				mocks:{	}
			});

			
		}



		// API Publica
		return {

			init: init

		};

	});