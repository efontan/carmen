<#ftl encoding="UTF-8" />
<#compress>
	<#import "/spring.ftl" as s />
	<#include "/common/layout/macros/library.ftl" />
	<#include "/common/layout/layout-config.ftl" />
	<#include "/common/variables.ftl">
	<#import "/common/layout/macros/helpers.ftl" as viewHelpers />
</#compress>
<#macro drawLayout

    title = ""
    
    cssFiles = [
	    {"media" : "screen", "href" : "...", "title" : "Despegar.com"}
	]
	
	jsFiles = []

	jQueryVersion = ""
	
	jsLibs = [
		"amplify-1.1.0.min.js",
		"json2.min.js"
	],
	
	metaTags = [ 
	    { "charset" : "utf-8" },
	    { "http-equiv" : "X-UA-Compatible",  "content" : "IE=Edge;chrome=1" }
	]
	
	pageContent = {
		"bodyClass"     : "",
		"headerHTML"    : "", 
		"footerHTML"    : "", 
		"mainContainerClass" : ""
	}
	
	extraHeadData = ""
		
>
<#compress>
<!DOCTYPE html>

<head>

    <title>El Carmen Barbieri</title>

	
	<!-- CSS Files -->
	<#list cssFiles as cssFile>   	
		<#if cssFile.content??>
        	${cssFile.content}
        <#else>
        	<link rel="stylesheet" media="${cssFile.media}" href="${cssFile.href}" title="${cssFile.title}" type="text/css">
        </#if>	
    </#list>

</head>

<body class="bootstrap">
	
	<div id="mainContainer" class="">
    
		<#nested>
		
	</div>
	
	<#if jQueryVersion?? && jQueryVersion != "">
        <script type="text/javascript" charset="utf-8" src="${staticContent}/jslibs/jquery-${jQueryVersion}.min.js"></script>	
    </#if>
    
    <!-- JS libs Files -->
	<#list jsLibs as jsLibFile>   	
      <script type="text/javascript" charset="utf-8" src="${staticContent}/jslibs/${jsLibFile}"></script> 
    </#list>
    


	<!-- Configuracion de Require JS -->
    <@requireJsConfig />
    
    <!-- Modulos e inicializaciones de Require JS -->
	${requireJsModules}
    
    
	<!-- JS Files -->
	<#list jsFiles as jsFile>   	
        <script type="text/javascript" charset="utf-8" src="${jsFile}"></script>
    </#list>

	
</body>

</html>

</#compress>
</#macro>