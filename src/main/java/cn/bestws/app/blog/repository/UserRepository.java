package cn.bestws.app.blog.repository;

import cn.bestws.app.blog.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.bestws.app.blog.projection.UserPasswordProjection;
@RepositoryRestResource(path = "users",excerptProjection = UserPasswordProjection.class)
public interface UserRepository extends JpaRepository<SysUser,Long> {

    @Override
    <S extends SysUser> S save(S entity);
}
