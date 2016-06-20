package gcommon.marshaller;

/**
 * Base exception for all marshalling errors.
 *
 * @author Guy Raz Nir
 * @since 19/06/2016
 */
public class MarshalException extends RuntimeException {

    public MarshalException() {
    }

    public MarshalException(String message) {
        super(message);
    }

    public MarshalException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarshalException(Throwable cause) {
        super(cause);
    }

    public MarshalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
