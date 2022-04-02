package resources;

public enum APIEndPointsEnum {
	
	ADD_PLACE_API("/maps/api/place/add/json"),
	GET_PLACE_API("/maps/api/place/get/json"),
	UPDATE_PLACE_API("/maps/api/place/update/json"),
	DELETE_PLACE_API("/maps/api/place/delete/json");
	
	
	private String apiEndPointAttribute;

	APIEndPointsEnum(String resources) {
		this.apiEndPointAttribute = resources;
	}
	
	public String getValue() {
		return apiEndPointAttribute;
	}
	
	

}
