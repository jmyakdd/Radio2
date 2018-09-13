package com.jmy.mycustomerviewlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jmy.mycustomerviewlibrary.R;

public class MyTitleEditText extends LinearLayout {
    private Context context;
    private TextView title;
    private EditText edittext;

    private String titleStr;
    private int titleColor;
    private float titleSize;
    private String textStr;
    private int textColor;
    private float textSize;
    private String hint;
    private int hintColor;

    public MyTitleEditText(Context context) {
        this(context, null);
    }

    public MyTitleEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyTitleEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MyTitleEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTitleEditText);
        if (array != null) {
            titleStr = array.getString(R.styleable.MyTitleEditText_title);
            titleColor = array.getColor(R.styleable.MyTitleEditText_titleColor, Color.BLACK);
            titleSize = array.getDimension(R.styleable.MyTitleEditText_titleSize, 20f);
            textStr = array.getString(R.styleable.MyTitleEditText_text);
            textColor = array.getColor(R.styleable.MyTitleEditText_textColor, Color.BLACK);
            textSize = array.getDimension(R.styleable.MyTitleEditText_textSize, 20f);
            hint = array.getString(R.styleable.MyTitleEditText_hint);
            hintColor = array.getColor(R.styleable.MyTitleEditText_hintColor, Color.GRAY);
        }
        array.recycle();
        LayoutInflater.from(context).inflate(R.layout.my_title_edittext, this, true);
        title = findViewById(R.id.title);
        edittext = findViewById(R.id.edittext);
        title.setText(titleStr);
        title.setTextColor(titleColor);
        title.setTextSize(textSize);

        edittext.setText(textStr);
        edittext.setTextColor(textColor);
        edittext.setTextSize(textSize);
        edittext.setHint(hint);
        edittext.setHintTextColor(hintColor);
    }
}
