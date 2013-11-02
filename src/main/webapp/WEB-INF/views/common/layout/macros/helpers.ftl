
<#macro warningBoxTemplate >
	<script id="warning-box-template" type="text/x-handlebars-template">
		<@warningBox
			customTitle = "{{#if title}}<h3>{{title}}</h3>{{/if}}"
			parameters  = "<span>{{{body}}}</span>"
			type		= "{{type}}"
		/>
	</script>
</#macro>


<#-- Estructura del warningBox-->
<#macro warningBox title="" text="" parameters="" customTitle="" type="alert">
	
	<div id="warningBox" class="warningBox ${type}">            
		
		<div class="icon mi-despegar-sprite-${type}-icon"></div>
		
		<div class="content">
		  
		  	<#if customTitle != "">
		  		${customTitle}
	     	<#elseif title != "">
				<h3><@s.message title /></h3>
			</#if>
		
			<div class="body">
				<#if text != "">
					<@s.message text />
				</#if>
				${parameters}				
			</div>
		</div>
	  
	</div>
	
</#macro>


<#-- Estructura inputs type text de forms-->
<#macro drawInputText id="" validations="" placeholder="" error="" span="" name="" label="" tabIndex = "", type="">
	
	<#assign inputType = "text" />
		<#if (type != "") >
		<#assign inputType = type />
	</#if>
	
	<#assign tabIndexAttrib = "" />
	<#if (tabIndex != "") >
		<#assign tabIndexAttrib = 'tabindex="' + tabIndex  + '"' />
	</#if>
	
	<div id="${id}-field" class="field-container" >
		<#if label != "">
			<label>${label}</label>
		</#if>
		<input ${tabIndexAttrib} name="${name}" data-validations="${validations}" id="${id}" type="${inputType}" placeholder="${placeholder}" class="medium-input">
		<#if span != "">
			${span}
		</#if>
		<#if error != "">
			<div class="error-msg">
				<span class="commonSprite errorCrossIcon icon-error"></span>
				${error}
			</div>
		</#if>
	</div>
	
</#macro>

<#macro dateToTextFlight date>
	<#assign dayOfWeek><@s.message "selfservice.day"+date.dayOfWeek().get()/></#assign>
	<#assign dayOfMonth = date.dayOfMonth().get()/>  
	<#assign month><@s.message "selfservice.month"+date.monthOfYear().get()/></#assign>
	<#assign year = date.getYear()/> 
	<#assign arguments = [dayOfWeek?capitalize,dayOfMonth,month?capitalize,year?c]/>
	<@s.messageArgs "selfservice.flights.date"  arguments/>
</#macro>