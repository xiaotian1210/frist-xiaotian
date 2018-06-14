package com.shareworx.platform.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shareworx.platform.util.ModelUtils;
import com.shareworx.platform.util.ShareworxAssert;
import com.shareworx.platform.util.StringUtils;

public class ObjectModel extends HashMap<String, Object> implements IJsonModel, IObjectModel, IObjectExtModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2621783499405105404L;
	
	private boolean readOnly = false;
	
	public ObjectModel() {
		super();
	}

	public ObjectModel(Map<? extends String, ? extends Object> m) {
		super(m);
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectModel#getAttributes()
	 */
	@Override
	public Set<String> getAttributes() {
		return keySet();
	}

	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectModel#getAttribute(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key){
		return (T) get(key);
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectExtModel#getStringAttribute(java.lang.String)
	 */
	@Override
	public String getStringAttribute(String key) {
		Object obj = get(key);
		if(obj != null){
			return obj.toString();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectExtModel#getIntegerAttribute(java.lang.String)
	 */
	@Override
	public Integer getIntegerAttribute(String key) {
		Object obj = get(key);
		if(obj == null){
			return 0;
		}
		if(obj instanceof String) {
			return StringUtils.toInteger(obj.toString());
		}
		return (Integer) obj;
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectExtModel#getDecimalAttribute(java.lang.String)
	 */
	@Override
	public BigDecimal getDecimalAttribute(String key) {
		Object obj = get(key);
		if(obj == null){
			return null;
		}
		return StringUtils.toDecimal(obj.toString());
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectModel#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String key, Object value) {
		put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectModel#getArrayAttribute(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends IObjectModel> List<T> getArrayAttribute(String key) {
		return (List<T>) get(key);
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectModel#setArrayAttribute(java.lang.String, java.util.List)
	 */
	@Override
	public <T> void setArrayAttribute(String key, List<T> value){
		put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see com.shareworx.platform.model.IObjectModel#addArrayAttribute(java.lang.String, T)
	 */
	@Override
	public <T extends IObjectModel> void addArrayAttribute(String key, T value){
		ShareworxAssert.doAssert(isReadOnly(), "current model is readonly");
		if(!containsKey(key) || get(key) == null){
			setArrayAttribute(key, new ArrayList<T>());
		}
		getArrayAttribute(key).add(value);
	}

	@Override
	public String toJSONString() {
		return ModelUtils.toJsonString(this);
	}
	
	@Override
	public Object put(String key, Object value) {
		ShareworxAssert.doAssert(isReadOnly(), "current model is readonly");
		return super.put(key, value);
	}
	
	public void readOnly(boolean isReadOnly) {
		this.readOnly = isReadOnly;
	}
	
	public boolean isReadOnly() {
		return this.readOnly;
	}
}
