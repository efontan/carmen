/**
 * Overlay
 */



define(["handlebars", "jquery", "common/helpers/spin",
		"common/modules/extraData", "underscore", "common/modules/localization",

		"templates/common-overlay-template-amd", "templates/op-loading"
	],

	function(Handlebars, $, Spinner, extraData, _, localization) {

		return function(options) {

			//Objeto con la configuración del módulo
			var _settings = {};

			//Objeto con la información del trigger que muestra el overlay
			var _trigger = {};

			// bloqueo de operacion de cierre
			var closeBlocked = false;
			var isDynamicDialog = false;

			/**
			 * Muestra un overlay con las opciones especificadas
			 */

			function open(options) {

				//Setea las opciones del overlay para uso interno
				_overlaySettings(options);

				//Obtiene la información del elemento que originó la apertura del overlay
				_triggerInfo();

				//Renderiza el overlay en las posiciones estandar
				_renderOverlay();

				//Muestra el overlay si es necesario
				_backgrundOverlay("show");

				// ejecutar acciones luego de mostrar el popup
				if (!_.isUndefined(_settings.callback)) {
					_settings.callback();
				}

			}

			/**
			 * Cierra el overlay
			 */

			function close(execOnClose) {

				if (closeBlocked) {
					return false;
				}

				//Pregunta por las opciones
				if (_.isEqual(_settings.remove, true)) {

					_removeOverlay();

				} else {

					_overlayEffect($("#" + _settings.id), "hide");

				}


				if (_settings.onClose && execOnClose) {
					_settings.onClose();
				}
				//Oculta el overlay si es necesario
				_backgrundOverlay("hide");

			}

			/**
			 * Crea las opciones internas del overlay
			 */

			function _overlaySettings(options) {

				//Opciones predeterminadas
				var defaults = {
					trigger: undefined, //Elemento que ejecuta la acción de apertura
					type: "popover",
					title: null, //Título del overlay
					body: "", //Cuerpo del overlay,
					content: null, //Contenido completo del overlay
					position: _.isEqual(options.type, "dialog") ? "fixed" : "right-bottom", //Posición del overlay
					close: true, //Mostrar la opción para cerrar el overlay
					arrow: true, //Mostrar la flecha indicadora del overlay
					id: null, //ID del overlay
					className: "", //Clase del overlay
					remove: false, //Si se debe remover del DOM al cerrarlo,
					effect: "fade", //Efecto al mostrar el overlay,
					background: false, //Mostrar un background junto al overlay,
					escape: true, //Cierra el overlay al presionar ESC,
					template: undefined, //Template personalizado del overlay,
					onClose: null
				};

				//Extiende las opciones con la configuración por default
				_settings = $.extend({}, defaults, options);

			}

			/**
			 * Crea el objeto con la información del trigger que muestra el overlay
			 */

			function _triggerInfo() {

				var $trigger = $(_settings.trigger);
				var offset = $trigger.offset();

				_trigger.element = $trigger;
				_trigger.width = $trigger.outerWidth(false);
				_trigger.height = $trigger.outerHeight(false);
				_trigger.left = offset.left;
				_trigger.top = offset.top;

			}

			/**
			 * Renderiza el overlay
			 */

			function _renderOverlay() {

				//Pregunta si el elemento ya tiene un overlay asignado. Si no lo tiene, crea uno y lo atacha al body
				if (_.isUndefined($(_settings.trigger).data("uxCommonOverlayData"))) {

					var html = "";

					//Setea el modelo básico. Para title, body y content pregunta si se esta pasando un objeto jquery
					var model = {
						title: (_settings.title instanceof jQuery ? _settings.title.html() : _settings.title),
						body: (_settings.body instanceof jQuery ? _settings.body.html() : _settings.body),
						content: (_settings.content instanceof jQuery ? _settings.content.html() : _settings.content),
						close: _settings.close,
						arrow: _settings.arrow,
						className: _settings.className,
						position: _settings.position,
						overlayPosition: _overlayPosition(),
						id: _overlayId(),
						type: _settings.type
					};

					//Setea el template
					if (!_.isUndefined(_settings.template)) {

						var source = $(_settings.template).html();
						var template = Handlebars.compile(source);
						html = template(model);

					} else {

						html = Handlebars.templates["common-overlay.ftl"](model);

					}

					//Apendea el HTML en el body y lo muestra
					_overlayEffect($(html).appendTo("body"), "show");
					if (isDynamicDialog) {
						$(".ux-common-overlay-body", "#" + _settings.id).addClass('loadingInstance');
					}

					//Guarda la información del overlay en el elemento trigger
					$(_settings.trigger).data("ux-common-overlay-data", _settings);

					//Calibra la posición del overlay
					_calibrateOverlay();

					//Verifica que el overlay sea visible
					_fixOverlay();

					//Binds para cerrar
					_bindOverlayClose();

					//Caso contrario muestra el overlay oculto leyendo su ID
				} else {
					_overlayEffect($("#" + $(_settings.trigger).data("uxCommonOverlayData").id), "show");

				}


			}


			/**
			 * Calcula la posición del overlay
			 */

			function _overlayPosition() {

				if (!_.isEqual(_settings.position.indexOf("right-"), -1)) {

					return "left: " + (_trigger.left + _trigger.width) + "px;" + "top: " + _trigger.top + "px;";

				} else if (!_.isEqual(_settings.position.indexOf("left-"), -1)) {

					return "left: " + _trigger.left + "px;" + "top: " + _trigger.top + "px;";

				} else if (!_.isEqual(_settings.position.indexOf("bottom-"), -1)) {

					return "left: " + _trigger.left + "px;" + "top: " + (_trigger.top + _trigger.height) + "px;";

				} else if (!_.isEqual(_settings.position.indexOf("top-"), -1)) {

					return "left: " + _trigger.left + "px;" + "top: " + _trigger.top + "px;";

				}

			}


			/**
			 * Calcula un ID para el overlay y lo asigna al elemento trigger como referencia
			 */

			function _overlayId() {

				var id = _settings.id;

				//Si el ID es nulo, entonces crea uno y lo asigna a settings
				if (_.isNull(_settings.id)) {
					id = "ux-common-overlay-" + Math.random().toString().replace(".", "");
					_settings.id = id;
				}


				return id;

			}


			/**
			 * Termina de calibrar la posición del overlay
			 */

			function _calibrateOverlay() {

				//Obtiene la información del overlay renderizado
				var $overlay = $("#" + _settings.id);
				var overlayWidth = $overlay.outerWidth(false);
				var overlayHeight = $overlay.outerHeight(false);
				var overlayMarginBottom = parseInt($overlay.css("marginBottom"), 10);
				var overlayMarginRight = parseInt($overlay.css("marginRight"), 10);

				//Objeto con los estilos que se aplicarán al overlay
				var styles = {};

				//Margenes derechos
				if (!_.isEqual(_settings.position.indexOf("right-"), -1)) {

					styles.marginLeft = overlayMarginRight;

				} else if (!_.isEqual(_settings.position.indexOf("left-"), -1)) {

					styles.marginLeft = -(overlayWidth + overlayMarginRight);

				} else if (!_.isEqual(_settings.position.indexOf("-right"), -1)) {

					styles.marginLeft = -(overlayMarginRight * 2) + (_trigger.width / 2);

				} else if (!_.isEqual(_settings.position.indexOf("-left"), -1)) {

					styles.marginLeft = -overlayWidth + ((overlayMarginRight * 2) + (_trigger.width / 2));

				} else if ((!_.isEqual(_settings.position.indexOf("top-center"), -1)) || !_.isEqual(_settings.position.indexOf("bottom-center"), -1)) {

					styles.marginLeft = -((overlayWidth / 2) - (_trigger.width / 2));

				} else if (_settings.position == "fixed") {

					styles.marginLeft = -(overlayWidth / 2);

				}

				//Margen superior
				if (!_.isEqual(_settings.position.indexOf("-bottom"), -1)) {

					styles.marginTop = -(overlayMarginBottom * 2) + (_trigger.height / 2);

				} else if (!_.isEqual(_settings.position.indexOf("-top"), -1)) {

					styles.marginTop = -overlayHeight + ((overlayMarginBottom * 2) + (_trigger.height / 2));

				} else if (!_.isEqual(_settings.position.indexOf("bottom-"), -1)) {

					styles.marginTop = overlayMarginBottom;

				} else if (!_.isEqual(_settings.position.indexOf("top-"), -1)) {

					styles.marginTop = -(overlayHeight + overlayMarginBottom);

				} else if ((!_.isEqual(_settings.position.indexOf("left-center"), -1)) || !_.isEqual((_settings.position.indexOf("right-center"), -1))) {

					styles.marginTop = -((overlayHeight / 2) - (_trigger.height / 2));

				} else if (_.isEqual(_settings.position, "fixed")) {

					styles.marginTop = -(overlayHeight / 2);

				}

				//Aplica los estilos
				$overlay.css(styles);

			}

			/**
			 * Verifica si el overlay es visible, caso contrario realiza las correcciones necesarias
			 */

			function _fixOverlay() {

				//Obtiene la posición del overlay
				var $overlay = $("#" + $(_settings.trigger).data("uxCommonOverlayData").id);
				var overlayOffset = $overlay.offset();
				var overlayWidth = $overlay.outerWidth(false);
				var windowWidth = $(window).width();

				//En caso que este fuera de pantalla, fuerza la renderización del overlay en una posición nueva
				if (overlayOffset.left < 0) {

					_settings.position = "right-center";

					_removeOverlay();

				} else if ((overlayOffset.left + overlayWidth) > windowWidth) {

					_settings.position = "left-center";

					_removeOverlay();

				}

				_renderOverlay();

			}

			/**
			 * Elimina el overlay del DOM y la información del elemento trigger
			 */

			function _removeOverlay() {

				var $overlay = $("#" + _settings.id);

				//Elimina la información en el elemento
				$(_settings.trigger).removeData("uxCommonOverlayData");

				//Eliminar el overlay
				$overlay.remove();

			}

			/**
			 * Bindea los eventos para cerrar el overlay
			 */

			function _bindOverlayClose(html) {

				$(".ux-common-overlay-close").on("click", function() {
					isFakeClick = true;
					close(true);

				});

				//Comprueba la configuración para cerrar el overlay al presionar ESC
				if (_.isEqual(_settings.escape, true)) {

					$(document).on(("keyup.ux.common.modules.overlay"), function(e) {
						isFakeClick = true;
						if (e.keyCode == 27) {
							close(false);

						}

					});

				}

				//Comprueba la configuración del background
				if (_.isEqual(_settings.background, true)) {

					//Utiliza delegate porque el background aún no se creó
					$(document).on("click", ".ux-common-overlay-background", function() {
						close(false);


					});

				}

			}

			/**
			 * Realiza un efecto en el overlay dependiendo de la configuración
			 */

			function _overlayEffect($selector, action) {

				if (_.isEqual(action, "show")) {

					if (_.isEqual(_settings.effect, "fade")) {

						$selector.stop(true, true).fadeIn();

					} else {

						$selector.stop(true, true).show();

					}

				} else if (_.isEqual(action, "hide")) {

					if (_.isEqual(_settings.effect, "fade")) {

						$selector.stop(true, true).fadeOut();



					} else {

						$selector.stop(true, true).hide();

					}

				}

			}

			/**
			 * Muestra un background por debajo del overlay
			 */

			function _backgrundOverlay(action) {

				if (closeBlocked) {
					return false;
				}

				if (_.isEqual(action, "show")) {

					if (_.isEqual(_settings.background, true)) {

						if (_.isEqual($(".ux-common-overlay-background").length, 0)) {

							$("body").append("<div class='ux-common-overlay-background'></div>");

						} else {
							$(".ux-common-overlay-background").show();
						}

					}

				} else if (_.isEqual(action, "hide")) {

					if (_.isEqual(_settings.background, true)) {

						_overlayEffect($(".ux-common-overlay-background"), "hide");

					}

				}

			}

			function blockClose(status) {
				closeBlocked = status;
				var popupContainer = $("#" + _settings.id);

				if (status) {
					popupContainer.find('.mod-btn').addClass("disabled");
					var template = Handlebars.templates['op-loading.hbs'];
					var context = {
						id: _settings.id + "-loading",
						"block": true
					};
					var popupContent = template(context);
					popupContainer.prepend(popupContent);

					_loadSpinner("spin");


				} else {
					popupContainer.find('.mod-btn').removeClass("disabled");
					popupContainer.find("#" + _settings.id + "-loading").remove();
				}
			}


			function openDynamicDialog(options) {
				var cached_content = true;
				var popupContent = extraData.get("popup-" + _settings.id);
				isDynamicDialog = true;

				if (!popupContent) {
					var template = Handlebars.templates['op-loading.hbs'];
					var context = {
						id: options.id + "-loading"
					};
					popupContent = template(context);
					cached_content = false;
				}

				var dynamicDialogOptions = {
					type: "dialog",
					background: true,
					body: popupContent,
					className: options.id + ' ' + 'popup-fixed',
					remove: false

				};

				var opts = $.extend(dynamicDialogOptions, options);

				//Setea las opciones del overlay para uso interno
				_overlaySettings(opts);

				//Obtiene la información del elemento que originó la apertura del overlay
				_triggerInfo();

				//Renderiza el overlay en las posiciones estandar
				var popupExist = $("#" + _settings.id).length > 0 ? true : false;

				_renderOverlay();

				if (!cached_content) {
					_loadSpinner("spin");
				}

				//Muestra el overlay si es necesario
				_backgrundOverlay("show");


				// ejecutar acciones luego de mostrar el popup
				if (!_.isUndefined(_settings.callback) && !popupExist) {
					_settings.callback();
				}


			}

			function _loadSpinner(id) {
				var spinner_opts = {
					lines: 13, // The number of lines to draw
					length: 0, // The length of each line
					width: 5, // The line thickness
					radius: 17, // The radius of the inner circle
					corners: 1, // Corner roundness (0..1)
					rotate: 0, // The rotation offset
					color: '#000', // #rgb or #rrggbb
					speed: 1, // Rounds per second
					trail: 60, // Afterglow percentage
					shadow: false, // Whether to render a shadow
					hwaccel: false, // Whether to use hardware acceleration
					className: 'spinner', // The CSS class to assign to the spinner
					zIndex: 1, // The z-index (defaults to 2000000000)
					top: 'auto', // Top position relative to parent in px
					left: 'auto' // Left position relative to parent in px
				};
				var target = document.getElementById(id);

				var spin = new Spinner(spinner_opts).spin(target);
			}

			function updateContent(content) {
				$("#" + _settings.id).find(".ux-common-overlay-body").html(content);
				if (isDynamicDialog) {
					$("#" + _settings.id).find(".ux-common-overlay-body").removeClass('loadingInstance');
				}
				_calibrateOverlay();


				extraData.setOnce("popup-" + _settings.id, content);
			}

			function updateTitle(title) {
				$(".ux-common-overlay-header","#" + _settings.id).find("h4").html(title);



				_calibrateOverlay();
			}

			function destroyOnClose(shouldDestoy) {
				_settings.remove = shouldDestoy;
			}


		

		function enableLoader()
		{	
				var template = Handlebars.templates['op-loading.hbs'];
				var context = {
					id: "newSpiner"
				};
				popupContent = template(context);
				updateContent(popupContent);

				_loadSpinner("spin");

				$(".ux-common-overlay-container").css("min-height","154px");

			
		}
		
		

			function renderPopupMessage(message_type, module) {
				
				// template de mensajes
				var template = Handlebars.templates['overlay-message.hbs'];
				
				if(_.isEqual(message_type, 'error') || _.isEqual(message_type, 'success')){
					// mensajes a mostrar
					var messages = {
						"error": localization.jsonGet(module, 'popup_error_desc'),
						"success": localization.jsonGet(module, 'success')
					};

					var message = {
						'type': message_type,
						'text': messages[message_type]
					};
					
					// cambiar titulo en el caso de error
					if (message_type == "error") {
						updateTitle(localization.jsonGet(module, 'popup_error_title'));
						//tracker.track("menuYSubMenu", "popupOperationError", module);
					}
				}else{
					var type = message_type.split('_');
					var message = {
						'type': 'popup-text',
						'text': localization.jsonGet(module, message_type)
					};
					if(_.isEqual(type[0], 'alert')){
						message['icon'] = 'alert';
					}
				}

				var popupContent = template(message);


				updateContent(popupContent);
			}


			return {
				open: open,
				openDynamicDialog: openDynamicDialog,
				close: close,
				calibrateOverlay: _calibrateOverlay,
				blockClose: blockClose,
				updateContent: updateContent,
				destroyOnClose: destroyOnClose,
				enableLoader: enableLoader,
				updateTitle: updateTitle,
				renderPopupMessage: renderPopupMessage
			};

		};
	});
