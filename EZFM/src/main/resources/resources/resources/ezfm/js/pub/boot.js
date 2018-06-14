var _uxBasePath = 'resources/shareworx/ux';

+function($){
	if($.requestFn) throw('conflict compnent $.request');
	var requestFn = function(){};
	
	$.extend(requestFn.prototype, {
		type: 'POST',
		contentType: 'application/json;charset=utf-8',
		dataType: 'json',
		success: function(){},
		fail: function(){},
		msg404: '400: 请检测待提交的数据格式是否正确.',
		
		ajax: function(type, operations){
			var me = this;
			if(!operations.url) throw('url must exist.');
			var argopers = {
				type: type,
				contentType: this.contentType,
				dataType: this.dataType,
				success: function(result){
					console.info(result);
					if(result && result.success){
						me.success(result);
					}else{
						//console.error('请求失败', (result.message || '未知错误'));
						if(me.failure){
							me.failure(result);
						}else{
							if(result.message){
								throw(result.message);
							}
							console.error(result.message || '未知错误');
						}
					}
				},
				error: function(request, statusText, errorThrown){
					var me = this;
					//console.warn(arguments);
					var statusCode = request.status;
					switch(statusCode){
						case 200:
							var result = eval("(" + request.responseText + ")");
							if(me.failure){
								me.failure(result);
							}else{
								if(result.message){
									throw(result.message);
								}
								console.error(result.message || '未知错误');
							}
						break;
						case 400:
							console.error(me.msg404);
						break;
						case 404:
							console.error('404: 页面未找到，请检测请求地址是否正确');
						break;
						case 415:
							console.error('415: 媒体格式错误，请检测请求配置，如: contentType = application/json');
						break;
						case 500:
							console.error('415: 服务端处理错误', statusText);
						break;
						default:
							console.error('未知错误：', statusText, errorThrown);
					}
					//me.fail(request, statusText, errorThrown);
				}
			};
			$.extend(argopers, operations);
			$.ajax(argopers);
		},
		/**
		 * HTTP POST请求
		 */
		httpPost: function(operations){
			operations.contentType = 'application/x-www-form-urlencoded;charset=utf-8';
			operations.type = 'POST';
			this.ajax('POST', operations);
		},
		/**
		 * HTTP GET请求
		 */
		httpGet: function(operations){
			operations.contentType = 'application/x-www-form-urlencoded;charset=utf-8';
			operations.type = 'GET';
			this.ajax('GET', operations);
		},
		/**
		 * HTTP POST 文件上传请求
		 */
		httpUpload: function(form, operations){
//			operations.contentType = 'multipart/form-data';
//			operations.type = 'POST';
//			this.ajax(operations);
			throw('Unsupport now!');
		},
		rest: function(type, operations){
			if(operations.data) operations.data=JSON.stringify(operations.data);
			operations.contentType = 'application/json;charset=utf-8';
			operations.type = type;
			this.ajax(type, operations);
		},
		/**
		 * RESTFul POST请求
		 */
		restPost: function(operations) {
			this.rest('POST', operations);
		},
		restPut: function(operations) {
			this.rest('PUT', operations);
		},
		restDelete: function(operations){
			this.rest('DELETE', operations);
		}
	});
	
	$.request = new requestFn();
	
	$.extend($.fn, {
		serializeJson: function(){
			var array = $(this).serializeArray();
			var json = {};
			for(var i in array){
				var key = array[i].name;
				var val = array[i].value;
				if(json[key]){
					if($.isArray(json[key])){
						json[key].push(val);
					}else{
						json[key] = [json[key], val];
					}
				}else{
					json[key] = val;
				}
			}
			return json;
		}
	});
}(jQuery);

+function($){
	var Assert = function(){}
	$.extend(Assert.prototype, {
		doCallback: function(callback, message){
			if(callback) {
				callback.call();
			}else{
				if(message){
					throw(message);
				}
				console.error(message || 'assert error.');
				//throw(message || 'assert error.');
			}
		},
		existInObject: function(object, key, message, callback){
			if(!object || !object[key]){
				this.doCallback(callback, message || 'the property from object is required.');
			}
		},
		isArrayEmpty: function(array, callback){
			if(!array || !array.length){
				this.doCallback(callback, 'array is empty.');
			}
		},
		isObjectEmpty: function(object, callback){
			if(!object || !$.isPlainObject(object)){
				this.doCallback(callback, 'object is empty');
			}
		}
	});
	
	$.assert = new Assert();
}(jQuery);

