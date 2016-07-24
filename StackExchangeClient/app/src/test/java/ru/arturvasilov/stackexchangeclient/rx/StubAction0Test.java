package ru.arturvasilov.stackexchangeclient.rx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rx.functions.Action0;

import static org.junit.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class StubAction0Test {

    @Test
    public void testCreated() throws Exception {
        Action0 action0 = new StubAction0();
        assertNotNull(action0);
    }

    @Test
    public void testCall() throws Exception {
        Action0 action0 = new StubAction0();
        action0.call();
    }
}
