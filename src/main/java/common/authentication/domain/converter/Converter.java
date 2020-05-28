package common.authentication.domain.converter;

import common.authentication.app.api.UserApi;
import common.authentication.domain.model.User;

public class Converter {

    private Converter() {
        super();
    }

    public static UserApi user(User user) {
        return UserApi.newBuilder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .documentNumber(user.getDocumentNumber())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .lastAccessAt(user.getLastAccessAt())
                .build();
    }

    public static User user(UserApi user) {
        return User.newBuilder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .documentNumber(user.getDocumentNumber())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .lastAccessAt(user.getLastAccessAt())
                .build();
    }

}
