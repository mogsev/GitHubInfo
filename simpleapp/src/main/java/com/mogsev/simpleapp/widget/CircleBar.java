package com.mogsev.simpleapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class CircleBar extends View {
    private static final String TAG = CircleBar.class.getSimpleName();

    private Paint mPaint;
    private RectF mRect;

    private float mAngle;
    final int mStrokeWidth = 10;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ONE_QUARTER, TWO_QUARTER, THREE_QUARTER, FULL})
    public @interface PartOfCircle {
    }

    public static final int ONE_QUARTER = 90;
    public static final int TWO_QUARTER = 180;
    public static final int THREE_QUARTER = 270;
    public static final int FULL = 360;

    public CircleBar(Context context) {
        super(context);
        if (!isInEditMode()) initialize();
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) initialize();
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) initialize();
    }

    private void initialize() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        //Circle color
        mPaint.setColor(Color.RED);

        //mRect = new RectF(strokeWidth, strokeWidth, 200 + strokeWidth, 200 + strokeWidth);

        //Initial Angle (optional, it can be zero)
        mAngle = 120;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int height = getMeasuredHeight();
        final int width = getMeasuredWidth();
        Log.i(TAG, "height: " + height + " width: " + width);
        int right = width + mStrokeWidth;
        int bottom = height + mStrokeWidth;

        mRect = new RectF(mStrokeWidth, mStrokeWidth, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRect, ONE_QUARTER, mAngle, false, mPaint);
    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    public static class CircleBarAngleAnimation extends Animation {

        private CircleBar mCircleBar;

        private float mOldAngle;
        private float mNewAngle;

        public CircleBarAngleAnimation(CircleBar circle, int newAngle) {
            mOldAngle = circle.getAngle();
            mNewAngle = newAngle;
            mCircleBar = circle;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            float angle = mOldAngle + ((mNewAngle - mOldAngle) * interpolatedTime);

            mCircleBar.setAngle(angle);
            mCircleBar.requestLayout();
        }
    }
}
