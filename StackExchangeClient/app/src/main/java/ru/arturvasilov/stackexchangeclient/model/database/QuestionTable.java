package ru.arturvasilov.stackexchangeclient.model.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.app.GsonHolder;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.sqlite.table.BaseTable;
import ru.arturvasilov.stackexchangeclient.sqlite.table.Table;
import ru.arturvasilov.stackexchangeclient.sqlite.table.TableBuilder;

/**
 * @author Artur Vasilov
 */
public class QuestionTable extends BaseTable<Question> {

    public static final Table<Question> TABLE = new QuestionTable();

    public static final String QUESTION_ID = "question_id";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String OWNER = "owner";
    public static final String IS_ANSWERED = "is_answered";
    public static final String VIEW_COUNT = "view_count";
    public static final String SCORE = "score";
    public static final String TAG = "tag";

    @Override
    public void onCreate(@NonNull SQLiteDatabase database) {
        TableBuilder.create(this)
                .intColumn(QUESTION_ID)
                .stringColumn(TITLE)
                .stringColumn(LINK)
                .stringColumn(OWNER)
                .intColumn(IS_ANSWERED)
                .intColumn(VIEW_COUNT)
                .intColumn(SCORE)
                .stringColumn(TAG)
                .primaryKey(QUESTION_ID, TAG)
                .execute(database);
    }

    @Override
    public int getLastUpgradeVersion() {
        return 1;
    }

    @NonNull
    @Override
    public ContentValues toValues(@NonNull Question question) {
        ContentValues values = new ContentValues();
        values.put(QUESTION_ID, question.getQuestionId());
        values.put(TITLE, question.getTitle());
        values.put(LINK, question.getLink());
        values.put(OWNER, GsonHolder.getGson().toJson(question.getOwner()));
        values.put(IS_ANSWERED, question.isAnswered() ? 1 : 0);
        values.put(VIEW_COUNT, question.getViewCount());
        values.put(SCORE, question.getScore());
        values.put(TAG, question.getTag());
        return values;
    }

    @NonNull
    @Override
    public Question fromCursor(@NonNull Cursor cursor) {
        User owner = GsonHolder.getGson().fromJson(cursor.getString(cursor.getColumnIndex(OWNER)), User.class);

        Question question = new Question();
        question.setQuestionId(cursor.getInt(cursor.getColumnIndex(QUESTION_ID)));
        question.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        question.setLink(cursor.getString(cursor.getColumnIndex(LINK)));
        question.setOwner(owner);
        question.setAnswered(cursor.getInt(cursor.getColumnIndex(IS_ANSWERED)) > 0);
        question.setViewCount(cursor.getInt(cursor.getColumnIndex(VIEW_COUNT)));
        question.setScore(cursor.getInt(cursor.getColumnIndex(SCORE)));
        question.setTag(cursor.getString(cursor.getColumnIndex(TAG)));
        return question;
    }
}