+function($){
	var loadViewObject = function(viewObject) {
		var id = viewObject.id;
		$.assert.existInObject(viewObject, 'id', 'id is required in view object.');
		
	}
	var loadViews = function(views) {
		if(!views) return;
		if($.isPlainObject(views)){
			var json = {};
			json[views.id] = loadViewObject(views);
			return json;
		}else{
			var obj = {};
			for(var i in views){
				obj[views[i].id] = loadViewObject(views[i]);
			}
			return obj;
		}
	}
	
	var Field = function(options){
		if(typeof options === 'string'){
			this.id = options;
		}else{
			$.assert.existInObject(options, 'id', 'id is required in field.');
			$.extend(this, options || {});
		}
	}
	$.extend(Field, {
		TYPE_STRING: 'string',
		TYPE_INTEGER: 'int',
		TYPE_DECIMAL: 'decimal',
		TYPE_BOOLEAN: 'boolean',
		TYPE_ENUM: 'enum',
		TYPE_REFERENCE: 'reference'
	});
	
	$.extend(Field.prototype, {
		getType: function(){ return this.type || Field.TYPE_STRING; },
		getId: function(){ return this.id; },
		isPrimary: function(){ return (this.primary ? true : false); },
		primary: function() { this.primary = true },
		getSuffix: function(){ return this.suffix; },
		getPrefix: function(){ return this.prefix; },
		getSubmitId: function(){ 
			var submitField = this.getId();
			if(this.getPrefix()) submitField = this.getPrefix() + "." + submitField;
			if(this.getSuffix()) submitField += "." + this.getSuffix();
			return submitField;
		}
	});
	
	var Model = function(options){
		this._source = options,
			this.primaryField = null,
			this._fields = {},
			this._values = {},
			this._raws = {},
			this._submitValues = {};
		this.init(options);
	}
	
	$.extend(Model.prototype, {
		init: function(options){
			console.info('初始化model');
			if(options){
				$.extend(this._source, options);
				$.extend(this, options);
				if(options.fields && options.fields.length){
					for(var i in options.fields){
						this.addField(options.fields[i]);
					}
				}
				if(options.data){
					this.setValues(options.data);
				}
				if(options.idProperty){
					var field = this.getField(options.idProperty);
					if(field == null) throw('unknow field ' + options.idProperty);
					field.primary();
					this.primaryField = field;
				}
			}
		},
		addField: function(sfield){
			var field = (typeof sfield === Field) ? sfield : new Field(sfield);
//			console.info((typeof sfield === Field), field);
			this._fields[field.getId()] = field;
			this._values[field.getId()] = null;
			this._submitValues[this.getSubmitKey(field.getId())] = null;
			if(sfield.isPrimary){
				this.primaryField = sfield;
			}
		},
		getField: function(key){
			return this._fields[key];
		},
		getSubmitKey: function(key){
			var field = this.getField(key);
			if(!field) return null;
			var submitField = field.getId();
			if(field.getPrefix()) submitField = field.getPrefix() + "." + submitField;
			if(field.getSuffix()) submitField += "." + field.getSuffix();
			return submitField;
		},
		removeField: function(key) {
			var field = this.getField(key);
			if(field){
				delete this._values[key];
				delete this._submitValue[key];
				delete this._fields[key];
			}
		},
		getFields: function(){
			return this._fields;
		},
		setValues: function(values){
			if(!values) return;
			var data = values;
			if($.isArray(values)){
				data = values[0];
			}
			for(var i in data){
				this.setValue(i, data[i]);
			}
		},
		setValue: function(key, value){
			 this._raws[key] = value;
			 var field = this._fields[key];
			 if(field){
			 	var type = field.getType();
			 	var val = value;
			 	if(type == 'int'){
			 		val = parseInt(value);
			 	}else if(type == 'decimal'){
			 		val = parseFloat(value);
			 	}else if(type == 'boolean'){
			 		val = ((value) ? true : false);
			 	}
			 	var oldVal = this._values[key];
			 	this._values[key] = val;
			 	this._submitValues[this.getSubmitKey(key)] = val;
			 	this.onChange(key, val, oldVal);
			 }
		},
		getValue: function(key){ return this._values[key]; },
		getRawValue: function(key) {return this._raws[key]; },
		getValues: function(){ return this._values; },
		getSubmitValues: function(){ return this._submitValues; },
		eachField: function(callback){
			if(!this._fields) return;
			for(var i in this._fields){
				callback.call(callback, this._fields[i]);
			}
		},
		onChange: function(key, newVal, oldVal){}
	});
	
	$.model = Model;
	
	var Form = function(options){
		this._source = {},
			this._fields = {};
		this.init(options);
	}
	
	$.extend(Form.prototype, {
		init: function(options){
			if(!options) return;
			$.extend(this._source, options);
			$.extend(this, options);
			if(options.id){
				this._view = $('#' + options.id);
			}
			// init form model
			if(options.model) {
//				if(typeof options.model === Model){
//					this._model = options.model;
//				}else{
//					this._model = new Model(options.model);
//				}
				this.loadModel(options.model);
				if(options.autoWidgetsByModel){
					this.registModelWidget();
				}
			}
			// load form widgets
			if(options.widgets){
				if($.isArray(options.widgets)){
					this.registFields(options.widgets);
				}else{
					this.registField(widget);
				}
			}
		},
		loadModel: function(model){
			if(typeof model === Model){
				this._model = model;
			}else{
				this._model = new Model(model);
			}
			if(model.data){
				setValues(model.getValues());
			}
		},
		getModel: function(){
			return this._model;
		},
		registModelWidget: function(){
			var me = this,
				model = me.getModel();
			if(!model) throw('model is required.');
			model.eachField(function(field){
				var type = field.getType() || 'string',
					fieldOption = {
						id: field.getId(),
						name: field.getId()
					};
				switch(type){
					case Field.TYPE_STRING: 
						$.extend(fieldOption, {
							xtype: 'textbox'
						});
						break;
					case Field.TYPE_INTEGER:
						$.extend(fieldOption, {
							xtype: 'numberbox'
						});
						break;
					case Field.TYPE_DECIMAL:
						$.extend(fieldOption, {
							xtype: 'numberbox'
						});
						break;
					case Field.TYPE_BOOLEAN:
						$.extend(fieldOption, {
							xtype: 'combobox'
						});
						break;
					case Field.TYPE_ENUM: 
						$.extend(fieldOption, {
							xtype: 'combobox'
						});
						break;
					case Field.TYPE_REFERENCE:
						$.extend(fieldOption, {
							xtype: 'textbox'
						});
						break;
					default:
						throw('unsupport field type: ' + type);
				}
				me.registField(fieldOption);
			});
		},
		getView: function(){
			return this._view;
		},
		registFields: function(options){
			if(options && options.length){
				for(var i in options){
					this.registField(options[i]);
				}
			}
		},
		registField: function(options){
			var me = this;
			var key = options.name;
			if(!key){
				throw('name is required.');
			}
			if(!options.id) throw("id is required.");
			var widgetField = $('#' + options.id);
			if(!widgetField || widgetField.size() == 0){
				widgetField = $('#' + this.id + ' [name=' + options.name + ']');
//				if(widgetField && widgetField.size > 0 && options.id){
//					widgetField.attr('id', options.id);
//					console.warn('set id ok.');
//				}
			}
			if(!widgetField || widgetField.size() == 0){
				console.warn('fail regist widget: ', options);
				return;
			}
//			console.info('regist', widgetField, widgetField.size());
			options.xtype = options.xtype || 'textbox';
			
			widgetField.xtype = options.xtype;
			
			options.widgetConfig = options.widgetConfig || {};
			var changeFn = options.widgetConfig.onChange;
			$.extend(options.widgetConfig, {
				onChange: function(newVal, oldVal){
//					console.info('change', arguments);
					if(me._model){
						me._model.setValue(key, newVal);
					}
					if(changeFn){
						changeFn.apply(changeFn, arguments);
					}
				}
			});
			widgetField[options.xtype].call(widgetField, options.formConfig);
			me._fields[key] = widgetField;
		},
		getField: function(id){
			return this._fields[id];
		},
		getFields: function(){
			var fields = [];
			if(this._fields){
				for(var key in this._fields){
					fields.push(this._fields[key]);
				}
			}
			return fields;
		},
		eachField: function(callback){
			if(this._fields){
				for(var key in this._fields){
					callback.call(callback, this._fields[key]);
				}
			}
		},
		setValues: function(values){
			if(values){
				for(var key in values){
					this.setValue(key, values[key]);
				}
			}
		},
		setValue: function(key, value){
			var field = this.getField(key);
			if(!field) {
				if(this._model){
					this._model.setValue(key, value);
				}
				return;
			}
			var obj = {};
			obj[key] = value;
			$('#form').form('load', obj);
		},
		getValues: function(){
			if(this._model){
				return this._model.getValues();
			}
			var values = {};
			for(var i in this._fields){
				values[i] = this._fields[i].val();
			}
			return values;
		},
		getFormValues: function(){
			if(this._model) return this._model.getSubmitValues();
			var values = {};
			var fields = this._fields;
			if(fields && fields.length){
				for(var key in fields){
					values[key] = fields[key].val();
				}
			}
			return values;
		}
	});
	
	var mvc = function(options){
		this._options = options;
//		this._views = loadViews(options) || {},
		this._models = {},
		this._stores = {},
		this._actions = {};
	}
	
	$.extend($, {
		field: function(options){ return new Field(options) },
		model: function(options){ return new Model(options) },
		widgetForm: function(options) { return new Form(options); },
		mvc: function(options){ return new mvc(optoins) }
	});
}(jQuery);