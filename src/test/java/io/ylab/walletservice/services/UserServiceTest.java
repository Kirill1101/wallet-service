package io.ylab.walletservice.services;

import io.ylab.walletservice.infrastucuture.factory.UserIdFactory;
import io.ylab.walletservice.infrastucuture.factory.UserServiceFactory;
import io.ylab.walletservice.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UserServiceTest {
    @Test
    public void methodCreateUserShouldCreateUser() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        Assert.assertNotNull(userService.getUserByLogin("kirill"));
    }

    @Test
    public void methodUserIsExistsShouldReturnCorrectAnswer() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());

        Assert.assertFalse(userService.userIsExists("kirill"));
        userService.createUser(user);
        Assert.assertTrue(userService.userIsExists("kirill"));
    }

    @Test
    public void methodGetUserByLoginShouldReturnCorrectUser() {
        UserService userService = UserServiceFactory.getUserService();
        User user = new User(UserIdFactory.getNewUserId(), "kirill", "1234", new ArrayList<>());
        userService.createUser(user);

        Assert.assertEquals(user, userService.getUserByLogin("kirill"));
    }
}
