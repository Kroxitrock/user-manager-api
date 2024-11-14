package com.andreystrinski.user_manager.controller;

import com.andreystrinski.user_manager.user.model.dto.UserDetailsDto;
import com.andreystrinski.user_manager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final ModelMapper modelMapper;

  private final UserService userService;

  @GetMapping
  public ResponseEntity<Page<UserDetailsDto>> findUsers(
      @PageableDefault(sort = "id", direction = Direction.ASC, value = 5) Pageable pageable) {
    var users = userService.findUsers(pageable);

    var response = users.map((user) -> modelMapper.map(user, UserDetailsDto.class));

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
  }
}
