package org.anystub.openapi;

import com.fasterxml.jackson.core.type.TypeReference;
import org.anystub.AnyStubId;
import org.anystub.RequestMode;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.PetApi;
import org.openapitools.client.api.PetApiTest;
import org.openapitools.client.model.Pet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenTest {

    @Test
    @AnyStubId(requestMode = RequestMode.rmFake)
    void requestGen() throws ApiException {
        PetApi petApi = new PetApi();

        PetApiTest petApiTest = new PetApiTest(petApi);

        Pet petById = petApiTest.getPetById(123L);


        assertEquals(1L, petById.getId(), );
    }

    @Test
    void typeTest() {
        TypeReference<String> rt = new TypeReference<>() {
        };

        assertEquals("", rt.getType().getTypeName());
    }
}
