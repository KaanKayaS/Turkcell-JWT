package com.turkcell.spring_cqrs.application.features.user.command.adminregister;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.application.features.user.rule.UserBusinessRules;
import com.turkcell.spring_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.spring_cqrs.domain.User;
import com.turkcell.spring_cqrs.persistence.repository.UserRepository;

@Component
public class AdminRegisterCommandHandler implements CommandHandler<AdminRegisterCommand, AdminRegisterResponse> {
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;

    public AdminRegisterCommandHandler(UserRepository userRepository, UserBusinessRules userBusinessRules,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdminRegisterResponse handle(AdminRegisterCommand command) {
        this.userBusinessRules.userWithSameEmailMustNotExist(command.email());

        User user = new User();
        user.setEmail(command.email());
        user.setPassword(passwordEncoder.encode(command.password()));
        user.setRoles(List.of("ADMIN"));

        userRepository.save(user);

        return new AdminRegisterResponse(user.getId(), user.getEmail());
    }
}
