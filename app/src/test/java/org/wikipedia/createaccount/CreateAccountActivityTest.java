package org.wikipedia.createaccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.wikipedia.test.TestRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.wikipedia.createaccount.CreateAccountActivity.ValidateResult;
import static org.wikipedia.createaccount.CreateAccountActivity.validateInput;

@RunWith(TestRunner.class) public class CreateAccountActivityTest {

    @Test public void testValidateInputSuccess() throws Throwable {
        assertThat(validateInput("user", "password", "", false, ""),
                is(ValidateResult.SUCCESS));
    }

    @Test public void testValidateInputSuccessRepeatPassword() throws Throwable {
        assertThat(validateInput("user", "password", "password", true, ""),
                is(ValidateResult.SUCCESS));
    }

    @Test public void testValidateInputSuccessWithEmail() throws Throwable {
        assertThat(validateInput("user", "password", "", false, "test@example.com"),
                is(ValidateResult.SUCCESS));
    }

    @Test public void testValidateInputInvalidUser() throws Throwable {
        assertThat(validateInput("user[]", "password", "password", true, ""),
                is(ValidateResult.INVALID_USERNAME));
    }

    @Test public void testValidateInputInvalidPassword() throws Throwable {
        assertThat(validateInput("user", "foo", "password", true, ""),
                is(ValidateResult.INVALID_PASSWORD));
    }

    @Test public void testValidateInputPasswordMismatch() throws Throwable {
        assertThat(validateInput("user", "password", "passw0rd", true, ""),
                is(ValidateResult.PASSWORD_MISMATCH));
    }

    @Test public void testValidateInputInvalidEmail() throws Throwable {
        assertThat(validateInput("user", "password", "password", true, "foo"),
                is(ValidateResult.INVALID_EMAIL));
    }
}
