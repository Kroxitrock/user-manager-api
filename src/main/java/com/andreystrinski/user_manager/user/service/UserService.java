package com.andreystrinski.user_manager.user.service;

import com.andreystrinski.user_manager.user.model.User;
import com.andreystrinski.user_manager.user.repository.UserRepository;
import com.andreystrinski.user_manager.user.repository.predicates.UserPredicates;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserPredicates userPredicates;

  @Transactional
  public void saveUser(User user) {
    userRepository.save(user);
  }

  public User getUser(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found!"));
  }

  public Page<User> findUsers(String nameSearch, Pageable pageable) {
    var predicate = userPredicates.searchByName(nameSearch);

    return userRepository.findAll(predicate, pageable);
  }

  @Transactional
  public void deleteUser(Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new EntityNotFoundException(
          "Delete operation failed! The requested user does not exist!");
    }

    userRepository.deleteById(userId);
  }

}
