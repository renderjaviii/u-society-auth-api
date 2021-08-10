package usociety.authentication.app.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import usociety.authentication.app.util.BaseObject;

@ApiModel(value = "User Api")
public class UserApi extends BaseObject {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String email;

    @JsonProperty
    private String username;

    @JsonProperty
    private String photo;

    @JsonProperty
    private LocalDate createdAt;

    @JsonProperty
    private LocalDateTime lastAccessAt;

    @JsonProperty
    private RoleApi role;

    public UserApi() {
        super();
    }

    private UserApi(Builder builder) {
        id = builder.id;
        name = builder.name;
        email = builder.email;
        username = builder.username;
        photo = builder.photo;
        createdAt = builder.createdAt;
        lastAccessAt = builder.lastAccessAt;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoto() {
        return photo;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastAccessAt() {
        return lastAccessAt;
    }

    public RoleApi getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static final class Builder {

        private Long id;
        private String name;
        private String email;
        private String username;
        private String photo;
        private LocalDate createdAt;
        private LocalDateTime lastAccessAt;
        private RoleApi role;

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

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
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

        public Builder role(RoleApi role) {
            this.role = role;
            return this;
        }

        public UserApi build() {
            return new UserApi(this);
        }

    }

}
