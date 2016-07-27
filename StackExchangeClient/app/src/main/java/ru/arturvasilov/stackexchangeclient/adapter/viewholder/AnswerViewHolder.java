package ru.arturvasilov.stackexchangeclient.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.images.CircleTransform;
import ru.arturvasilov.stackexchangeclient.model.content.Answer;
import ru.arturvasilov.stackexchangeclient.utils.HtmlCompat;
import ru.arturvasilov.stackexchangeclient.utils.Views;

/**
 * @author Artur Vasilov
 */
public class AnswerViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mAuthorIcon;
    private final TextView mAuthorName;
    private final TextView mAnswerBody;
    private final View mAnsweredIcon;
    private final View mDivider;

    public AnswerViewHolder(View itemView) {
        super(itemView);

        mAuthorIcon = Views.findById(itemView, R.id.icon);
        mAuthorName = Views.findById(itemView, R.id.authorName);
        mAnswerBody = Views.findById(itemView, R.id.answerBody);
        mAnsweredIcon = Views.findById(itemView, R.id.answeredView);
        mDivider = Views.findById(itemView, R.id.divider);
    }

    public void bind(@NonNull Answer answer, boolean isLast) {
        Picasso.with(mAuthorIcon.getContext())
                .load(answer.getOwner().getProfileImage())
                .transform(new CircleTransform())
                .into(mAuthorIcon);

        mAuthorName.setText(answer.getOwner().getName());
        CharSequence body = HtmlCompat.fromHtml(answer.getBody());
        int maxLength = mAnswerBody.getResources().getInteger(R.integer.body_preview_length);
        if (body.length() > maxLength) {
            body = TextUtils.concat(body.subSequence(0, maxLength - 3), "...");
        }
        mAnswerBody.setText(body);
        mAnsweredIcon.setVisibility(answer.isAccepted() ? View.VISIBLE : View.INVISIBLE);
        mDivider.setVisibility(isLast ? View.GONE : View.VISIBLE);
    }
}
