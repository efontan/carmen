#Dependencias
require 'picasso'
require 'ceaser-easing'
gem 'picasso', '~> 0.5.2'
gem 'ceaser-easing', '~> 0.7'

#Configuración general
module Shared

	#Estilo de los CSS
	@output_style = :expanded

	def self.output_style
		return @output_style
	end

	#Utilizar URLs relativas
	@relative_assets = true

	def self.relative_assets
		return @relative_assets
	end

	#Opciones de Sass
	@sass_options = { :cache => true, :debug_info => false }

	def self.sass_options
		return @sass_options
	end

	#Sin comentarios
	@line_comments = false

	def self.line_comments
		return @line_comments
	end

	#Precisión de decimales
	@precision = 5

	def self.precision
		return @precision
	end

	#Renombrado de sprites
	def self.on_sprite_saved(filename, product)
		if File.exists?(filename)
      		#FileUtils.mv filename, filename.gsub(%r{\w+-s[a-z0-9]{10}\.png$}) { |capture| product + '-' + capture.gsub(%r{-s[a-z0-9]{10}\.png}, '.png') }
      		FileUtils.mv filename, filename.gsub(%r{-s[a-z0-9]{10}\.png$}, '-sprite.png')
		end
	end

	#Reemplazo de nombres de sprites en el css
	def self.on_stylesheet_saved(filename, product)
		if File.exists?(filename)
			css = File.read filename
			File.open(filename, 'w+') do |f|
				#f << css.gsub(%r{\w+-s[a-z0-9]{10}\.png}) { |capture| product + '-' + capture.gsub(%r{-s[a-z0-9]{10}\.png}, '.png') }
				f << css.gsub(%r{-s[a-z0-9]{10}\.png}, '-sprite.png')
			end
		end
	end

end