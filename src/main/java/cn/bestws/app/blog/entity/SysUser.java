package cn.bestws.app.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class SysUser {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 账号
     */
    @Column(length = 50, unique = true)
    @NotNull
    @Size(min = 5, max = 50)
    private String username;
    /**
     * 密码
     */
    @Column(length = 100)
    @RestResource(exported = false)
    private String password;
    /**
     * md5密码盐
     */
    private String salt;
    /**
     * 一个管理员具有多个角色
     */
//    private List<SysRole> roles;
}
