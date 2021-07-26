package org.zhongsoft.pendtasks;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.zhongsoft.pendtasks package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: org.zhongsoft.pendtasks
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link DeleteTasks }
	 * 
	 */
	public DeleteTasks createDeleteTasks() {
		return new DeleteTasks();
	}

	/**
	 * Create an instance of {@link DeleteTasksResponse }
	 * 
	 */
	public DeleteTasksResponse createDeleteTasksResponse() {
		return new DeleteTasksResponse();
	}

	/**
	 * Create an instance of {@link AddTasks }
	 * 
	 */
	public AddTasks createAddTasks() {
		return new AddTasks();
	}

	/**
	 * Create an instance of {@link AddTasksResponse }
	 * 
	 */
	public AddTasksResponse createAddTasksResponse() {
		return new AddTasksResponse();
	}

	/**
	 * Create an instance of {@link UpdateTasks }
	 * 
	 */
	public UpdateTasks createUpdateTasks() {
		return new UpdateTasks();
	}

	/**
	 * Create an instance of {@link UpdateTasksResponse }
	 * 
	 */
	public UpdateTasksResponse createUpdateTasksResponse() {
		return new UpdateTasksResponse();
	}

}
