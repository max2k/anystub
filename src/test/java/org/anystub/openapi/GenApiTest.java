package org.anystub.openapi;

import org.anystub.AnyStubId;
import org.anystub.Base;
import org.anystub.RequestMode;
import org.anystub.mgmt.BaseManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenApiTest {


    @Test
    void testAccessDoc() {
        Base locate = BaseManagerFactory.locate("xApiCalls.yml");
        locate.constrain(RequestMode.rmNone);
        String value = locate.request("findPetsByStatusWithHttpInfo", "[\"available\"]");
        assertEquals("200", value);
    }

    @Test
    @AnyStubId(filename = "xApiCalls", requestMode = RequestMode.rmNone)
    void testApiCalls() {
        PetApi petApi = new PetApi();
        PetApiTest petApiTest = new PetApiTest(petApi);

        ResponseEntity<List<Pet>> response = petApiTest.findPetsByStatusWithHttpInfo(asList("available"));

        assertEquals(200, response.getStatusCodeValue());
        List<Pet> body = response.getBody();
        assertEquals(428,body.size());

    }

    @Test
    @AnyStubId(filename = "testVoidHttpInfo")
    void testVoidHttpInfo() {
        PetApi petApi = new PetApi();
        PetApiTest petApiTest = new PetApiTest(petApi);

        Pet pet = new Pet();
        pet.setName("test");
        ResponseEntity<Void> response = petApiTest.addPetWithHttpInfo(pet);

        assertEquals(200, response.getStatusCodeValue());
        List<String> strings = response.getHeaders().get("Content-Type");
        assertEquals(1, strings.size());
        assertEquals("application/json", strings.get(0));

    }

    @Test
    @AnyStubId(filename = "testVoidHttpInfo")
    void testVoid() {
        PetApi petApi = new PetApi();
        PetApiTest petApiTest = new PetApiTest(petApi);

        Pet pet = new Pet();
        pet.setName("test");
        petApiTest.addPet(pet);
        assertTrue(true);

    }
}
