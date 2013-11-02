define([
		"amplify", "underscore",

		"common/modules/logger", "common/modules/localization", "common/modules/extraData",

		"modules/service-definition/common-services",

		"game/modules/ar_map_controller", "common/modules/map", "common/modules/gmaps"

	],

	function(amplify, _, logger, localization, extraData, commonServices, arMapController) {

		var tooltip = null;

		/**
		 * Inicializador
		 * @param {object} options Objeto con todas las opciones necesarias
		 */

		function init(options) {

			logger.info("[INFO] El Carmen Barbieri.");

			// inicializar pagina de Login
			if (!_.isUndefined(options.pageInit)) {
				options.pageInit(localization, extraData);
			}

			localization.init(
				extraData.get('transaction-info').productType,
				extraData.get('transaction-info').locale
			);

			// servicios de autos
			commonServices.init(
				extraData.get('transaction-info').productType,
				extraData.get('transaction-info').locale
			);

			$(".avatar").on("click", function (event) {
				event.preventDefault();
				$(".avatar").removeClass("avatar-selected");
				$(this).addClass("avatar-selected");
			});
			
			var date = new Date();
			$('.actual-date').html(date.toLocaleDateString());
			
//			arMapController.init();
			
			amplify.request({
				resourceId: "carmen.initialize",
				data: {
					//token: extraData.get("transaction-info").token
				},
				success: function(service_data) {

					var context = {};

						context = {

						};

						alert('true');

					// devolver informacion
//					callback(context, is_error);

				},
				error: function(message, level) {
					
					alert('error');

//					callback({}, true);

				}
			});

			_bindEvents();

		}

		function _bindEvents () {
			
		}


		// API Publica
		return {

			init: init

		};

	});