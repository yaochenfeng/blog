package cn.bestws.app.modules.auth;


import lombok.Data;

import javax.persistence.*;

@Table(name = "auth_role")
@Entity
@Data
public class Role{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String desc;
}