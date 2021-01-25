package usociety.authentication.app.api;

class ApiErrorFixture {

    public static String description = "description";
    public static String statusCode = "statusCode";

    public static ApiError value() {
        return ApiError.newBuilder()
                .description(description)
                .statusCode(statusCode)
                .build();
    }

}
