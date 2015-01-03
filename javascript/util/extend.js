$(function(window,undefined){
	Function.prototype.extend = function(superClass){
		var F = function(){};
		F.prototype = superClass.prototype;
		this.prototype = new F();
		this.prototype.constructor = this;

		this.super = superClass.prototype;
	}
})(window);
