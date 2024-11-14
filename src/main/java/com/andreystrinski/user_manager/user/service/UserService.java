package com.andreystrinski.user_manager.user.service;

import com.andreystrinski.user_manager.user.model.User;
import com.andreystrinski.user_manager.user.repository.UserRepository;
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

}
