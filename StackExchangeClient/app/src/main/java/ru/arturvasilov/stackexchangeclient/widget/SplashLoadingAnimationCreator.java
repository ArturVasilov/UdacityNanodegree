package ru.arturvasilov.stackexchangeclient.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * @author Nikita Simonov
 */
public final class SplashLoadingAnimationCreator {

    private static final float START_ALPHA = 0.35f;
    private static final float END_ALPHA = 0.1f;

    private static final float START_SCALE = 0.8f;
    private static final float END_SCALE = 1.0f;

    private static final long FULL_EXPANSION_DURATION = 1000;
    private static final long FULL_CONSTRICTION_DURATION = 1000;

    private static final long FIRST_CIRCLE_TIME = 500;
    private static final long SECOND_CIRCLE_TIME = 700;
    private static final long THIRD_CIRCLE_TIME = 900;

    public static Animator createFullAnimator(final View view1, final View view2, final View view3) {

        Animator fullExpansionAnimator = fullExpansionAnimator(view1, view2, view3);
        Animator fullConstrictionAnimator = fullConstrictionAnimator(view1, view2, view3);

        AnimatorSet fullAnimator = new AnimatorSet();
        fullAnimator.playSequentially(fullExpansionAnimator, fullConstrictionAnimator);
        fullAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return fullAnimator;
    }

    private static Animator fullExpansionAnimator(View view1, View view2, View view3) {
        Animator expansionAnimator1 = createExpansionAnimator(view1, FIRST_CIRCLE_TIME);
        Animator expansionAnimator2 = createExpansionAnimator(view2, SECOND_CIRCLE_TIME);
        Animator expansionAnimator3 = createExpansionAnimator(view3, THIRD_CIRCLE_TIME);
        Animator time = createTimeAnimator(FULL_EXPANSION_DURATION);

        AnimatorSet fullExpansionAnimator = new AnimatorSet();
        fullExpansionAnimator.playTogether(expansionAnimator1, expansionAnimator2, expansionAnimator3, time);

        return fullExpansionAnimator;
    }

    private static Animator fullConstrictionAnimator(View view1, View view2, View view3) {
        Animator anim1 = createConstrictionAnimator(view1, FIRST_CIRCLE_TIME);
        Animator anim2 = createConstrictionAnimator(view2, SECOND_CIRCLE_TIME);
        Animator anim3 = createConstrictionAnimator(view3, THIRD_CIRCLE_TIME);
        Animator time = createTimeAnimator(FULL_CONSTRICTION_DURATION);

        AnimatorSet fullConstrictionAnimator = new AnimatorSet();
        fullConstrictionAnimator.playTogether(anim1, anim2, anim3, time);

        return fullConstrictionAnimator;
    }

    private static Animator createExpansionAnimator(View view, long duration) {
        Animator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, START_ALPHA, END_ALPHA);
        alphaAnimator.setInterpolator(new LinearInterpolator());

        Animator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, START_SCALE, END_SCALE);
        scaleXAnimator.setInterpolator(new DecelerateInterpolator());

        Animator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, START_SCALE, END_SCALE);
        scaleYAnimator.setInterpolator(new DecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        animatorSet.setDuration(duration);

        return animatorSet;
    }

    private static Animator createConstrictionAnimator(View view, long duration) {
        Animator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, END_ALPHA, START_ALPHA);
        alphaAnimator.setInterpolator(new LinearInterpolator());

        Animator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, END_SCALE, START_SCALE);
        scaleXAnimator.setInterpolator(new DecelerateInterpolator());

        Animator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, END_SCALE, START_SCALE);
        scaleYAnimator.setInterpolator(new DecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        animatorSet.setDuration(duration);

        return animatorSet;
    }

    private static Animator createTimeAnimator(long duration) {
        Animator timeAnimator = ObjectAnimator.ofInt(1);
        timeAnimator.setDuration(duration);

        return timeAnimator;
    }
}
