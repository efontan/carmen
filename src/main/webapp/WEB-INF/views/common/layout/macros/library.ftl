

<#-- Agrupa las acciones JS del evento ready -->
<#macro onDocumentReady>

	<#assign jsCode>
		<#nested>
	</#assign>
	
	<#global onReadyEvent = onReadyEvent + jsCode />


</#macro>


<#-- Agrupa contenida a mostrarse antes del cierre de la etiqueta html -->
<#macro addRequireJsContent>

	<#assign content>
		<#nested>
	</#assign>
	
	<#global requireJsModules = requireJsModules + content />

</#macro>

<#-- Agrupa contenida a mostrarse luego de la apertura del tag body -->
<#macro addPreBodyContent>

	<#assign content>
		<#nested>
	</#assign>
	
	<#global preBodyContent = preBodyContent + content />

</#macro>

<#-- Agrupa contenida a mostrarse antes del cierre de la etiqueta body -->
<#macro addExtraBodyContent>

	<#assign content>
		<#nested>
	</#assign>
	
	<#global extraBodyContent = extraBodyContent + content />


</#macro>


<#-- Configuracion de RequireJS -->
<#macro requireJsConfig>
	<script>
		require.config({
			"baseUrl": "${libJsPath}/",

			"shim": {
				"handlebars": {
					"exports": "Handlebars"
        		},
				"amplify": {
					"deps": ["jquery"],
					"exports": "amplify"
        		},
        		bootstrap : {
        			"exports" : "jquery19"
        		}
			},
			map: {
		        "*": {
		            "jquery": "jquery19"
		        }
   			 },

		    "paths": {
		    	"async": "common/loaders/async",
				"jquery": "common/loaders/jquery",
		        "handlebars": "common/loaders/handlebars",
		        "amplify": "common/loaders/amplify",
		        "bootstrap" :  "${staticContent}/jslibs/bootstrap-2.3.2.min",
	        	"libs.amplify": "${staticContent}/jslibs/amplify-1.1.0.min",
		        "libs.handlebars": "${staticContent}/jslibs/handlebars-1.0.0.rc.1.min",
	        	"libs.jquery": "${staticContent}/jslibs/jquery-1.7.2.min",
	        	'underscore': 'common/helpers/lodash.min'
		    }
		});
		define("jquery19", ['${staticContent}/jslibs/jquery-1.9.1.min.js', 'bootstrap'], function () { 
			return jQuery.noConflict(true); 
		});
		
	</script>
</#macro>




<#-- Guardar datos extra que se envian a los modulos JS -->
<#macro setExtraData name values>

	<#-- Traducir de Json Object de Freemarker a Json Object de JS -->
	<#assign extraDataValues = "" />
	<#list values?keys as key>
		<#if values[key]??>
			<#assign extraDataVal = values[key]?trim?replace("\n","<br/>")?replace('"', "'") />
			<#assign extraDataValues =extraDataValues +  '"${key}":"${extraDataVal}",' />
		</#if>
	</#list>

	<#-- Solo agregar datos extra con contenido -->
	<#if extraDataValues != "">
	
	    <#-- Quitar ultima coma -->
	    <#assign lastCommaPosition = extraDataValues?last_index_of(",") />
		<#assign extraDataValues = extraDataValues?substring(0, lastCommaPosition) />

    	<@onDocumentReady>
			<@compress single_line=true> 
			extraData.set("${name}", {
					${extraDataValues}
			});
			</@compress>
		</@onDocumentReady>
		
	</#if>
	
</#macro>


<#-- Guardar datos extra que se envian a los modulos JS -->
<#macro setJsMessages name values>

	<#-- Traducir de Json Object de Freemarker a Json Object de JS -->
	<#assign extraDataValues = "" />
	<#list values?keys as key>
		<#if values[key]??>
			<#assign extraDataVal>
				<@s.message  values[key] />
			</#assign>
			<#assign extraDataValues =extraDataValues +  '"${key}":"${extraDataVal}",' />
		</#if>
	</#list>

	<#-- Solo agregar datos extra con contenido -->
	<#if extraDataValues != "">
	
	    <#-- Quitar ultima coma -->
	    <#assign lastCommaPosition = extraDataValues?last_index_of(",") />
		<#assign extraDataValues = extraDataValues?substring(0, lastCommaPosition) />

    	<@onDocumentReady>
			<@compress single_line=true> 
			localization.set("${name}", {
					${extraDataValues}
			});
			</@compress>
		</@onDocumentReady>
		
	</#if>
	
