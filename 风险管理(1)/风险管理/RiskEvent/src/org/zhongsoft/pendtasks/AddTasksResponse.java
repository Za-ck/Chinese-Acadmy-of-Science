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
 *         &lt;element name="AddTasksResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "", propOrder = { "addTasksResult", "errorMsg" })
@XmlRootElement(name = "AddTasksResponse")
public class AddTasksResponse {

	@XmlElement(name = "AddTasksResult")
	protected boolean addTasksResult;
	protected String errorMsg;

	/**
	 * Gets the value of the addTasksResult property.
	 * 
	 */
	public boolean isAddTasksResult() {
		return addTasksResult;
	}

	/**
	 * Sets the value of the addTasksResult property.
	 * 
	 */
	public void setAddTasksResult(boolean value) {
		this.addTasksResult = value;
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
