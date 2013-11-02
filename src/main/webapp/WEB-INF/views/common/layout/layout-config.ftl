<#--Brand-->
<#assign brandName = "" />

<#--Path al framework CSS-->
<#assign cssPath = cssversioned!"css" />

<#--Path al framework JS-->
<#assign jsPath = jsversioned!"js" />

<#--Path a las imagenes estaticas-->
<#assign imgPath = imgversioned!"img" />

<#--Path a los CSS de Mi Despegar -->
<#assign libCssPath = selfserviceVersionedCss!"css"?replace("css", "resources/css") />

<#--Path a los JS de Mi Despegar -->
<#assign libJsPath = selfserviceVersionedJs!"js"?replace("js", "resources/js") />

<#--Path a las imagenes de Mi Despegar -->
<#assign libImgPath = selfserviceVersionedImg!"img"?replace("img", "resources/img") />

<#--Path a StaticContent-->
<#assign staticContent = staticContent!"" />

<#--Cultura-->
<#assign locale = language!"" + "_" + country!"" />

<#--Global variable to store RequireJS module initializations -->
<#global requireJsModules = "" />

<#--Global variable to store ready event operations -->
<#global onReadyEvent = "" />

<#--Global variable to store Extra body content -->
<#global extraBodyContent = "" />

<#--Global variable to store Extra body content -->
<#global preBodyContent = "" />
 
 