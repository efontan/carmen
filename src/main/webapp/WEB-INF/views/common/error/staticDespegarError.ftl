<#include "/common/layout/layout.ftl">
<@drawLayout
    
    title = "selfservice.common.midespegar"
        
	cssFiles = [
		{"content" :  headerCSS!""},
		{"content" :  footerCSS!""},
	    {"media" : "screen", "href" : "${libCssPath}/despegar/pkg/common.css", "title" : "Despegar.com"}    ]
    
  	
	jsFiles = [
	    "${jsPath}/FrameworkJS/base.js"
	]
	
	jQueryVersion = "1.7.2"
	
	jsLibs = [
		"require-2.1.5.min.js",
		"amplify-1.1.0.min.js",
		"json2.min.js"
	]
	
	metaTags = [
	    { "http-equiv" : "X-UA-Compatible", "content" : "IE=Edge;chrome=1" },
	    { "charset" : "utf-8" }
	]
	
	pageContent = {
	    "bodyClass"     : "mi-despegar ${brandName} ${locale}",
		"headerHTML"    : headerHTML!"", 
		"footerHTML"    : footerHTML!"",
		"mainContainerClass" : "mi-despegar-log-in"
	}
	
	extraHeadData = newRelic.header
>
	<#-- Header -->
	<#include "/common/modules/header/header.ftl">	
	
	<div id="mainContainer" >
	
	    <h3 class="autogestionLogo spriteMiDespegar">
	       <span class="disabled"><@s.message "selfservice.common.midespegar" /></span>
	    </h3>
	
		<div class="error errorPageContenido">
			<h2 id="disculpaLangES" lang="es">Estamos trabajando para mejorar los servicios que le brindamos.</h2>
			<h2 id="disculpaLangEN" lang="en">We are currently working to improve the services of our website.</h2>
			<h2 id="disculpaLangPT" lang="pt">Estamos trabalhando para melhorar os servicos do nosso portal.</h2>
		</div>
	
	</div>


</@drawLayout>