package com.turkcell.spring_cqrs.core.security.authorization;

import java.util.Collections;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.spring_cqrs.core.mediator.pipeline.RequestHandlerDelegate;
import com.turkcell.spring_cqrs.core.security.context.UserContext;
import com.turkcell.spring_cqrs.core.security.exception.AuthenticationException;
import com.turkcell.spring_cqrs.core.security.exception.AuthorizationException;

@Component
@Order(10)
public class AuthorizationBehavior implements PipelineBehavior {
    private final UserContext userContext;

    public AuthorizationBehavior(UserContext userContext) {
        this.userContext = userContext;
    }

    

    @Override
    public boolean supports(Object request) {
        return request instanceof AuthorizableRequest;
    }


    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        if(!userContext.isAuthenticated())
            throw new AuthenticationException();
        
        AuthorizableRequest authRequest = (AuthorizableRequest) request;
        List<String> requiredRoles = authRequest.getRequiredRoles();

        if(requiredRoles != null && !requiredRoles.isEmpty()) {
            List<String> userRoles = userContext.getRoles() != null
                    ? userContext.getRoles()
                    : Collections.emptyList();

            boolean hasRole = requiredRoles.stream().anyMatch(userRoles::contains);
            if(!hasRole)
                throw new AuthorizationException();
        }
        
        return next.invoke(); 
    }

}
