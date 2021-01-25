package usociety.authentication.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import usociety.authentication.app.util.BaseObject;

@ApiModel(value = "Api Error")
public class ApiError extends BaseObject {

    @JsonProperty
    private String description;
    @JsonProperty
    private String statusCode;

    public ApiError() {
        super();
    }

    public ApiError(String description, String statusCode) {
        this.description = description;
        this.statusCode = statusCode;
    }

    private ApiError(Builder builder) {
        description = builder.description;
        statusCode = builder.statusCode;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getDescription() {
        return this.description;
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

        private String description;
        private String statusCode;

        private Builder() {
            super();
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder statusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }

    }

}