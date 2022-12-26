package com.amat.sfmpoc.beans;

import java.io.Serializable;

public class GeneralResponseObject implements Serializable {

	private static final long serialVersionUID = 1091462469048733125L;

	private boolean result;
	private String message;
	private int messageCode;
	private Object data;

	public GeneralResponseObject() {
		super();
	}

	public GeneralResponseObject(boolean result, String message, int messageCode, Object data) {
		super();
		this.result = result;
		this.message = message;
		this.messageCode = messageCode;
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GeneralResponseObject [result=" + result + ", message=" + message + ", messageCode=" + messageCode
				+ ", data=" + data + "]";
	}

}
