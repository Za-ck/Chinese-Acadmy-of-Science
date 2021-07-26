package org.zhongsoft.pendtasks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeleteTasksResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "deleteTasksResult", "errorMsg" })
@XmlRootElement(name = "DeleteTasksResponse")
public class DeleteTasksResponse {

	@XmlElement(name = "DeleteTasksResult")
	protected boolean deleteTasksResult;
	protected String errorMsg;

	/**
	 * Gets the value of the deleteTasksResult property.
	 * 
	 */
	public boolean isDeleteTasksResult() {
		return deleteTasksResult;
	}

	/**
	 * Sets the value of the deleteTasksResult property.
	 * 
	 */
	public void setDeleteTasksResult(boolean value) {
		this.deleteTasksResult = value;
	}

	/**
	 * Gets the value of the errorMsg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the value of the errorMsg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setErrorMsg(String value) {
		this.errorMsg = value;
	}

}
