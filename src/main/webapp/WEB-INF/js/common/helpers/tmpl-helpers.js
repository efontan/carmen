/**
 * Funciones comunes para templates de Handlebars - Mi Despegar
 */

define(["jquery", "handlebars", "common/modules/localization"], function($, Handlebars, localization) {

	/**
	 * _Each
	 * Helper que permite iterar sobre un objeto o arreglo, permitiendo acceder al
	 * index y total de elementos sobre el elemento. Opcionalmente se puede enviar
	 * más información para ser accedida dentro del contexto
	 * @see https://gist.github.com/1048968
	 *
	 * {{#_each object|array info=info}}
	 *   {{_data.index}}
	 *   {{_data.total}}
	 *   {{_data.extra.info}}
	 * {{_each}}
	 */
	Handlebars.registerHelper("_each", function(context, options) {

		var buffer = "";
		var total = context.length;
		var index = 0;
		var emptyHash = $.isEmptyObject(options.hash);

		for (var i = 0, j = total; i < j; i++) {

			var item = {};

			//Guarda la información del objeto/array en data
			item.data = context[i];
			item.index = index;
			item.list = context;

			//Información privada la guarda en _data
			item._data = {};
			item._data.index = index;
			item._data.total = total;

			//Si existe información extra, la guarda en _data.extra
			if (emptyHash === false) {
				item._data.extra = options.hash;
			}

			buffer += options(item);

			index++;

		}

		return buffer;

	});


	/**
	 * Debug
	 * Permite mostrar el contexto donde se llama a la directiva y un valor opcional
	 * @see https://gist.github.com/1468937
	 *
	 * {{debug value}}
	 */
	Handlebars.registerHelper("debug", function(value) {

		console.log("\nCurrent Context");
		console.log("====================");
		console.log(this);

		if (arguments.length > 1) {
			console.log("Value");
			console.log("====================");
			console.log(value);
		}

	});


	/**
	 * Get
	 * Pasando un arreglo o un objeto, permite acceder a una entrada determinada del elemento
	 * @see https://gist.github.com/1346716
	 *
	 * {{#get object|array index=key|index}}
	 *   {{value}}
	 * {{get}}
	 *
	 * {{get object="{/"key/":/"value/"}" key="key"}}
	 *
	 * {{get array="1,2,3" index=0}}
	 */
	Handlebars.registerHelper('get', function(context, options) {

		if (context.hash) {

			if (context.hash.array) {

				var array = context.hash.array;

				array = array.split(",");

				return array[context.hash['index']];

			} else if (context.hash.object) {

				var object = context.hash.object;

				object = $.parseJSON(object);

				return object[context.hash['key']];

			}

		} else {

			return options(context[options.hash['index']]);

		}

	});


	/**
	 * Compare
	 * Permite realizar operaciones de comparación entre dos valores
	 * @see https://gist.github.com/1890659
	 *
	 * {{#compare value1 value2 operator=operator}}
	 * {{compare}}
	 */
	Handlebars.registerHelper('compare', function(lvalue, rvalue, options) {

		if (arguments.length < 3) {

			throw new Error("Handlerbars Helper 'compare' needs 2 parameters");

		} else {

			var operator = options.hash.operator || "==";

			lvalue = lvalue === "null" ? null : lvalue;
			rvalue = rvalue === "null" ? null : rvalue;

			var operators = {
				'==': function(l, r) {
					return l == r;
				},
				'===': function(l, r) {
					return l === r;
				},
				'!=': function(l, r) {
					return l != r;
				},
				'<': function(l, r) {
					return l < r;
				},
				'>': function(l, r) {
					return l > r;
				},
				'<=': function(l, r) {
					return l <= r;
				},
				'>=': function(l, r) {
					return l >= r;
				},
				'typeof': function(l, r) {
					return typeof l == r;
				}
			};

			if (!operators[operator]) {

				throw new Error("Handlerbars Helper 'compare' doesn't know the operator " + operator);

			} else {

				var result = operators[operator](lvalue, rvalue);

				if (result) {

					return options.fn(this);

				} else {

					return options.inverse(this);

				}

			}

		}

	});

	/**
	 * Even_odd
	 * Devuelve un string si el valor es par o impar
	 */
	Handlebars.registerHelper("even_odd", function(index) {

		return (index % 2 === 0 ? "even" : "odd");

	});

	/**
	 * Calcule
	 * Permite realizar calculos matematicos simples entre dos valores
	 *
	 * {{calcule value1 value2 symbol=symbol}}
	 */
	Handlebars.registerHelper('calcule', function(lvalue, rvalue, options) {

		if (arguments.length < 3) {

			throw new Error("Handlerbars Helper 'compare' needs 2 parameters");

		} else {

			var symbol = options.hash.symbol || "+";

			var symbols = {
				'+': function(l, r) {
					return l + r;
				},
				'-': function(l, r) {
					return l - r;
				},
				'*': function(l, r) {
					return l * r;
				},
				'%': function(l, r) {
					return l % r;
				}
			};

			if (!symbols[symbol]) {

				throw new Error("Handlerbars Helper 'compare' doesn't know the symbol " + symbol);

			} else {

				return symbols[symbol](lvalue, rvalue);

			}

		}

	});


	/**
	 * in
	 * Permite indicar si un elemento se encuentra dentro de un arreglo
	 *
	 * {{#in array="AR,BR,PE,VE" index="AR"}} ..... {{/in}}
	 *
	 * La inversa, ejecuta true cuando un elemento no se encuentra en el arreglo,
	 * se debe indicar con op='!'
	 *
	 * {{#in array="AR,BR,PE,VE" index="AR" op='!'}} ..... {{/in}}
	 */

	Handlebars.registerHelper('in', function(context, options) {

		if (context.hash) {

			var array = context.hash.array;

			array = array.split(",");

			if ($.inArray(context.hash['index'], array) >= 0) {
				if (context.hash['op'] == '!') {return context.inverse(this);}
				return context.fn(this);
			} else {
				if (context.hash['op'] == '!') {return context.fn(this);}
				return context.inverse(this);
			}
		}

	});

	Handlebars.registerHelper('field-container', function(field) {

		var result = '<div id="' + field.field_id + '-field" class="field-container">';
		var field_type = (typeof field.type != "undefined") ? field.type : "text";

		if (typeof field.label != "undefined") {
			result += '<label> ' + field.label + ' </label>';
		}

		var tabIndex = 'tabindex=" ' + field.tabIndex + '"';

		result += '<input ' + tabIndex + ' name="' + field.name + '" data-validations="' + field.validations + '" id="' + field.field_id + '" type="' + field_type + '" placeholder="' + field.placeholder + '" class="medium-input">';
		result += '<div class="error-msg">';
		result += '<span class="commonSprite errorCrossIcon icon-error"></span>';
		result += field.error_message;
		result += '</div>';
		result += '</div>';

		return new Handlebars.SafeString(result);
	});


	Handlebars.registerHelper('partial', function(templateName, data, context) {
		return new Handlebars.SafeString(Handlebars.templates[templateName + '.hbs'](data));
	});

	Handlebars.registerHelper('tt', function(module_id, key) {
		var result = localization.jsonGet(module_id, key);

		return result;
	});

	//{{#each_with_index records}}
	// 	<li class="legend_item{{index}}"><span></span>{{Name}}</li>
	// {{/each_with_index}}
	Handlebars.registerHelper('each_with_index', function(array, obj) {
		var buffer = "";
		for (var i = 0, j = array.length; i < j; i++) {
			var item = array[i];

			// if item is already an object just add the index property
			if (typeof(item) == 'object') {
				item['index'] = i;
			} else { // make an object and add the index property
				item = {
					value: item, // TODO: make the name of the item configurable
					index: i
				};
			}

			buffer += obj.fn(item);
		}

		// return the finished buffer
		return buffer;

	});


});