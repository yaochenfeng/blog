package cn.bestws.app.blog.projection;

import cn.bestws.app.blog.entity.SysUser;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "passwords", types = { SysUser.class })
public interface UserPasswordProjection {

    String getPassword();
}
