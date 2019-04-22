package cn.bestws.app.modules.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auth_user")
public class User{
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 32)
    private String username;
    @Column(length = 32)
    private String nickname;
    @JsonIgnore
    private String password;
}
