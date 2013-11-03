define([
		"amplify", "underscore",

		"common/modules/logger", "common/modules/extraData",

		"modules/service-definition/common-services",

		"game/modules/ar_map_controller"

	],

	function(amplify, _, logger, extraData, commonServices, arMapController) {
	

		/**
		 * Inicializador
		 * @param {object} options Objeto con todas las opciones necesarias
		 */

		function init(options) {

			logger.info("[INFO] El Carmen Barbieri.");
			
			// game services
			commonServices.init();
			
			amplify.request({
				resourceId: "carmen.initialize",
				data: {
					//token: extraData.get("transaction-info").token
				},
				success: function(service_data) {
					
					extraData.set("startPoint", service_data.data);
					
					_bindEvents(service_data);

				},
				error: function(message, level) {
					
					alert('error');

				}
			});
			

		}

		function _bindEvents () {

			$(".avatar").on("click", function (event) {
				event.preventDefault();
				$(".avatar").removeClass("avatar-selected");
				$(this).addClass("avatar-selected");
			});
			
			$('#form-player').submit(function(event){
				event.preventDefault();
				
				extraData.set('avatarGenre', $('.avatar-selected').data('full-genre'));
				
				amplify.request({
					resourceId: "carmen.setPlayer",
					data: {
						"name": $('#name').val(),
						"email": $('#email').val(),
						"genre": $('.avatar-selected').data('genre')
					},
					success: function(data) {
						
						extraData.set('token', $('#email').val());
						
						_getStatus(function(data, success) {
							
							if(success){
								_initTopBar(data);
								_contextSwitch();
								arMapController.init();
							}else alert('error getStatus');
							
						});
					},
					error: function(message, level) {
						alert('error setUser');
					}
				});
				
			});
			
		}
		
		function _getStatus(callback) {

			amplify.request({
				resourceId: "carmen.getStatus",
				data: {
					token: extraData.get("token")
				},
				success: function(service_data) {

					var context = {};

					// chequear que no sea error
					var is_error = service_data.responseStatus;

					if (is_error) {

						context = service_data.data;

					}

					// devolver informacion
					callback(context, is_error);

				},
				error: function(message, level) {

					callback({}, true);

				}
			});

		}
		
		function _contextSwitch(){
			$('.index').addClass('hide');
			$('.main-game').removeClass('hide');
		}
		
		function _initTopBar(data){
			var date = new Date(data.actualDate);
			$('.actual-date').html(date.toLocaleDateString());
			$('.money').html(data.remainingMoney);
		}


		// API Publica
		return {

			init: init

		};

	});