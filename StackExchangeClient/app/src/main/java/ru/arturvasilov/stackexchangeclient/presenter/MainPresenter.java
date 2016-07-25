package ru.arturvasilov.stackexchangeclient.presenter;

import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.model.database.UserTable;
import ru.arturvasilov.stackexchangeclient.rx.StubAction;
import ru.arturvasilov.stackexchangeclient.view.MainView;

/**
 * @author Artur Vasilov
 */
public class MainPresenter {

    private final MainView mView;

    public MainPresenter(@NonNull MainView view) {
        mView = view;
    }

    public void init() {
        UserTable.getCurrentUser()
                .subscribe(user -> {
                    mView.showUserImage(user.getProfileImage());
                    mView.showUserName(user.getName());
                }, new StubAction<>());

        mView.addTab(R.string.all);
        mView.addTab(R.string.my);

        //TODO : load tabs from favourite
    }
}
