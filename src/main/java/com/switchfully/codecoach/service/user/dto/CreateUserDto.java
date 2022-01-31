package com.switchfully.codecoach.service.user.dto;

public record CreateUserDto(String firstName, String lastName, String email, String password, String team, String profilePicture) {
}
