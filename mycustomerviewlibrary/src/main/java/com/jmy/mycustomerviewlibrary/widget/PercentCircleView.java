package com.jmy.mycustomerviewlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.jmy.mycustomerviewlibrary.R;

public class PercentCircleView extends View implements GestureDetector.OnGestureListener {
    private int arcColor;
    private int circleColor;
    private int textColor;
    private float textSize;
    private String text;
    private float startAngle;
    private float percent;

    private Paint circlePaint, arcPaint, textPaint;
    private RectF rectF;

    private float radius;
    private int centerXY;
    private int arcWidth;

    private GestureDetector gestureDetector;

    public PercentCircleView(Context context) {
        this(context, null);
    }

    public PercentCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, this);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.MyViewStyle);
        if (ta != null) {
            circleColor = ta.getColor(R.styleable.MyViewStyle_circleColor, Color.TRANSPARENT);
            arcColor = ta.getColor(R.styleable.MyViewStyle_arcColor, Color.TRANSPARENT);
            textColor = ta.getColor(R.styleable.MyViewStyle_textColor, Color.BLACK);
            textSize = ta.getDimension(R.styleable.MyViewStyle_textSize, 50);
            text = ta.getString(R.styleable.MyViewStyle_text);
            startAngle = ta.getInt(R.styleable.MyViewStyle_startAngle, 0);
            percent = ta.getFloat(R.styleable.MyViewStyle_percent, 1f);
            if (percent > 1) {
                percent = 1;
            }
            ta.recycle();
        }
    }

    private void initPaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);
        circlePaint.setStyle(Paint.Style.FILL);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(arcWidth);
        arcPaint.setColor(arcColor);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int length = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        rectF = new RectF(length * 0.1f, length * 0.1f, length * 0.9f, length * 0.9f);
        radius = length * 0.6f / 2;
        centerXY = length / 2;
        arcWidth = (int) (length * 0.2f / 2);
        initPaint();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerXY, centerXY, radius, circlePaint);
        canvas.drawArc(rectF, startAngle, 360 * percent, false, arcPaint);

        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        float startY = centerXY - fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(text, 0, text.length(), centerXY, startY, textPaint);
    }

    private float startX = 0;
    private float startY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                startX = event.getX();
                startY = event.getY();
                float tanValue;
                if (startX == centerXY && startY == centerXY) {
                    percent = 0;
                } else if (startX == centerXY) {
                    percent = startY > centerXY ? 0.25f : 0.75f;
                } else if (startY == centerXY) {
                    percent = startX > centerXY ? 0f : 0.5f;
                } else {
                    tanValue = (startY - centerXY) / (startX - centerXY);
                    percent = (float) (Math.atan(tanValue) / (Math.PI * 2));
                    if (percent < 0) {
                        percent = startY > centerXY ? (float) (percent + 0.5) : percent + 1;
                    } else {
                        percent = startX > centerXY ? percent : (float) (percent + 0.5);
                    }
                }
                invalidate();
                break;
        }
        return true;
    }

    /**
     * Notified when a tap occurs with the down {@link MotionEvent}
     * that triggered it. This will be triggered immediately for
     * every down event. All other events should be preceded by this.
     *
     * @param e The down motion event.
     */
    @Override
    public boolean onDown(MotionEvent e) {
        Log.e("test", "onDown");
        return false;
    }

    /**
     * The user has performed a down {@link MotionEvent} and not performed
     * a move or up yet. This event is commonly used to provide visual
     * feedback to the user to let them know that their action has been
     * recognized i.e. highlight an element.
     *
     * @param e The down motion event
     */
    @Override
    public void onShowPress(MotionEvent e) {
        Log.e("test", "onShowPress");
    }

    /**
     * Notified when a tap occurs with the up {@link MotionEvent}
     * that triggered it.
     *
     * @param e The up motion event that completed the first tap
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e("test", "onSingleTapUp");
        return false;
    }

    /**
     * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
     * current move {@link MotionEvent}. The distance in x and y is also supplied for
     * convenience.
     *
     * @param e1        The first down motion event that started the scrolling.
     * @param e2        The move motion event that triggered the current onScroll.
     * @param distanceX The distance along the X axis that has been scrolled since the last
     *                  call to onScroll. This is NOT the distance between {@code e1}
     *                  and {@code e2}.
     * @param distanceY The distance along the Y axis that has been scrolled since the last
     *                  call to onScroll. This is NOT the distance between {@code e1}
     *                  and {@code e2}.
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e("test", "onScroll");
        return false;
    }

    /**
     * Notified when a long press occurs with the initial on down {@link MotionEvent}
     * that trigged it.
     *
     * @param e The initial on down motion event that started the longpress.
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Log.e("test", "onLongPress");
    }

    /**
     * Notified of a fling event when it occurs with the initial on down {@link MotionEvent}
     * and the matching up {@link MotionEvent}. The calculated velocity is supplied along
     * the x and y axis in pixels per second.
     *
     * @param e1        The first down motion event that started the fling.
     * @param e2        The move motion event that triggered the current onFling.
     * @param velocityX The velocity of this fling measured in pixels per second
     *                  along the x axis.
     * @param velocityY The velocity of this fling measured in pixels per second
     *                  along the y axis.
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e("test", "onFling");
        return false;
    }
}
