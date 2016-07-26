package ru.arturvasilov.stackexchangeclient.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.arturvasilov.stackexchangeclient.api.ApiConstants;
import ru.arturvasilov.stackexchangeclient.presenter.QuestionsListPresenter;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;
import ru.arturvasilov.stackexchangeclient.view.QuestionsListView;

/**
 * @author Artur Vasilov
 */
public class QuestionsListFragment extends Fragment implements QuestionsListView {

    private static final String TAG_KEY = "tag_key";

    private QuestionsListPresenter mPresenter;

    @NonNull
    public static QuestionsListFragment create(@NonNull String tag) {
        QuestionsListFragment fragment = new QuestionsListFragment();
        Bundle args = new Bundle();
        args.putString(TAG_KEY, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tag = getArguments().getString(TAG_KEY);
        tag = TextUtils.isEmpty(tag) ? ApiConstants.TAG_ALL : tag;
        mPresenter = new QuestionsListPresenter(getActivity(), getActivity().getLoaderManager(), this, tag);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.init();
    }
}
