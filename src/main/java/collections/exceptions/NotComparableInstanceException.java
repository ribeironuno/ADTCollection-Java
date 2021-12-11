package collections.exceptions;

/**
 * Throws exception when element in question must be instanced of {@link Comparable} but isn't.
 */
public class NotComparableInstanceException extends Exception {
    public NotComparableInstanceException() {
        super();
    }

    public NotComparableInstanceException(String s) {
        super(s);
    }
}
