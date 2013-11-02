define(["amplify", "common/modules/logger"], 
	function (amplify, logger) {

		// ID del modulo
		var MODULE_ID = "ServiceManage";

		var serviceMap = [];



		//function getMocksFor(serviceID){}

		function addRequest(requestDefinition)
			{

				logger.info("[INFO] Register serviceID:"+requestDefinition.serviceID);
				function _newRequest()
					{
					
					
					var service_definition	=	{
							url: function(){return _resolveURL(requestDefinition.serviceID);}(),
							dataType:requestDefinition.params.dataType,
							type: requestDefinition.params.type
						};
						
						// data Mapper 
						if (!_.isNull( requestDefinition.params.dataMap)) {
							service_definition.dataMap = requestDefinition.params.dataMap;
						}
						
						if (!_.isNull( requestDefinition.params.contentType)) {
							service_definition.contentType = requestDefinition.params.contentType;
						}
						
						if (!_.isNull( requestDefinition.params.cache)) {
							service_definition.cache = requestDefinition.params.cache;
						}
						
						// obtener datos de la reserva
						amplify.request.define( 
								requestDefinition.serviceID, 
								requestDefinition.type,
								service_definition
						);
						//logger.info(requestDefinition);
					}

				serviceMap[requestDefinition.serviceID] = requestDefinition;
				_newRequest();
			}

		function _resolveURL(serviceID)
			{

				var out = "";

				if( serviceID in serviceMap )
					{
						if (serviceMap[serviceID].mocked  )
							out = serviceMap[serviceID].mocks[serviceMap[serviceID].currentMock];
						else 
							out = serviceMap[serviceID].params.url; 
					}
				//	else
				//		logger.info("[INFO]" + " (" + MODULE_ID + ") ServiceID: "+serviceID+" not registered");
				return out;
			}

	function _subscribeMocker()
		{
			amplify.subscribe("mock_Service", function (data){
				if(data.serviceID in serviceMap) 
					{
						handleMockerEvent(data);
					}
				else
					{logger.info("[INFO] Mocker ServiceID: "+data.serviceID+"::"+data.mockID+" not registered");}
			});
		}

	function _handleMockerEvent(data)
		{
			if ( data.mockID in undefined )
				{
					if(data.mockID in serviceMap[serviceID].mocks) 
						{
							serviceMap[data.serviceID].current = serviceMap[data.serviceID].mocks[data.mockID];
							serviceMap[data.serviceID].mocked  = true;
						}
					else
						{logger.info("[INFO] Mocker ServiceID: "+data.serviceID+" not registered");}
				}
			else
				{
					serviceMap[data.serviceID].mocked  = false;
				}
		}

	/**
	 * Inicializador
	 */
	function init() {
		logger.info("[INFO] Inicializando (" + MODULE_ID + ")");
		subscribeMocker();
	}

	// API Publica
	return {
		init : init,
	addRequest : addRequest
	};

});