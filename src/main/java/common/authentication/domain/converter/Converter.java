package common.authentication.domain.converter;

import org.modelmapper.ModelMapper;

import common.authentication.app.api.UserApi;
import common.authentication.domain.model.User;

public class Converter {

    private static final ModelMapper modelMapper = new ModelMapper();

    private Converter() {
        super();
    }

    public static UserApi user(User user) {
        return modelMapper.map(user, UserApi.class);
    }

    public static User user(UserApi user) {
        return modelMapper.map(user, User.class);
    }

}
