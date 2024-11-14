package com.andreystrinski.user_manager.user.model.dto;

import lombok.Data;

@Data
public class UserDetailsDto {
  private Long id;
  private String name;
  private String email;
  private String phoneNumber;
}
