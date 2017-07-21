package com.jkb.rollinglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.lang.ref.WeakReference;

/**
 * A view that can rolling automatic within child views list.
 * come from the {@link BaseAdapter} associated with this view.
 * <p>
 * Created by yangjing on 17-7-21.
 */

public class RollingLayout extends FrameLayout implements RollingLayoutAction {

    private static final String TAG = "RollingLayout";
    //attrs
    private int mOrientation;
    private int mEachTime;
    private int mPauseTime;

    //data
    private static final int WHAT_ROLLING_NEXT = 1001;
    private static final int WHAT_REQUEST_LAYOUT = 1002;
    private int mCurrentPosition = 0;
    private RollingHandler rollingHandler;
    private Scroller mScroller;


    public RollingLayout(@NonNull Context context) {
        this(context, null);
    }

    public RollingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RollingLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        mScroller = new Scroller(getContext());
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
    }

    @Override
    public void setRollingOrientation(@Orientation int orientation) {
        mOrientation = orientation;
        postInvalidate();
    }

    @Override
    public void setRollingEachTime(int time) {
        mEachTime = time;
        postInvalidate();
    }

    @Override
    public void setRollingPauseTime(int time) {
        mPauseTime = time;
        postInvalidate();
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
        mCurrentPosition = 0;
        if (getChildCount() == 0) {
            throw new RuntimeException("the RollingLayout must host one child view at least");
        }
        if (rollingHandler == null) {
            rollingHandler = new RollingHandler(this);
        }
        rollingHandler.sendEmptyMessageDelayed(WHAT_ROLLING_NEXT, mPauseTime);
    }

    @Override
    public void stopRolling() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //measure the view's width.
        int widthResult = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            widthResult = widthSize;
        } else {
            widthResult = getMaxChildViewWidth();
        }
        //measure the view's height.
        int heightResult = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            heightResult = heightSize;
        } else {
            heightResult = getMaxChildViewHeight();
        }

        setMeasuredDimension(widthResult, heightResult);
    }

    /**
     * traverse the child views and return the max width.
     *
     * @return max width of child view.
     */
    public int getMaxChildViewWidth() {
        int maxWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int width = childView.getMeasuredWidth()
                    + childView.getPaddingLeft() + childView.getPaddingRight();
            maxWidth = width > maxWidth ? width : maxWidth;
        }
        return maxWidth;
    }

    /**
     * traverse the child views and return the max height.
     *
     * @return max height of child view.
     */
    public int getMaxChildViewHeight() {
        int maxHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int height = childView.getMeasuredHeight() +
                    +childView.getPaddingTop() + childView.getPaddingBottom();
            maxHeight = height > maxHeight ? height : maxHeight;
        }
        return maxHeight;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int viewHeight = getMeasuredHeight();
        int viewWidth = getMeasuredWidth();

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int currentChildHeight = i * getMeasuredHeight();
            int currentChildWidth = i * getMeasuredWidth();

            int childLeft;
            int childRight;
            int childTop;
            int childBottom;

            //make child view support padding attributes.
            int childHeight = childView.getMeasuredHeight()
                    + childView.getPaddingTop() + childView.getPaddingBottom();
            int childWidth = childView.getMeasuredWidth()
                    + childView.getPaddingLeft() + childView.getPaddingRight();

            switch (mOrientation) {
                case UP_DOWN:
                case DOWN_UP:
                    childTop = currentChildHeight + (viewHeight - childHeight) / 2;
                    childBottom = childTop + childHeight;
                    childView.layout(getPaddingLeft(), childTop, viewWidth - getPaddingRight(), childBottom);
                    break;
                case LEFT_RIGHT:
                case RIGHT_LEFT:
                    childLeft = currentChildWidth + (viewWidth - childWidth) / 2;
                    childRight = childLeft + childWidth;
                    childView.layout(childLeft, getPaddingTop(), childRight, viewHeight - getPaddingBottom());
                    break;
            }
        }

        if (rollingHandler != null) {
            rollingHandler.sendEmptyMessageDelayed(WHAT_ROLLING_NEXT, mPauseTime);
        }
    }

    private void smoothScrollTo(int destX, int destY) {
        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        mScroller.startScroll(0, scrollY, destX, deltaY, mEachTime);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        } else {
            if (mCurrentPosition == getChildCount()) {
//                rollingHandler.sendEmptyMessage(WHAT_REQUEST_LAYOUT);
            } else {
                rollingHandler.sendEmptyMessageDelayed(WHAT_ROLLING_NEXT, mPauseTime);
            }
        }
    }

    /**
     * Inside handler for loop rolling and keep out of leaks.
     */
    private static class RollingHandler extends Handler {
        private final WeakReference<RollingLayout> mRollingLayout;

        RollingHandler(RollingLayout rollingLayout) {
            mRollingLayout = new WeakReference<>(rollingLayout);
        }

        @Override
        public void handleMessage(Message msg) {
            RollingLayout rollingLayout = mRollingLayout.get();
            if (rollingLayout == null) return;
            switch (msg.what) {
                case WHAT_REQUEST_LAYOUT:
                    /*rollingLayout.mCurrentPosition = 0;
                    rollingLayout.requestLayout();*/
                    break;
                case WHAT_ROLLING_NEXT:
                    rollingLayout.mCurrentPosition++;
                    rollingLayout.mCurrentPosition %= rollingLayout.getChildCount();
                    rollingLayout.smoothScrollTo(0, rollingLayout.mCurrentPosition * rollingLayout.getMeasuredHeight());
                    break;
            }
        }
    }
}
