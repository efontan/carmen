<#include "/common/layout/layout.ftl">
<@drawLayout
    
    title = "selfservice.common.midespegar"
        
	cssFiles = [
		{"content" :  headerCSS},
		{"content" :  footerCSS},
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
		"headerHTML"    : headerHTML, 
		"footerHTML"    : footerHTML,
		"mainContainerClass" : "mi-despegar-log-in"
	}
	
	extraHeadData = newRelic.header
>
	<#-- Header -->
	<#include "/common/modules/header/header.ftl">	
	
	<#assign parameters>
		<p class="body">
			<a href="${siteBaseUrl}" title="<@s.message 'selfservice.error.back' />">
				<@s.message 'selfservice.error.back' /> 
			</a>
		</p>
	</#assign>

	<@viewHelpers.warningBox 
		title = 'selfservice.error.title' 
		text = 'selfservice.error.desc'
		parameters = parameters

 	/>


</@drawLayout>