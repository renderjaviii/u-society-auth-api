package usociety.authentication.app.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import usociety.authentication.app.util.validator.CreateUserRequestConstraint;

@CreateUserRequestConstraint
@ApiModel(value = "Request to create user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {

    @ApiModelProperty(notes = "Username", required = true)
    @NotBlank
    @JsonProperty(value = "username")
    private String username;

    @ApiModelProperty(notes = "Password", required = true)
    @NotBlank
    @JsonProperty(value = "password")
    private String password;

    @ApiModelProperty(notes = "First name", required = true)
    @JsonProperty(value = "firstName")
    private String name;

    @ApiModelProperty(notes = "Photo")
    @JsonProperty(value = "photo")
    private String photo;

    @ApiModelProperty(notes = "Document Number", required = true)
    @NotBlank
    @JsonProperty(value = "documentNumber")
    private String documentNumber;

    @ApiModelProperty(notes = "Email", required = true)
    @Email
    @NotBlank
    @JsonProperty(value = "email")
    private String email;

    @ApiModelProperty(notes = "User role", required = true)
    @NotNull
    @JsonProperty(value = "userRole")
    private String userRole;

    public CreateUserRequest() {
        super();
    }

    private CreateUserRequest(Builder builder) {
        username = builder.username;
        password = builder.password;
        name = builder.firstName;
        photo = builder.photo;
        documentNumber = builder.documentNumber;
        email = builder.email;
        userRole = builder.userRole;
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getUserRole() {
        return userRole;
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
        private String documentNumber;
        private String email;
        private String userRole;

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

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder userRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public CreateUserRequest build() {
            return new CreateUserRequest(this);
        }

    }

}
