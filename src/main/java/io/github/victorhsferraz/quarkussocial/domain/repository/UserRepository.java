package io.github.victorhsferraz.quarkussocial.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.github.victorhsferraz.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped // Singleton
public class UserRepository implements PanacheRepository<User> {

}
