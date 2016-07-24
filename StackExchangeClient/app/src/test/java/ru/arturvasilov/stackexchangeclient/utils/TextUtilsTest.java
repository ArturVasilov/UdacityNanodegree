package ru.arturvasilov.stackexchangeclient.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class TextUtilsTest {

    @Test
    public void testNullStringIsEmpty() throws Exception {
        boolean empty = TextUtils.isEmpty(null);
        //noinspection ConstantConditions
        assertTrue(empty);
    }

    @Test
    public void testEmptyString() throws Exception {
        boolean empty = TextUtils.isEmpty("");
        assertTrue(empty);
    }

    @Test
    public void testNotEmptyString1() throws Exception {
        boolean empty = TextUtils.isEmpty("s");
        assertFalse(empty);
    }

    @Test
    public void testNotEmptyString2() throws Exception {
        boolean empty = TextUtils.isEmpty("safgbgnsvskvbs;o;wl dcol ibsvos;cj,sp;csvbwfsl");
        assertFalse(empty);
    }

    @Test
    public void testNotEmptyString3() throws Exception {
        boolean empty = TextUtils.isEmpty(";hvsj'c,;hmvsj,k..ca'j;dglvsbnqufrypnny79o" +
                "'pacha,a,ck'.vfpvvs'fvj,fwgw,evs'vsv.s'[.s.s]AOZ*^906jakz,-[Kk00-K-9-00" +
                "zax.axa/[xxa]");
        assertFalse(empty);
    }
}
