package org.anystub.openapi;

import com.fasterxml.jackson.core.type.TypeReference;
import org.anystub.AnyStubId;
import org.anystub.RequestMode;
import org.anystub.mgmt.BaseManagerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FakeTest {

    static class Pet {
        long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    static class PetApi {
        Pet getPetById(long id) {
            throw new RuntimeException("does not work");
        }
    }

    static class PetApiTest extends PetApi {
        final PetApi petApi;

        PetApiTest(PetApi petApi) {
            this.petApi = petApi;
        }

        @Override
        Pet getPetById(long id) {
            return BaseManagerFactory.locate()
                    .requestO(() -> petApi.getPetById(id),
                            new TypeReference<Pet>() {
                            },
                            "getPetById", id);
        }
    }

    static class PetApiTest1 extends PetApi {
        final PetApi petApi;

        PetApiTest1(PetApi petApi) {
            this.petApi = petApi;
        }

        @Override
        Pet getPetById(long id) {
            return BaseManagerFactory.locate()
                    .requestO(() -> petApi.getPetById(id),
                            Pet.class,
                            "getPetById", id);
        }
    }

    @Test
    @AnyStubId(requestMode = RequestMode.rmFake)
    void requestGen() {

        PetApi petApi = new PetApi();

        PetApiTest petApiTest = new PetApiTest(petApi);

        Pet petById = petApiTest.getPetById(1233L);


        assertEquals(-592218937L, petById.getId());
    }

    @Test
    @AnyStubId(filename = "requestGen", requestMode = RequestMode.rmFake)
    void requestGen1() {

        PetApi petApi = new PetApi();

        PetApiTest1 petApiTest = new PetApiTest1(petApi);

        Pet petById = petApiTest.getPetById(1233L);


        assertEquals(-592218937L, petById.getId());
    }

    @Test
    @AnyStubId(requestMode = RequestMode.rmPassThrough)
    void requestException() {
        PetApi petApi = new PetApi();

        PetApiTest petApiTest = new PetApiTest(petApi);


        assertThrows(Exception.class, () -> {
            Pet petById = petApiTest.getPetById(123L);
            System.out.println(petById.toString());
        });


    }
}
