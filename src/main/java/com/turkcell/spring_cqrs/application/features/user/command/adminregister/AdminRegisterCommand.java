package com.turkcell.spring_cqrs.application.features.user.command.adminregister;

import org.hibernate.validator.constraints.Length;

import com.turkcell.spring_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminRegisterCommand(
    @NotBlank @Email String email,
    @NotBlank @Length(min=3) String password
) implements Command<AdminRegisterResponse> {}
