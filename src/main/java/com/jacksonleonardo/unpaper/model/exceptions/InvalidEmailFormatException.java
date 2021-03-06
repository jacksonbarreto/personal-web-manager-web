package com.jacksonleonardo.unpaper.model.exceptions;

/**
 * Launched to indicate that the e-mail address, complete and in plain text,
 * used as a parameter for the {@code IEmail} interface does not fall under
 * the guidelines of RFC2822 and RFC1035.
 */
public class InvalidEmailFormatException extends IllegalArgumentException {
    public InvalidEmailFormatException() {
        super();
    }
}
