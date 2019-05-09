package cn.bestws.app.modules.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "auth_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;
}
