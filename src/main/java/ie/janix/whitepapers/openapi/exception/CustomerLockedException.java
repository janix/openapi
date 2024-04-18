package ie.janix.whitepapers.openapi.exception;

/**
 * Exception thrown if record cannot be updated
 */
public class CustomerLockedException extends Exception {

    public CustomerLockedException(long id) {
        super("Customer with id " + id + " cannot be updated at this time");
    }

}
