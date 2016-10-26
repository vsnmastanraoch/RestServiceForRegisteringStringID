package org.inno.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReturnObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1584831497641354012L;
	
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
