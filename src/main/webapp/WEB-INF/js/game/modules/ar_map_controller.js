define([
		"underscore", "common/modules/overlay",

		"common/modules/extraData", "common/modules/logger", 

		"game/modules/ar_map", "common/modules/map",
		
		"templates/hotel-map", "templates/op-loading"

	],

	function(_, overlay, extraData, logger) {

		var tooltip = null;
		var module_id = "ar_map";

		/**
		 * Inicializador
		 * @param {object} options Objeto con todas las opciones necesarias
		 */

		function init(options) {

			logger.info("[INFO] Inicializando m&oacute;dulo "+module_id);

			var map_options = {
				map: 'ar_mill_en',
				backgroundColor: 'transparent',
	            regionStyle: {
	              initial: {
	                fill: '#8d8d8d'
	              }
	            },
	            onRegionClick: function (event, code) {

	            	tooltip = new overlay();

	            	tooltip.openDynamicDialog({
						trigger: $('#main-panel'),
						onClose: function() {},
						id: 'hotelMap',
						//title: localization.jsonGet('specialRequest', 'title'),
						effect: "fade",
						remove: true,
						position: 'top-left',
						type: 'popover',
						arrow: false,
						background: false,
						callback: function() {
							_getFormData(function(data, isError) {
								// si no es error mostrar el formulario
								if (!isError) {
									_renderSpecialRequest(data, code, tooltip);
									
								} else {
									// sino mostrar mensaje de error
									_renderSpecialRequest(data, code, tooltip);
									//tooltip.renderPopupMessage("error", "specialRequest");
								}

							});
						}
					});
	            }
			};

			_bindMap($('#map'), map_options);

		}

		function _bindMap(obj, options) {
			// setear autocomplete
			if (!_.isUndefined(window.$(obj).vectorMap)) {
				window.$(obj).vectorMap(options);
			} else {
				$(obj).vectorMap(options);
			}
		}
		
		function _getFormData(callback) {

			amplify.request({
				resourceId: "carmen.setPlayer",
				data: {
					token: extraData.get("transaction-info").token||"0000000000"
				},
				success: function(service_data) {

					var context = {};

					// chequear que no sea error
					var is_error = service_data.responseStatus != "SUCCESS";

					// chequear errores y procesar informacion
					if (!is_error) {

						
					}


					// devolver informacion
					callback(context, is_error);

				},
				error: function(message, level) {

					var context = {};

					context = {
						body: 'lerolero'
					};

					callback(context, true);

				}
			});

		}


		function _renderSpecialRequest(data, code, tooltip) {

			var template = Handlebars.templates['hotel-map.hbs'];
			
			var context = {
				"body": code,
			};

			var popupContent = template(context);

			tooltip.updateContent(popupContent);
		

			var map = new GMaps({
			    el: '#gmap',
			    lat: 51.5073346,
			    lng: -0.1276831,
			    zoom: 13,
			    zoomControl : true,
			    zoomControlOpt: {
			        style : 'SMALL',
			        position: 'TOP_LEFT'
			    },
			    panControl : false,
			  });

				map.addMarker({
			      lat: 51.503324,
			      lng: -0.119543,
			      title: 'London Eye',
			      infoWindow: {
			        content: '<p>The London Eye is a giant Ferris wheel situated on the banks of the River Thames in London, England. The entire structure is 135 metres (443 ft) tall and the wheel has a diameter of 120 metres (394 ft).</p>' }
			    });

			    map.addMarker({
			      lat: 51.5007396,
			      lng: -0.1245299,
			      title: 'Big Ben',
			      infoWindow: {
			        content: '<p>Big Ben is the nickname for the great bell of the clock at the north end of the Palace of Westminster in London, and often extended to refer to the clock and the clock tower, officially named Elizabeth Tower.</p>'
			      }
			    });

			    map.addMarker({
			      lat: 51.518856,
			      lng: -0.1263371,
			      title: 'British Museum',
			      infoWindow: {
			        content: '<p>The British Museum is a museum in London dedicated to human history and culture.</p>'
			      }
			    });

			    map.addMarker({
			      lat: 51.5085822,
			      lng: -0.1283169,
			      title: 'National Gallery',
			      infoWindow: {
			        content: '<p>The National Gallery is an art museum on Trafalgar Square, London. Founded in 1824, it houses a collection of over 2,300 paintings dating from the mid-13th century to 1900.</p>'
			      }
			    });

			    map.addMarker({
			      lat: 51.5228316,
			      lng: -0.1553503,
			      title: 'Madame Tussauds',
			      infoWindow: {
			        content: '<p>Madame Tussauds is a wax museum in London with branches in a number of major cities.</p>'
			      }
			    });

			    map.addMarker({
			      lat: 51.5089465,
			      lng: -0.0764269,
			      title: 'Tower Of London',
			      infoWindow: {
			        content: '<p>Her Majesty\'s Royal Palace and Fortress, more commonly known as the Tower of London, is a historic castle on the north bank of the River Thames in central London, England, United Kingdom.</p>'
			      }
			    });
	
		}

		// API Publica
		return {

			init: init

		};

	});