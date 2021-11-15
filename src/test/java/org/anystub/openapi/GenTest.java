package org.anystub.openapi;

import com.fasterxml.jackson.core.type.TypeReference;
import org.anystub.AnyStubId;
import org.anystub.RequestMode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.PetApi;
import org.openapitools.client.api.PetApiTest;
import org.openapitools.client.model.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenTest {

    @Test
    @AnyStubId(requestMode = RequestMode.rmFake)
    void requestGen() throws ApiException {
        PetApi petApi = new PetApi();

        PetApiTest petApiTest = new PetApiTest(petApi);

        Pet petById = petApiTest.getPetById(1233L);


        assertEquals(-592218937L, petById.getId());
    }

    @Test
    @AnyStubId(requestMode = RequestMode.rmPassThrough)
    @Disabled("sometime the API gives correct response")
    void requestException() throws ApiException {
        PetApi petApi = new PetApi();

        PetApiTest petApiTest = new PetApiTest(petApi);


        assertThrows(Exception.class, () -> {
            Pet petById = petApiTest.getPetById(123L);
            System.out.println(petById.toString());
        });


    }


}
