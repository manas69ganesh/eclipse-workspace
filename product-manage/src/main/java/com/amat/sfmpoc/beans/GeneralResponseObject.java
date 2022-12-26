package com.amat.sfmpoc.beans;

public class GeneralResponseObject {

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
