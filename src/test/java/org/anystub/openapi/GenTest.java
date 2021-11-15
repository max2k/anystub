package org.anystub.openapi;

import org.anystub.AnyStubId;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.PetApi;
import org.openapitools.client.api.PetApiTest;
import org.openapitools.client.model.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenTest {

    @Test
    @AnyStubId
    void requestGen() throws ApiException {
        PetApi petApi = new PetApi();

        PetApiTest petApiTest = new PetApiTest(petApi);

        Pet petById = petApiTest.getPetById(123L);


        assertEquals(1L, petById.getId());
    }
}
