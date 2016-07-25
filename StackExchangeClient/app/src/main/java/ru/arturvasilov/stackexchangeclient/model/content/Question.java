package ru.arturvasilov.stackexchangeclient.model.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.stackexchangeclient.utils.TextUtils;

/**
 * @author Artur Vasilov
 */
public class Question {

    @SerializedName("question_id")
    private int mQuestionId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("link")
    private String mLink;

    @SerializedName("tags")
    private List<String> mTags;

    @SerializedName("owner")
    private User mOwner;

    @SerializedName("is_answered")
    private boolean mIsAnswered;

    @SerializedName("view_count")
    private int mViewCount;

    @SerializedName("answer_count")
    private int mAnswerCount;

    @SerializedName("score")
    private int mScore;

    private String mTag;

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@NonNull String title) {
        mTitle = title;
    }

    @NonNull
    public String getLink() {
        return mLink;
    }

    public void setLink(@NonNull String link) {
        mLink = link;
    }

    @NonNull
    public List<String> getTags() {
        if (mTags == null) {
            mTags = new ArrayList<>();
        }
        return mTags;
    }

    public void setTags(@NonNull List<String> tags) {
        mTags = tags;
    }

    @NonNull
    public User getOwner() {
        return mOwner;
    }

    public void setOwner(@NonNull User owner) {
        mOwner = owner;
    }

    public boolean isAnswered() {
        return mIsAnswered;
    }

    public void setAnswered(boolean answered) {
        mIsAnswered = answered;
    }

    public int getViewCount() {
        return mViewCount;
    }

    public void setViewCount(int viewCount) {
        mViewCount = viewCount;
    }

    public int getAnswerCount() {
        return mAnswerCount;
    }

    public void setAnswerCount(int answerCount) {
        mAnswerCount = answerCount;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    @NonNull
    public String getTag() {
        if (TextUtils.isEmpty(mTag)) {
            mTag = "";
        }
        return mTag;
    }

    public void setTag(@NonNull String tag) {
        mTag = tag;
    }
}
