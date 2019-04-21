package cn.bestws.app.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auth_role")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String desc;

}
