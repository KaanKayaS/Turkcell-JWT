package com.turkcell.spring_cqrs.core.security.exception;

/**
 * JWT yoksa veya geçersizse fırlatılır.
 * SecurityExceptionHandler tarafından 401 Unauthorized olarak dönülür.
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Kimlik doğrulama gerekli.");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
