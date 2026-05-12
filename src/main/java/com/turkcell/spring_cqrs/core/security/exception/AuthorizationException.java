package com.turkcell.spring_cqrs.core.security.exception;

/**
 * Kullanıcı authenticated ancak gerekli role sahip değilse fırlatılır.
 * SecurityExceptionHandler tarafından 403 Forbidden olarak dönülür.
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super("Bu işlem için yetkiniz bulunmamaktadır.");
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
