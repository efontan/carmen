define(["handlebars"],function(a){var b=a.template,c=a.templates=a.templates||{};c["op-loading.hbs"]=b(function(a,b,c,d,e){function l(a,b){return"\r\n		<div class='popupBlock'></div>\r\n	"}c=c||a.helpers;var f="",g,h,i="function",j=this.escapeExpression,k=this;f+='<div id="',h=c.id,h?g=h.call(b,{hash:{}}):(g=b.id,g=typeof g===i?g():g),f+=j(g)+"\">\r\n	<span id='spin'>\r\n		<h4 class='loading-label'>Cargando</h4>\r\n	</span>\r\n	",g=b.block,g=c["if"].call(b,g,{hash:{},inverse:k.noop,fn:k.program(1,l,e)});if(g||g===0)f+=g;return f+="\r\n		\r\n</div>\r\n\r\n",f})})