package ru.arturvasilov.stackexchangeclient.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.images.CircleTransform;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.utils.Views;

/**
 * @author Artur Vasilov
 */
public class QuestionItemViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mAuthorIcon;
    private final TextView mTitle;
    private final TextView mAuthorName;
    private final TextView mViewsCount;
    private final TextView mAnswersCount;
    private final View mAnsweredIcon;
    private final View mDivider;

    public QuestionItemViewHolder(View itemView) {
        super(itemView);

        mAuthorIcon = Views.findById(itemView, R.id.icon);
        mTitle = Views.findById(itemView, R.id.questionTitle);
        mAuthorName = Views.findById(itemView, R.id.questionAuthor);
        mViewsCount = Views.findById(itemView, R.id.viewsCount);
        mAnswersCount = Views.findById(itemView, R.id.answersCount);
        mAnsweredIcon = Views.findById(itemView, R.id.answeredQuestion);
        mDivider = Views.findById(itemView, R.id.divider);
    }

    public void bind(@NonNull Question question, boolean isLast) {
        Picasso.with(mAuthorIcon.getContext())
                .load(question.getOwner().getProfileImage())
                .transform(new CircleTransform())
                .into(mAuthorIcon);

        mTitle.setText(question.getTitle());
        mAuthorName.setText(question.getOwner().getName());
        mViewsCount.setText(String.valueOf(question.getViewCount()));
        mAnswersCount.setText(String.valueOf(question.getAnswerCount()));
        mAnsweredIcon.setVisibility(question.isAnswered() ? View.VISIBLE : View.INVISIBLE);
        mDivider.setVisibility(isLast ? View.GONE : View.VISIBLE);
    }

}
