package com.restapi.assignment.trackinghistory.response;

/* Return the response when adding new value
 * The response is previous input 
 */

public class AddValueResponse {

	private String output;

	public AddValueResponse() {
	
	}
	
	public AddValueResponse(String output) {
		super();
		this.output = output;
	}

	// Getter
	public String getOutput() {
		return output;
	}

	//Setter
	public void setOutput(String output) {
		this.output = output;
	}

}
