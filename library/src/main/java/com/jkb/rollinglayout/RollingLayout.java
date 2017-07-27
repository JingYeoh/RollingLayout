package com.jkb.rollinglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ViewFlipper;

/**
 * A view that can rolling automatic within child views list.
 * come from the {@link BaseAdapter} associated with this view.
 * <p>
 * Created by yangjing on 17-7-21.
 */

public class RollingLayout extends ViewFlipper implements RollingLayoutAction {

    private static final String TAG = "RollingLayout";
    //attrs
    @Orientation
    private int mOrientation;
    private int mEachTime;
    private int mPauseTime;

    //data

    //listener
    private OnRollingChangedListener onRollingChangedListener;
    private OnRollingItemClickListener onRollingItemClickListener;


    public RollingLayout(@NonNull Context context) {
        this(context, null);
    }

    public RollingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    /**
     * Initialize the view's attributes.
     */
    private void initAttrs(AttributeSet attrs) {
        //Initialize the attributes from layout.
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RollingLayout);
        mOrientation = ta.getInt(R.styleable.RollingLayout_rolling_orientation, DOWN_UP);
        mEachTime = ta.getInteger(R.styleable.RollingLayout_rolling_eachTime, 500);
        mPauseTime = ta.getInteger(R.styleable.RollingLayout_rolling_pause, 1000);
        ta.recycle();

        setRollingPauseTime(mPauseTime);
        setRollingEachTime(mEachTime);
        setRollingOrientation(mOrientation);
    }

    @Override
    public void setRollingOrientation(@Orientation int orientation) {
        mOrientation = orientation;
        switch (orientation) {
            case UP_DOWN:
                setInAnimation(getAnimator(R.anim.slide_up_to_down_in));
                setOutAnimation(getAnimator(R.anim.slide_up_to_down_out));
                break;
            case DOWN_UP:
                setInAnimation(getAnimator(R.anim.slide_down_to_up_in));
                setOutAnimation(getAnimator(R.anim.slide_down_to_up_out));
                break;
            case LEFT_RIGHT:
                setInAnimation(getAnimator(R.anim.slide_left_to_right_in));
                setOutAnimation(getAnimator(R.anim.slide_left_to_right_out));
                break;
            case RIGHT_LEFT:
                setInAnimation(getAnimator(R.anim.slide_right_to_left_in));
                setOutAnimation(getAnimator(R.anim.slide_right_to_left_out));
                break;
        }
    }

    @Override
    public void setRollingEachTime(int time) {
        mEachTime = time;
    }

    @Override
    public void setRollingPauseTime(int time) {
        mPauseTime = time;
        setFlipInterval(time);
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        //TODO:you should use observer pattern instead clearViews.
        removeAllViews();
        //add the views of adapter
        for (int i = 0; i < adapter.getCount(); i++) {
            addView(adapter.getView(i, null, this));
        }
        requestLayout();
    }

    @Override
    public void startRolling() {
        startFlipping();
    }

    @Override
    public void stopRolling() {
        stopFlipping();
    }

    @Override
    public void addOnRollingChangedListener(@NonNull OnRollingChangedListener onRollingChangedListener) {
        this.onRollingChangedListener = onRollingChangedListener;
    }

    /**
     * return Animator and set duration time.
     *
     * @param animId the resource id of animator.
     * @return {@link Animation}
     */
    private Animation getAnimator(@AnimRes int animId) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), animId);
        animation.setDuration(mEachTime);
        return animation;
    }
}
