package com.shareworx.platform.exception;

@SuppressWarnings("serial")
public class FieldValidRuntimeException extends ShareworxRuntimeException {

	private int row = 0;
	
	private String fieldName;
	
	private String columnName;
	
	private Object fieldValue;
	
	private String message;
	
	public FieldValidRuntimeException(String fieldName, String columnName, Object fieldValue, String message) {
		super(message);
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.fieldValue = fieldValue;
		this.message = message;
	}
	
	public FieldValidRuntimeException(int row, String fieldName, String columnName, Object fieldValue, String message) {
		super(message);
		this.row = row;
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.fieldValue = fieldValue;
		this.message = message;
	}
	
	
	public FieldValidRuntimeException() {
		super();
	}

	public FieldValidRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldValidRuntimeException(String message) {
		super(message);
	}

	public FieldValidRuntimeException(Throwable cause) {
		super(cause);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
