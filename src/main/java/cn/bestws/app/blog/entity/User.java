package cn.bestws.app.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auth_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String nickname;
    @JsonIgnore
    private String password;

}
