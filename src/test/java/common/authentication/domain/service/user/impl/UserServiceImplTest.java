package common.authentication.domain.service.user.impl;

import static java.lang.Boolean.FALSE;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import common.authentication.app.api.UserApi;
import common.authentication.app.api.UserApiFixture;
import common.authentication.domain.exception.UserException;
import common.authentication.domain.model.UserFixture;
import common.authentication.domain.repository.UserRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String USERNAME = "username";

    @Mock
    private UserRepository userRepository;
    @Mock
    private Clock clock;

    @InjectMocks
    private UserServiceImpl subject;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(subject, "userRepository", userRepository);
        ReflectionTestUtils.setField(subject, "clock", clock);
    }

    @Test
    public void shouldGetUserUsingTheCorrectData() throws UserException {
        when(userRepository.findByUsernameAndAccountLocked(any(), anyBoolean()))
                .thenReturn(Optional.of(UserFixture.value()));

        UserApi executed = subject.get(USERNAME);
        assertEquals(UserApiFixture.value(), executed);
        verify(userRepository).findByUsernameAndAccountLocked(USERNAME, FALSE);
    }

}