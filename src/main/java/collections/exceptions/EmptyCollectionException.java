package collections.exceptions;

/**
 * Exception that is thrown when a collection is empty
 */
public class EmptyCollectionException extends Exception {

    public EmptyCollectionException() {
        super();
    }

    public EmptyCollectionException(String s) {
        super(s);
    }

}
