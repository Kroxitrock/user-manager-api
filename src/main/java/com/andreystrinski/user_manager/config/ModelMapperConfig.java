package com.andreystrinski.user_manager.config;

import com.andreystrinski.user_manager.user.model.User;
import com.andreystrinski.user_manager.user.repository.UserRepository;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Autowired
  public UserRepository repository;

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


  @Bean
  public boolean isInitialised() {
    var users = new ArrayList<User>();
    users.add(
        User.builder().name("Andrey").email("andro@gmail.com").phoneNumber("+359888888").build());
    users.add(
        User.builder().name("Nikoleta").email("niki@gmail.com").phoneNumber("+359888888").build());
    users.add(User.builder().name("Alexander").email("sasho@gmail.com").phoneNumber("+359888888")
        .build());
    users.add(
        User.builder().name("Valentin").email("valo@gmail.com").phoneNumber("+359888888").build());
    users.add(
        User.builder().name("Marian").email("marian@gmail.com").phoneNumber("+359888888").build());
    users.add(
        User.builder().name("Dimitar").email("mitko@gmail.com").phoneNumber("+359888888").build());
    users.add(
        User.builder().name("Peter").email("pepeto@gmail.com").phoneNumber("+359888888").build());

    repository.saveAll(users);
    return true;
  }
}
