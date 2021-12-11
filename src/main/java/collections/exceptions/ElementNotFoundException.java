package collections.exceptions;

/**
 * Class that represents exception bo be thrown when an element if found but not found in collection
 */
public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(String collection) {
        super("The target element is not in this " + collection);
    }
}