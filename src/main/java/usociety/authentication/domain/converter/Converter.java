package usociety.authentication.domain.converter;

import static java.lang.Boolean.TRUE;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import usociety.authentication.app.api.UserApi;
import usociety.authentication.domain.model.User;

public class Converter {

    private static final ModelMapper modelMapper;

    private Converter() {
        super();
    }

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(TRUE);
    }

    public static UserApi user(User user) {
        return modelMapper.map(user, UserApi.class);
    }

    public static User user(UserApi user) {
        return modelMapper.map(user, User.class);
    }

}
