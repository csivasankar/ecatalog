package org.cisco.catalog.domain.json;


public class ErrorDto extends BaseDto {

	private JsonError error = new JsonError();

	public JsonError getError() {
		return error;
	}

	public void setError(JsonError error) {
		this.error = error;
	}

	public void setErrorMessage(String msg) {
		getError().setErrorDescription(msg);
	}
}
