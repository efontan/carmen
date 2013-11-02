<#-- <#import "/nibbler-library.ftl" as n /> -->

<#--Header HTML-->
<#-- <#assign headerHTML>
	<@nibblerRender headerParams /> 
</#assign> -->

<#--Header CSS-->
<#-- <#assign headerCSS>
	<@nibblerRender headerHeadParams /> 
</#assign> -->

<#--Footer HTML-->
<#-- <#assign footerHTML>
	<@nibblerRender footerParams />
</#assign> -->

<#--Footer CSS-->
<#-- <#assign footerCSS>
	<@nibblerRender footerHeadParams />
</#assign> -->

<#--Footer JS-->
<#-- <@addExtraBodyContent>
	<@nibblerRender headerJs />
	<@nibblerRender footerJs />
</@addExtraBodyContent> -->


<#--Global variable to store ready event operations -->
<#global onReadyEvent = "" />

<#--URL base de Mi Despegar -->
<#assign siteBaseUrl = "/" + getMessage("selfservice.locale" ) />


<#assign agentId = "" />
<#assign isOpe = "" />

<#if transaction?? >
	<#assign agentId = transaction.email />
	<#assign isOpe = isOperator?string />
</#if>

<#-- Datos Globales expuestos por ExtraDa -->
<#assign globalInfo = {
	"agentId"	: agentId,
	"country"	: country!"AR",
	"language"	: language!"ES",
	"locale"	: getMessage('selfservice.locale')!"midespegar",
	"isOperator":	isOpe
} />

<@setExtraData "global-info" globalInfo />

<#if transaction?? >
	<#assign transactionInfo = {
		"transactionId"	: transaction.transactionId,
		"token"			: transaction.token,
		"productId"     : transaction.productId,
		"productType"	: transaction.productType,
		"productId"		: transaction.productId,
		"locale"		: getMessage('selfservice.locale'),
		"defAction" 	: defAction!""
	} />
	<@setExtraData "transaction-info" transactionInfo />
</#if>

