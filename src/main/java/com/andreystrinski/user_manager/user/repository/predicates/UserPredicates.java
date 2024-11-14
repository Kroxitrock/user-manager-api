package com.andreystrinski.user_manager.user.repository.predicates;

import com.andreystrinski.user_manager.user.model.QUser;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserPredicates {

  QUser qUser;

  public UserPredicates() {
    qUser = QUser.user;
  }

  public BooleanBuilder searchByName(String nameSearch) {
    var predicate = new BooleanBuilder();

    if (nameSearch != null) {
      predicate.and(qUser.name.toLowerCase().contains(nameSearch.toLowerCase()));
    }

    return predicate;
  }

}
