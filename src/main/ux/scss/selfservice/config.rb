#Variables
resources	= File.join("..", "..", "resources")
shared		= File.join(File.dirname(__FILE__), "..", "shared")
product		= File.basename(File.dirname(__FILE__))

#Importa la configuración común
require File.join(shared, 'shared.rb')

#Directorio Sass relativo al config.rb
sass_dir          = "/"

#Directorio CSS relativo al config.rb
css_dir           = File.join(resources, "ux-library", "css", product)

#Directorio Images relativo al config.rb
images_dir        = File.join(resources, "ux-library", "images")

#Directorio JS relativo al config.rb
javascripts_dir   = File.join(resources, "ux-library", "js")

#Directorio Fonts relativo al config.rb
fonts_dir		  = File.join(resources, "ux-library", "fonts")

#Estilo de los CSS
output_style      = Shared.output_style

#URLs relativas
relative_assets   = Shared.relative_assets

#Opciones de Sass
sass_options      = Shared.sass_options

#Comentarios
line_comments     = Shared.line_comments

#Scss comunes
additional_import_paths = [
    shared
]

#Presición de decimales
Sass::Script::Number.precision = Shared.precision

#Renombrado de sprite
on_sprite_saved do |filename|
	puts filename
	Shared.on_sprite_saved(filename, product)
end

#Reemplazo del nombre del sprite en los css
on_stylesheet_saved do |filename|
	Shared.on_stylesheet_saved(filename, product)
end