package com.andreystrinski.user_manager.user.service;

import com.andreystrinski.user_manager.user.model.User;
import com.andreystrinski.user_manager.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Page<User> findUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  public void deleteUser(Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new EntityNotFoundException(
          "Delete operation failed! The requested user does not exist!");
    }

    userRepository.deleteById(userId);
  }

}
