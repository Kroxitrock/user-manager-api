package com.andreystrinski.user_manager.controller;

import com.andreystrinski.user_manager.user.model.User;
import com.andreystrinski.user_manager.user.model.dto.SaveUserDto;
import com.andreystrinski.user_manager.user.model.dto.UserDetailsDto;
import com.andreystrinski.user_manager.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final ModelMapper modelMapper;

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody @Valid SaveUserDto user) {
    var entity = modelMapper.map(user, User.class);

    userService.saveUser(entity);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<Page<UserDetailsDto>> findUsers(
      @RequestParam(required = false) String name,
      @PageableDefault(sort = "id", direction = Direction.ASC, value = 5) Pageable pageable) {
    var users = userService.findUsers(name, pageable);

    var response = users.map((user) -> modelMapper.map(user, UserDetailsDto.class));

    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable long id,
      @RequestBody @Valid SaveUserDto user) {
    var entity = userService.getUser(id);
    modelMapper.map(user, entity);

    userService.saveUser(entity);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
  }
}
