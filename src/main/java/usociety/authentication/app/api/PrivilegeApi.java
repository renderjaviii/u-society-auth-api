package usociety.authentication.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import usociety.authentication.app.util.BaseObject;

@ApiModel(value = "Privilege Api")
@JsonRootName(value = "privilege")
public class PrivilegeApi extends BaseObject {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    public PrivilegeApi() {
        super();
    }

    private PrivilegeApi(Builder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
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

        public PrivilegeApi build() {
            return new PrivilegeApi(this);
        }

    }

}
