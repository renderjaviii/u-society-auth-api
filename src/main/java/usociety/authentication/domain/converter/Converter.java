package usociety.authentication.domain.converter;

import java.util.stream.Collectors;

import usociety.authentication.app.api.PrivilegeApi;
import usociety.authentication.app.api.RoleApi;
import usociety.authentication.app.api.UserApi;
import usociety.authentication.domain.model.Privilege;
import usociety.authentication.domain.model.Role;
import usociety.authentication.domain.model.User;

public class Converter {

    private Converter() {
        super();
    }

    public static UserApi user(User user) {
        return UserApi.newBuilder()
                .lastAccessAt(user.getLastAccessAt())
                .createdAt(user.getCreatedAt())
                .role(convert(user.getRole()))
                .username(user.getUsername())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .name(user.getName())
                .id(user.getId())
                .build();
    }

    private static RoleApi convert(Role role) {
        return RoleApi.newBuilder()
                .description(role.getDescription())
                .name(role.getName())
                .id(role.getId())
                .privileges(role.getPrivileges()
                        .stream()
                        .map(Converter::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    private static PrivilegeApi convert(Privilege privilege) {
        return PrivilegeApi.newBuilder()
                .description(privilege.getDescription())
                .name(privilege.getName())
                .id(privilege.getId())
                .build();
    }

    public static User user(UserApi user) {
        return User.newBuilder()
                .lastAccessAt(user.getLastAccessAt())
                .createdAt(user.getCreatedAt())
                .role(convert(user.getRole()))
                .username(user.getUsername())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .name(user.getName())
                .id(user.getId())
                .build();
    }

    private static Role convert(RoleApi role) {
        return Role.newBuilder()
                .description(role.getDescription())
                .name(role.getName())
                .id(role.getId())
                .privileges(role.getPrivileges()
                        .stream()
                        .map(Converter::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    private static Privilege convert(PrivilegeApi privilege) {
        return Privilege.newBuilder()
                .description(privilege.getDescription())
                .name(privilege.getName())
                .id(privilege.getId())
                .build();
    }

}
