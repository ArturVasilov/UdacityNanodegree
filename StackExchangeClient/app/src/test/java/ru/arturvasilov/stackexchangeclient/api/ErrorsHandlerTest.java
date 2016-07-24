package ru.arturvasilov.stackexchangeclient.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.arturvasilov.stackexchangeclient.testutils.MockUtils;
import rx.Observable;

import static junit.framework.Assert.assertEquals;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class ErrorsHandlerTest {

    static {
        MockUtils.setupHawkForTests();
    }

    @Test
    public void testErrorHandling() throws Exception {
        Observable.just(10)
                .compose(ErrorsHandler.handleErrors())
                .subscribe(integer -> {
                    assertEquals(10, integer.intValue());
                });
    }
}
