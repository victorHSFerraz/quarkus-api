package io.github.victorhsferraz.quarkussocial.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.github.victorhsferraz.quarkussocial.domain.model.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

}
