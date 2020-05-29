package common.authentication.domain.service.user.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import common.authentication.app.api.UserApi;
import common.authentication.domain.converter.Converter;
import common.authentication.domain.exception.GenericException;
import common.authentication.domain.model.User;
import common.authentication.domain.repository.UserRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl subject;

    private final String userName = "username";
    private User user;

    @Before
    public void setUp() {
        user = User.newBuilder()
                .documentNumber("documentNumber")
                .build();
    }

    @Test
    public void shouldGetUserUsingTheCorrectData() throws GenericException {
        when(userRepository.getOne(any())).thenReturn(user);

        UserApi executed = subject.get(username, documentNumber, email, userName);
        assertEquals(Converter.user(user), executed);
        verify(userRepository).findByUsername(userName);
    }

}