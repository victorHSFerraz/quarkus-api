package io.github.victorhsferraz.quarkussocial.domain.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.github.victorhsferraz.quarkussocial.domain.model.Follower;
import io.github.victorhsferraz.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    public boolean follows(User follower, User user) {
        var params = Parameters
                .with("follower", follower)
                .and("user", user).map();

        var query = find("follower = :follower and user = :user", params);
        Optional<Follower> result = query.firstResultOptional();
        return result.isPresent();
    }

    public List<Follower> findByUser(Long userId) {
        var query = find("user.id", userId);
        return query.list();
    }

    public void deleteByFollowerAndUser(Long followerId, Long userId) {
        var params = Parameters
                .with("followerId", followerId)
                .and("userId", userId)
                .map();

        delete("follower.id = :followerId and user.id = :userId", params);
    }
}
