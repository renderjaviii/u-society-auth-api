package usociety.authentication.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

public class UserFixture {

    public static LocalDateTime lastAccessAt;
    public static LocalDate createdAt;
    public static String description;
    public static String encodePass;
    public static String firstName;
    public static String username;
    public static int privilegeId;
    public static String email;
    public static String name;
    public static int roleId;
    public static String url;

    static {
        lastAccessAt = LocalDateTime.of(2020, 12, 2, 0, 0);
        createdAt = LocalDate.of(2020, 10, 2);
        description = "description";
        encodePass = "encodePass";
        username = "username";
        privilegeId = 1;
        email = "email";
        name = "name";
        roleId = 1;
        url = "url";
    }

    public static User value() {
        return User.newBuilder()
                .lastAccessAt(lastAccessAt)
                .name(firstName)
                .photo(url)
                .username(username)
                .password(encodePass)
                .email(email)
                .role(Role.newBuilder()
                        .description(description)
                        .privileges(Collections.singletonList(Privilege.newBuilder()
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
