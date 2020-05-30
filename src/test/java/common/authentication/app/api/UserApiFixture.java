package common.authentication.app.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import common.authentication.domain.enums.GenderEnum;

public class UserApiFixture {

    public static LocalDateTime lastAccessAt;
    public static String documentNumber;
    public static LocalDate birthDate;
    public static LocalDate createdAt;
    public static String description;
    public static String phoneNumber;
    public static String encodePass;
    public static String firstName;
    public static String lastName;
    public static String username;
    public static int privilegeId;
    public static String gender;
    public static String email;
    public static String name;
    public static int roleId;

    static {
        lastAccessAt = LocalDateTime.of(2020, 12, 2, 0, 0);
        birthDate = LocalDate.of(2000, 10, 2);
        createdAt = LocalDate.of(2020, 10, 2);
        gender = GenderEnum.MALE.getValue();
        documentNumber = "documentNumber";
        description = "description";
        phoneNumber = "phoneNumber";
        encodePass = "encodePass";
        firstName = "firstName";
        lastName = "lastName";
        username = "username";
        privilegeId = 1;
        email = "email";
        name = "name";
        roleId = 1;

    }

    public static UserApi value() {
        return UserApi.newBuilder()
                .lastAccessAt(lastAccessAt)
                .birthDate(birthDate)
                .createdAt(createdAt)
                .gender(gender)
                .documentNumber(documentNumber)
                .phoneNumber(phoneNumber)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(encodePass)
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
