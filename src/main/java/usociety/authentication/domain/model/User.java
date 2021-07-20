package usociety.authentication.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "photo")
    private String photo;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(name = "last_access_at")
    private LocalDateTime lastAccessAt;

    @Column(name = "account_locked", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean accountLocked;

    @Column(name = "email_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean emailVerified;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public User() {
        super();
    }

    public User(Builder builder) {
        id = builder.id;
        name = builder.name;
        photo = builder.photo;
        username = builder.username;
        password = builder.password;
        email = builder.email;
        createdAt = builder.createdAt;
        lastAccessAt = builder.lastAccessAt;
        accountLocked = builder.accountLocked;
        emailVerified = builder.emailVerified;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setLastAccessAt(LocalDateTime lastAccessAt) {
        this.lastAccessAt = lastAccessAt;
    }

    public LocalDateTime getLastAccessAt() {
        return lastAccessAt;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User that = (User) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static final class Builder {

        private Long id;
        private String name;
        private String username;
        private String password;
        private String email;
        private String photo;
        private LocalDate createdAt;
        private LocalDateTime lastAccessAt;
        private boolean accountLocked;
        private boolean emailVerified;
        private Role role;

        private Builder() {
            super();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder photo(String photo) {
            this.photo = photo;
            return this;
        }

        public Builder createdAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastAccessAt(LocalDateTime lastAccessAt) {
            this.lastAccessAt = lastAccessAt;
            return this;
        }

        public Builder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public Builder emailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}