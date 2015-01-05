(function(window,undefined){
	var Interface = function(interfaceName,methods){
		if(arguments.length!=2){
			throw new Error("invalid args count!!!!valid arguments:(interfaceName,methods[])");
		}
		this.name = interfaceName;
		this.methods = [];
		for (var i = 0,len = methods.length; i < len; i++) {
			this.methods.push(methods[i]);
		}
	}
	Interface.ensureImplements = function(obj){
		if(arguments.length<2){
			throw new Error("args length less than 2");
		}
		for (var i = 1,len = arguments.length; i < len; i++) {
			var interface = arguments[i];
			if(interface.constructor!==Interface){
				throw new Error("interface must be instance of Interface");
			}
			for (var j = 0 , methodCount = interface.methods.length; j < methodCount; j++) {
				var method = interface.methods[j];
				if(!obj[method] || typeof(obj[method])!='function'){
					throw new Error("obj does not implement the interface["+interface.name+"],method:"+method);
				}
			}
		}
	}
	window.Interface = Interface;
})(window);
