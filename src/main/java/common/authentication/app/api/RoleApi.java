package common.authentication.app.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import common.authentication.app.util.BaseObject;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Role Api")
@JsonRootName(value = "role")
public class RoleApi extends BaseObject {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private List<PrivilegeApi> privileges;

    public RoleApi() {
        super();
    }

    private RoleApi(Builder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        privileges = builder.privileges;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PrivilegeApi> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<PrivilegeApi> privileges) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private Integer id;
        private String name;
        private String description;
        private List<PrivilegeApi> privileges;

        private Builder() {
            super();
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder privileges(List<PrivilegeApi> privileges) {
            this.privileges = privileges;
            return this;
        }

        public RoleApi build() {
            return new RoleApi(this);
        }

    }

}