</#macro>




<#-- Genera un mensaje localizado en plural o singular -->
<#macro msg identifier quantity params=[]>
 
	<#assign id = identifier />

	<#if (quantity > 1)>
	    <#assign id = identifier + ".plural" />
	</#if>
		
	<#if (params?size > 0) >
		<@s.messageArgs id params />
	<#else>
		<@s.message id />
	</#if>

</#macro>


<#-- Retorna string de los mensajes localizados -->
<#function getMessage msg>
	<#assign string>
		<@s.message  msg />
	</#assign>
    <#return string>
</#function>



<#macro nibblerRender params="" >

<#--	<#if params.model??> -->
		<@n.nibblerRender params />
<#--	</#if> -->
	

</#macro>

<#macro dateToTextFlight date>
	<#assign dayOfWeek><@s.message "selfservice.day"+date.dayOfWeek().get()/></#assign>
	<#assign dayOfMonth = date.dayOfMonth().get()/>  
	<#assign month><@s.message "selfservice.month"+date.monthOfYear().get()/></#assign>
	<#assign year = date.getYear()/> 
	<#assign arguments = [dayOfWeek?capitalize,dayOfMonth,month?capitalize,year?c]/>
	<@s.messageArgs "selfservice.flights.date"  arguments/>
</#macro>

<#macro stopsFlightsText stopsCounts>
	<#assign stops = stopsCounts -1/>
	<#if (stops > 0)>
		<#assign arguments = [stops]/>
		<#if (stops > 1)>
      		<@s.messageArgs "selfservice.flights.stops" arguments/>
      	 <#else>
    		<@s.messageArgs "selfservice.flights.stop"  arguments/>
   		 </#if>
    <#else>
    	<@s.message "selfservice.flights.nonstop"/>
    </#if>	
</#macro>

<#macro periodOfTime date1 date2>
	<#assign totalMillis = date2.getMillis()-date1.getMillis()/>
	<#assign days = (totalMillis/86400000)?int/>
	<#assign hours = (totalMillis/3600000)?int - (24 * days)/>
	<#assign minutes = (totalMillis/60000)?int - (60 * hours) - (24 * 60 * days)/>
	<#if (days > 0)>
		<#assign arguments1 = [days, hours, minutes]/>
		<@s.messageArgs "selfservice.flights.longTripDuration" arguments1/>
	<#else>
		<#assign arguments2= [hours, minutes]/>
    	<@s.messageArgs "selfservice.flights.shortTripDuration" arguments2/>
    </#if>	
</#macro>

<#macro dateToText date onlyDate>
	<#assign dayOfWeek><@s.message "selfservice.day"+date.dayOfWeek().get()/></#assign>
	<#assign dayOfMonth = date.dayOfMonth().get()/>
	<#assign month><@s.message "selfservice.month"+date.monthOfYear().get()/></#assign>
	<#assign hour = date.hourOfDay().get()/>		
	<#if onlyDate>
		<#assign arguments1 = [dayOfMonth,month?capitalize]/>
		<@s.messageArgs "selfservice.hotels.date.onlyDate" arguments1/>
	<#else>
		<#if (hour?string?length>0) >
			<#assign arguments2 = [dayOfWeek?capitalize,dayOfMonth,month?capitalize,hour]/>
			<@s.messageArgs "selfservice.hotels.date.normal" arguments2/>
		<#else>			
			<#assign arguments3 = [dayOfWeek?capitalize,dayOfMonth,month?capitalize]/>
			<@s.messageArgs "selfservice.hotels.date.short" arguments3/>
    	</#if>	
    </#if>	
</#macro>