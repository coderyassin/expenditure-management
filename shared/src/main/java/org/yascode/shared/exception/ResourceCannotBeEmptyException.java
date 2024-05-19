package org.yascode.shared.exception;

public class ResourceCannotBeEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceCannotBeEmptyException(String msg) {
        super(msg);
    }

}
