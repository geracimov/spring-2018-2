package ru.geracimov.otus.spring.hw25springintegration.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SP_SEC_AUTHORITIES")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

}
