package org.inno.vo;

import java.io.Serializable;

public class RequestObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4724790447966237332L;

	private int stringId;
	
	private String stringValue;

	public int getStringId() {
		return stringId;
	}

	public void setStringId(int stringId) {
		this.stringId = stringId;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
}
