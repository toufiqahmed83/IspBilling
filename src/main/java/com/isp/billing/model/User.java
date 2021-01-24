package com.isp.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Toufiq on 7/25/2019.
 */
@Entity
@Table(name = "APP_USER")
//@Getter @Setter @NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    @Size(min = 4 , message = "Password must be Greater then 4 digits.")
    private String password;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles= new LinkedHashSet<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "START_DATE")//, nullable = false
    @Temporal(TemporalType.DATE)
//    @NotEmpty(message = "Date is required.")
    private Date startDates;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "END_DATE")//, nullable = false
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDates;

    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new LinkedHashSet<>();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return grantedAuthorities;
    }
}
