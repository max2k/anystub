package org.anystub.openapi;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-21T18:35:35.849942400Z[Europe/London]")
public class PetApi {

    public PetApi() {
    }



    /**
     * Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     * <p><b>200</b> - successful operation
     * <p><b>400</b> - Invalid status value
     * @param status Status values that need to be considered for filter (required)
     * @return List&lt;Pet&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Pet> findPetsByStatus(List<String> status) throws RestClientException {
        return findPetsByStatusWithHttpInfo(status).getBody();
    }

    /**
     * Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     * <p><b>200</b> - successful operation
     * <p><b>400</b> - Invalid status value
     * @param status Status values that need to be considered for filter (required)
     * @return ResponseEntity&lt;List&lt;Pet&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Pet>> findPetsByStatusWithHttpInfo(List<String> status) throws RestClientException {
        throw new UnsupportedOperationException();
    }

    public ResponseEntity<Void > addPetWithHttpInfo (Pet body) throws RestClientException {
        throw new UnsupportedOperationException();
    }

    protected void addPet(Pet pet) {
        throw new UnsupportedOperationException();
    }
}
