package starter.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.json.simple.parser.ParseException;
import starter.actions.PetStoreActions;
import starter.utils.DataMgm;

import static org.junit.Assert.assertEquals;

public class PetStoreStepDefinitions {
    @Steps
    PetStoreActions petStoreActions;

    DataMgm dataMgm;

    int statusResponse;

    @Given("setup is done")
    public void setupIsDone() {
        petStoreActions.setup();
    }

    @When("search for {string} pets in the store")
    public void searchForPetsInTheStore(String status) {
        statusResponse = petStoreActions.getPetsByStatus(status);
    }

    @Then("list of available pets is returned")
    public void listOfAvailablePetsIsReturned() {
        assertEquals(200,statusResponse);
    }

    @When("^post new available pet in the store$")
    public void postNewAvailablePetInTheStore() throws ParseException {
        petStoreActions.postNewAvailablePet(dataMgm.getJsonFromFile());
    }

    @Then("^new pet is added$")
    public void newPetIsAdded() throws ParseException {
        petStoreActions.checkPetExists(true);
    }

    @When("update new pet status to {string}")
    public void updateNewPetStatusTo(String status) throws ParseException {
        petStoreActions.updatePetStatus(status);
    }

    @Then("new pet status is updated to {string}")
    public void newPetStatusIsUpdatedTo(String status) throws ParseException {
        petStoreActions.checkPetUpdated(status);

    }

    @When("delete pet added")
    public void deletePetAdded() throws ParseException {
        petStoreActions.deletePet();
    }

    @Then("pet is removed from store")
    public void petIsRemovedFromStore() throws ParseException {
        petStoreActions.checkPetExists(false);
    }
}
