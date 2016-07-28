package ru.arturvasilov.stackexchangeclient.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.arturvasilov.stackexchangeclient.testutils.TestLocalRepository;
import ru.arturvasilov.stackexchangeclient.testutils.TestRemoteRepository;
import ru.arturvasilov.stackexchangeclient.testutils.service.AnswerServiceMock;
import ru.arturvasilov.stackexchangeclient.testutils.service.ApplicationServiceMock;
import ru.arturvasilov.stackexchangeclient.testutils.service.NotificationServiceMock;
import ru.arturvasilov.stackexchangeclient.testutils.service.QuestionServiceMock;
import ru.arturvasilov.stackexchangeclient.testutils.service.TagsServiceMock;
import ru.arturvasilov.stackexchangeclient.testutils.service.UserInfoServiceMock;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class RepositoryProviderTest {

    @Test
    public void testRemoteNotInited() throws Exception {
        assertNull(RepositoryProvider.provideRemoteRepository());
    }

    @Test
    public void testRemoteSetter() throws Exception {
        RemoteRepository repository = new TestRemoteRepository(new UserInfoServiceMock(), new QuestionServiceMock(),
                new AnswerServiceMock(), new TagsServiceMock(), new NotificationServiceMock(), new ApplicationServiceMock());
        RepositoryProvider.setRemoteRepository(repository);

        assertTrue(repository == RepositoryProvider.provideRemoteRepository());
    }

    @Test
    public void testLocalNotInited() throws Exception {
        assertNull(RepositoryProvider.provideLocalRepository());
    }

    @Test
    public void testLocalSetter() throws Exception {
        LocalRepository repository = new TestLocalRepository();
        RepositoryProvider.setLocalRepository(repository);

        assertTrue(repository == RepositoryProvider.provideLocalRepository());
    }
}
