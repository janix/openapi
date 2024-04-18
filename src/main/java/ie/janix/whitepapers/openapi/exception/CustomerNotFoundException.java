package ie.janix.whitepapers.openapi.exception;

/**
 * Exception thrown if record is not found
 */

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(long id) {
        super("Customer with id " + id + " not found");
    }
}

