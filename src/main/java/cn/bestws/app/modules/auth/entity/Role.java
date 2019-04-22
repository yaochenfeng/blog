package cn.bestws.app.modules.auth.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auth_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(length = 25)
    private String name;
}
