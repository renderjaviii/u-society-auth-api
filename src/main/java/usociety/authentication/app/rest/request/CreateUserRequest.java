package usociety.authentication.app.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Request to create user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {

    @ApiModelProperty(notes = "Name", required = true)
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(notes = "Username", required = true)
    @NotBlank
    @JsonProperty(value = "username")
    private String username;

    @ApiModelProperty(notes = "Email", required = true)
    @Email
    @NotBlank
    @JsonProperty(value = "email")
    private String email;

    @ApiModelProperty(notes = "Photo")
    @JsonProperty(value = "photo")
    private String photo;

    @ApiModelProperty(notes = "Password", required = true)
    @NotBlank
    @JsonProperty(value = "password")
    private String password;

    public CreateUserRequest() {
        super();
    }

    private CreateUserRequest(Builder builder) {
        username = builder.username;
        password = builder.password;
        name = builder.firstName;
        photo = builder.photo;
        email = builder.email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String username;
        private String password;
        private String firstName;
        private String photo;
        private String email;

        private Builder() {
            super();
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder photo(String photo) {
            this.photo = photo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public CreateUserRequest build() {
            return new CreateUserRequest(this);
        }

    }

}
