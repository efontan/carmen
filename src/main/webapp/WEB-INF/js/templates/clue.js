define(["handlebars"],function(a){var b=a.template,c=a.templates=a.templates||{};c["clue.hbs"]=b(function(a,b,c,d,e){c=c||a.helpers;var f="",g,h,i="function",j=this.escapeExpression;return f+='<div id="showClue" class="clue">\r\n	',h=c.characterJob,h?g=h.call(b,{hash:{}}):(g=b.characterJob,g=typeof g===i?g():g),f+=j(g)+"\r\n	",h=c.description,h?g=h.call(b,{hash:{}}):(g=b.description,g=typeof g===i?g():g),f+=j(g)+"\r\n	",h=c.inTheHouse,h?g=h.call(b,{hash:{}}):(g=b.inTheHouse,g=typeof g===i?g():g),f+=j(g)+"\r\n	",h=c.avatar,h?g=h.call(b,{hash:{}}):(g=b.avatar,g=typeof g===i?g():g),f+=j(g)+"\r\n</div>\r\n\r\n",f})})