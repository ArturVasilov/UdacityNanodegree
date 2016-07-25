package ru.arturvasilov.stackexchangeclient.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.arturvasilov.stackexchangeclient.testutils.TestRepository;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class RepositoryProviderTest {

    @Test
    public void testNotInited() throws Exception {
        assertNull(RepositoryProvider.provideRemoteRepository());
    }

    @Test
    public void testSetter() throws Exception {
        StackRepository repository = new TestRepository();
        RepositoryProvider.setRemoteRepository(repository);

        assertTrue(repository == RepositoryProvider.provideRemoteRepository());
    }
}
