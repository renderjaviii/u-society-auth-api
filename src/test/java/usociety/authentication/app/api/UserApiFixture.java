package usociety.authentication.app.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

public class UserApiFixture {

    public static LocalDateTime lastAccessAt;
    public static LocalDate birthDate;
    public static LocalDate createdAt;
    public static String description;
    public static String username;
    public static int privilegeId;
    public static String email;
    public static String name;
    public static int roleId;

    static {
        lastAccessAt = LocalDateTime.of(2020, 12, 2, 0, 0);
        birthDate = LocalDate.of(2000, 10, 2);
        createdAt = LocalDate.of(2020, 10, 2);
        description = "description";
        username = "username";
        privilegeId = 1;
        email = "email";
        name = "name";
        roleId = 1;

    }

    public static UserApi value() {
        return UserApi.newBuilder()
                .lastAccessAt(lastAccessAt)
                .createdAt(createdAt)
                .username(username)
                .email(email)
                .role(RoleApi.newBuilder()
                        .description(description)
                        .privileges(Collections.singletonList(PrivilegeApi.newBuilder()
                                .description(description)
                                .name(name)
                                .id(roleId)
                                .build()))
                        .name(name)
                        .id(privilegeId)
                        .build())
                .build();
    }

}
