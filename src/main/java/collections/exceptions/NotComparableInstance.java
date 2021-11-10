package collections.exceptions;

/**
 * Throws exception when element in question must be instanced of {@link Comparable} but isn't.
 */
public class NotComparableInstance extends Exception {
    public NotComparableInstance() {
        super();
    }

    public NotComparableInstance(String s) {
        super(s);
    }
}
