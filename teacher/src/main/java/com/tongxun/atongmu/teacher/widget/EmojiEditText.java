package com.tongxun.atongmu.teacher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Anro on 2016/8/3.
 */
public class EmojiEditText extends EditText {
    private int mEmojiconSize;

    public EmojiEditText(Context context) {
        this(context, null);
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mEmojiconSize = (int) getTextSize()+20;
        setFocusable(true);
        setFocusableInTouchMode(true);
        findFocus();
        setText(getText());
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        EmojiHandler.addEmojis(getContext(), getText(), mEmojiconSize);
    }



}
