package ru.arturvasilov.stackexchangeclient.view;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.model.content.Question;

/**
 * @author Artur Vasilov
 */
public interface QuestionsListView extends EmptyListView {

    void showQuestions(@NonNull List<Question> questions);

}
