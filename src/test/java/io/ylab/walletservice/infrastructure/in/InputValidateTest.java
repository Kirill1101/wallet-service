package io.ylab.walletservice.infrastructure.in;

import io.ylab.walletservice.infrastucuture.in.InputValidate;
import io.ylab.walletservice.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class InputValidateTest {
    @Test
    public void methodIsCorrectNumberInListShouldReturnCorrectValue() {
        Assert.assertTrue(InputValidate.isCorrectNumberInMenu(1,1,1));
        Assert.assertFalse(InputValidate.isCorrectNumberInMenu(1,5,6));
    }

    @Test
    public void methodUserIsExistsForLoginShouldReturnCorrectValue() {
        User user = new User(1, "kirill", "1234", new ArrayList<>());
        Assert.assertFalse(InputValidate.userIsExistsForLogin(null));
        Assert.assertTrue(InputValidate.userIsExistsForLogin(user));
    }

    @Test
    public void methodIsCorrectPasswordShouldReturnCorrectValue() {
        Assert.assertFalse(InputValidate.isCorrectPassword("admin", "admim"));
        Assert.assertTrue(InputValidate.isCorrectPassword("admin", "admin"));
    }

    @Test
    public void methodIsPossibleToTakeMoneyShouldReturnCorrectValue() {
        Assert.assertFalse(InputValidate.isPossibleToTakeMoney(10, 11));
        Assert.assertTrue(InputValidate.isPossibleToTakeMoney(10, 10));
    }

    @Test
    public void methodIsBiggerThanZeroShouldReturnCorrectValue() {
        Assert.assertFalse(InputValidate.isBiggerThanZero(0));
        Assert.assertTrue(InputValidate.isBiggerThanZero(1));
    }

    @Test
    public void methodIsEmptyShouldReturnCorrectValue() {
        Assert.assertFalse(InputValidate.isEmpty("admin"));
        Assert.assertTrue(InputValidate.isEmpty(""));
    }

    @Test
    public void methodIsIntShouldReturnCorrectValue() {
        Assert.assertFalse(InputValidate.isInt("5.1"));
        Assert.assertTrue(InputValidate.isInt("5"));
    }
}
