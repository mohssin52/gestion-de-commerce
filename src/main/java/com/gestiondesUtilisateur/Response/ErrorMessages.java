package com.gestiondesUtilisateur.Response;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Missing required field."),
    RECORD_ALREADY_EXISTS("Record already exists."),
    INTERNAL_SERVER_ERROR("Internal  server error."),
    NO_RECORD_FOUND("Record with provided id is not found."),
   CHAMP_VIDE("ce champ ne doit etre vide "),
	PRODUCT_VIDE("user n'avoir pas des produit");
	private  String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
