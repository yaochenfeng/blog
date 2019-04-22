package cn.bestws.app.modules.auth.repository;

import cn.bestws.app.modules.auth.entity.User;
import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Api(tags = "users")
@Repository
@RestResource(path = "users")
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
