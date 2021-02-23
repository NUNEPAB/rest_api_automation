package starter.actions;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import starter.utils.DataMgm;
import starter.utils.Pet;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class PetStoreActions {

    DataMgm dataMgm;

    public void setup(){
        baseURI ="https://petstore.swagger.io/v2";
    }

    public int getPetsByStatus(String status){

        RequestSpecification request = given();

        Response response = request.queryParam("status",status)
                .get("/pet/findByStatus");

        String jsonString = response.jsonPath().prettify();
        Gson gson = new Gson();
        Pet[] pets= gson.fromJson(jsonString, Pet[].class);

        return response.getStatusCode();
    }

    public void postNewAvailablePet(JSONObject json) throws ParseException {

        Response response = given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(json.toJSONString()).
                when().
                post("https://petstore.swagger.io/v2/pet/");

        assertEquals(response.getStatusCode(), 200);

    }

    public void updatePetStatus(String petStatus) throws ParseException {
        JSONObject request = DataMgm.getJsonFromFile();

        request.replace("status", petStatus);

        Response updateResponse = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type","application/json").
                body(request.toJSONString()).
                when().
                post("/pet");

        assertEquals(200,updateResponse.getStatusCode());
    }

    public void deletePet() throws ParseException {
        JSONObject json = DataMgm.getJsonFromFile();
        RequestSpecification deleteRequest = RestAssured.given().header("api_key", "special-key");
        Response deleteResponse = deleteRequest.delete("/pet/"+ json.get("id"));

        assertEquals(deleteResponse.getStatusCode(),200);
    }

    public void checkPetExists(boolean exists) throws ParseException {
        JSONObject json = DataMgm.getJsonFromFile();
        RequestSpecification getRequest = RestAssured.given();
        Response deleteResponse = getRequest.get("/pet/"+ json.get("id"));

        if (exists) {
            assertEquals(200,deleteResponse.getStatusCode());
        } else{
            assertEquals(404,deleteResponse.getStatusCode());
        }


    }

    public void checkPetUpdated(String status) throws ParseException {
        JSONObject json = DataMgm.getJsonFromFile();
        RequestSpecification getRequest = RestAssured.given();
        Response getResponse = getRequest.get("/pet/"+ json.get("id"));

        assertEquals(200,getResponse.getStatusCode());
        assertEquals(status,getResponse.jsonPath().get("status"));

    }

}
