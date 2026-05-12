package com.turkcell.spring_cqrs.application.features.user.command.adminregister;

import java.util.UUID;

public record AdminRegisterResponse(UUID id, String email) {}
