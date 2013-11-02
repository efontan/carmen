#Compass / Sass

#Dependencias
require 'picasso'
require 'ceaser-easing'
gem 'picasso', '~> 0.6.2'
gem 'ceaser-easing', '~> 0.7'


#Directorio CSS relativo al config.rb
css_dir           = "../webapp/WEB-INF/css"

#Directorio Sass relativo al config.rb
sass_dir          = "/"

#Directorio Images relativo al config.rb
images_dir        = "../webapp/WEB-INF/img"

#Directorio JS relativo al config.rb
javascripts_dir   = "../webapp/WEB-INF/js"

#Utilizar URLs relativas
relative_assets   = true

#Cache activada
sass_options      = { :cache => true }

#Sin comentarios
line_comments     = false

#Modo expandido
output_style      = :expanded

#URL hacia donde apuntan las imágenes
http_images_path = "/midespegar/img/"
