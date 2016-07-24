package ru.arturvasilov.stackexchangeclient.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.arturvasilov.stackexchangeclient.rx.StubAction;
import ru.arturvasilov.stackexchangeclient.testutils.MockUtils;

import static org.junit.Assert.assertEquals;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class PreferencesUtilsTest {

    static {
        MockUtils.setupHawkForTests();
    }

    @Test
    public void testPutAccessToken() throws Exception {
        PreferencesUtils.saveAccessToken("token")
                .subscribe(Assert::assertTrue);
    }

    @Test
    public void testGetAccessToken() throws Exception {
        MockUtils.PREFERENCES.edit().putString("key_access_token", "token").apply();

        PreferencesUtils.getAccessToken()
                .subscribe(s -> {
                            assertEquals("token", "s");
                        }, new StubAction<>(),
                        () -> MockUtils.PREFERENCES.edit().remove("key_access_token").apply());
    }
}
