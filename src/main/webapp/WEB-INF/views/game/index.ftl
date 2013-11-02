<#include "/common/layout/layout.ftl">
<@drawLayout
    
    title = "selfservice.common.midespegar"
        
	cssFiles = [
		{"content" :  headerCSS!""},
		{"content" :  footerCSS!""},
	    {"media" : "screen", "href" : "${libCssPath}/despegar/pkg/common.css", "title" : "Despegar.com"},
   	    {"media" : "screen", "href" : "${libCssPath}/despegar/pkg/game.css", "title" : "Despegar.com"}
    ]
    
  	
	jsFiles = [
	    "${libJsPath}/pkg/common.js"
	    "${libJsPath}/pkg/game.js",
	    "http://maps.google.com/maps/api/js?key=AIzaSyD7B_GwDao4BElWQL7OU9KN1IKtAaNRUUE&sensor=true"
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
		"mainContainerClass" : ""
	}
	
	extraHeadData = ''
>

	<#include "index-content.ftl">


</@drawLayout>