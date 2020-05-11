package com.boot.pp25.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 3, max = 10)
    @Column(name = "username")
    @NotBlank(message = "Поле не должно быть пустым")
    private String userName;

    @Size(min = 3, max = 10)
    @Column(name = "password")
    @NotBlank(message = "Поле не должно быть пустым")
    private String password;

    @Size(min = 3, max = 10)
    @Column(name = "lastName")
    @NotBlank(message = "Поле не должно быть пустым")
    private String lastName;

    @Digits(integer = 2, fraction = 0, message = "Не более 2-х знаков")
    @Column(name = "age")
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0)
    private Integer age;

    @Column(name = "email")
    @NotBlank(message = "Поле не должно быть пустым")
    @Email(message = "Поле должно быть по стандартам email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String userName, String password, String lastName, Integer age, String email, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(age, user.age) &&
                Objects.equals(email, user.email) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, lastName, age, email, roles);
    }

    @Override
    public String toString() {
        return "userName :" + userName +
                "\nlastName " + lastName +
                "\npassword " + password +
                "\nemail " + email +
                "\nage " + age +
                "\nroles " + roles.stream().map(Objects::toString).collect(Collectors.joining(" "));
    }
}

