
<div class="game-container">
	<div id="top_bar" class="top_bar">
		<ul class="score main-game hide">
			<li class="date"><span class="actual-date">00/00/0000</span> - D&iacute;a <span class="actual-day">1</span></li>
			<li class="city">Ciudad de Buenos Aires</li>
			<li class="money-container">$<span class="money"></span></li>
		</ul>
		<span class="title index">&iquest;En qu&eacute; lugar de Argentina est&aacute; Carmen?</span>
		<div class="social">
			<span title="Facebook" class="mi-despegar-sprite-facebook bit-icon"></span>
			<span title="Twitter" class="mi-despegar-sprite-twitter bit-icon"></span>
			<span title="Google+" class="mi-despegar-sprite-google bit-icon"></span>
		</div>
	</div>
	<div class="mi-despegar-sprite-game_bg">
        <div class="row-fluid show-grid">
        	<div class="span4 well white-well left-panel">
        		<div class="actual-location">
        			<span class="mi-despegar-sprite-pointer left"></span><span class="title">Argentina</span>
        		</div>
        		<div class="printer-text index">
        				
					despegar.com -<br /> Tu misi&oacute;n es, en teor&iacute;a, bastante sencilla. Una infinidad de Bancos han sido robados en varias ciudades de Argentina por una infame ladrona. Ten&eacute;s que llegar hasta su escondite y arrestarla antes del ${dateLimit}hs, o antes que se agote tu dinero, siguiendo las pistas que ella ha dejado por el camino. Deber&aacute;s viajar entre ciudades y usar tu olfato de detective para no perder el rastro. Buena suerte!

					<span class="mi-despegar-sprite-carmen carmen-picture"></span>

        		</div>
        		<span class="mi-despegar-sprite-printer printer index"></span>
        		<div id="map" class="map main-game hide"></div>
        	</div>
        	<div id="main-panel" class="span7 well white-well left-panel block-popup-container">
        		<div class="span10 well white-well form-player">
        			<form id="form-player" class="index">
						<div class="span3 white-well bit-text blue">
							<label class="control-label" for="name">Nombre:</label>
							<label class="control-label" for="email">Email:</label>
						</div>

						<div class="span8  white-well">
							<input class="span12 bit-text" type="text" id="name" required="true">
							<input class="span12 bit-text" type="text" id="email" required="true">
						</div>
						<div class="span12 white-well avatar-container">
							<div class="span5 white-well">
								<span class="mi-despegar-sprite-male-avatar avatar avatar-selected" data-full-genre="male" data-genre="m"></span>
								<span class="bit-text blue">Henry</span>
							</div>
							<div class="span5 white-well">
								<span class="mi-despegar-sprite-female-avatar avatar" data-full-genre="female" data-genre="f"></span>
								<span class="bit-text blue">Cloudia</span>
							</div>
						</div>
						<div class="span12 white-well"><button type="submit" class="btn btn-info span11 play">Jugar</button></div>
					</form>
					<div class="span12 white-well main-game hide clue-container">
						<div class="span11 well">
							<span class="mi-despegar-sprite-male-avatar avatar avatar-selected clue-avatar"></span>
							<span class="bit-text clue-description">Ops! no tengo pistas para t&iacute; a&uacute;n.</span>
						</div>
						<span class="mi-despegar-sprite-male-avatar avatar avatar-selected ask-avatar"></span>
						<button type="submit" class="btn btn-info span11 clue-return">Volver al mapa</button>
					</div>
					<div class="span12 white-well hide flight-container">
						
					</div>
        		</div>
        </div>
        <div class="row-fluid show-grid">
        	<div class="span4 well white-well footer-panel bit-text footer-label"><span class="blue">despegar</span><span class="red">.com</span></div>
        	<div class="span7 well white-well footer-panel bit-text footer-label action-menu">
        		<span class="blue index">Para comenzar completa el formulario y selecciona un detective.</span>
        		<span class="blue main-game hide">Seg&uacute;n nuestras fuentes tenemos fuertes indicios de que Carmen puede encontrarse en <span class="toTravel red">...</span>.</span>
        	</div>
        </div>
    </div>
</div>


<#-- Initialization scripts using RequireJS -->	
<@addRequireJsContent>
<script type="text/javascript">

	define('options', function() {
		return {
			pageInit: function(localization, extraData) { ${onReadyEvent} }
		};
	});
	
</script>
</@addRequireJsContent>

<#-- Contenido extra en el body -->	
<@addExtraBodyContent>
</@addExtraBodyContent>