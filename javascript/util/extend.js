/**
 * 案例
 * function Person(age){
 *	this.age = age;
 * }
 * Person.prototype.getAge = function(){
 *	return this.age;
 * }
 * function Baby(name,age){
 *	Baby.super.constructor.call(this,age);
 *	this.name = name;
 *	this.getAge = function(){
 *		return Baby.super.getAge.call(this);
 *	}
 * }
 * 
 * Baby.extend(Person);
 * var baby = new Baby('xiaodiao',1);
 * alert(baby.getAge());
 * 
 * var person = clone({
 * 	name:'',
 * 	age:1,
 * 	getName:function(){
 *		return this.name;
 *	},
 * 	getAge:function(){
 * 		return this.age;
 *	}
 * });
 * alert(person.getAge());
 **/
(function(window,undefined){
	Function.prototype.extend = function(superClass){
		var F = function(){};
		F.prototype = superClass.prototype;
		this.prototype = new F();
		this.prototype.constructor = this;

		this.super = superClass.prototype;
		if(superClass.prototype.constructor == Object.prototype.constructor){
			superClass.prototype.constructor = superClass;
		}
	}
	window.clone = function(obj){
		var F = function(){}
		F.prototype = obj;
		return  new F();
	}
})(window);
