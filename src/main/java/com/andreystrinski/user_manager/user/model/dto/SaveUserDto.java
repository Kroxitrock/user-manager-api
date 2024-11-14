package com.andreystrinski.user_manager.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveUserDto {

  @NotBlank(message = "Name must not be blank.")
  private String name;

  @Email(message = "The email should be in the correct format.")
  @NotBlank(message = "Email must not be blank.")
  private String email;

  @NotBlank(message = "The phone number must not be blank.")
  @Pattern(regexp = "^\\+?[0-9]+$", message = "A phone number can be consisted only of numbers and may start with a \"+\".")
  private String phoneNumber;
}
