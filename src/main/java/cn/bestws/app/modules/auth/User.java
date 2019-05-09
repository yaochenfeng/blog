package cn.bestws.app.modules.auth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    private String id;

    private String username;

    private String password;
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;
}
