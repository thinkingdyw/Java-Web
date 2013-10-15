/**
 * 实现简单的动态代理操作
 */
(function(window,undefined){

	Object.prototype.extend = function(target){
		var target_proto_ = target.prototype;
		
		for(k in target_proto_){
			this[k] = target_proto_.k;
		}
		for(k in target){
			this[k] = target.k;
		}
	};
	var Proxy = function(){
	
	}
	/**
	*@target 代理的目标对象
	*@method 要代理的方法：方法名
	*@callback 执行目标方法之前执行的回调函数，返回值为：true/false
	*/
	Proxy.get = function(target,method,callback){
		var proxy = {};
		var _proxyMethod = undefined;
		if(target[method] && typeof(target[method]) == 'function'){
			_proxyMethod = target[method];
			proxy.extend(target);
			proxy[method] = function(){
				var rs = callback();
				if(rs){
					return _proxyMethod();
				}
				return null;
			}
			return proxy;
		}else{
			throw new TypeError('不存在该方法!');
		}
	}
	window.Proxy = Proxy;
})(window);
