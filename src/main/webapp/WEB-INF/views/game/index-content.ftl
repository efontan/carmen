
<div class="game-container">
	<div class="top_bar">
		<ul class="score">
			<li class="date">xx/xx/xxxx - D&iacute;a 1</li>
			<li class="city">lalala lalal lalal lalalal lalalal lalala</li>
			<li class="money">$00.000</li>
		</ul>
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
        		<!--<div class="printer-text">
        				
					despegar.com - tu misi&oacute;n es bastante simple, lo que requieres hacer es bla bla bla bla bla y fundamentalmente encontrar a bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, puedes utilizar bla bla bla y bla bla bla, lo que requieres hacer es bla bla bla bla bla y fundamentalmente encontrar a bla bla bla, lo que requieres hacer es bla bla bla bla bla y fundamentalmente encontrar a bla bla bla, lo que requieres hacer es bla bla bla bla bla y fundamentalmente encontrar a bla bla bla.

					<span class="mi-despegar-sprite-carmen carmen-picture"></span>

        		</div>-->
        		<span class="mi-despegar-sprite-printer printer"></span>
        		<!--<div id="map" class="map"></div>-->
        	</div>
        	<div id="main-panel" class="span7 well white-well left-panel block-popup-container">
        		<div class="span10 well white-well form-player">
        			<form class="form-horizontal">
						<div class="span3 well">
							<label class="control-label" for="inputEmail">Nombre:</label>
							<label class="control-label" for="inputEmail">Email:</label>

						</div>

						<div class="span8 well">
							<input class="span12" type="text" id="name" placeholder="Nombre">
							<input class="span12" type="text" id="email" placeholder="Email">
						</div>
						<div class="span11 well">
							<span class="mi-despegar-sprite-male-avatar avatar" data-genre="male"></span>
							<span class="mi-despegar-sprite-female-avatar avatar" data-genre="female"></span>
						</div>


						<button type="submit" class="btn btn-info">Jugar</button>
					</form>
        		</div>
        </div>
        <div class="row-fluid show-grid">
        	<div class="span4 well white-well footer-panel bit-text footer-label"><span class="blue">despegar</span><span class="red">.com</span></div>
        	<div class="span7 well white-well footer-panel bit-text footer-label action-menu"></div>
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
	${newRelic.footer}
</@addExtraBodyContent>