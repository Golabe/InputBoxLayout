package top.golabe.kotlin.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class InputBoxLayout extends LinearLayout implements View.OnKeyListener, TextWatcher {
    private static final String TAG = "InputBoxLayout";
    private static final int INPUT_TYPE_NUMBER = 0x0001;
    private static final int INPUT_TYPE_TEXT = 0x0002;
    private static final int INPUT_TYPE_NUMBER_PASSWORD = 0x0003;
    private static final int INPUT_TYPE_TEXT_PASSWORD = 0x0004;
    private int inputType;
    private int boxNumber;
    private int textSize;
    private int textColor;
    private int boxBgNormal;
    private int boxBgFocus;
    private int boxSize;
    private int vPadding;
    private int hPadding;

    private List<EditText> mEditTextList = new ArrayList<>();
    private int currentPosition = 0;

    private OnCompleteListener onCompleteListener;

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
        invalidate();
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
        invalidate();
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    public int getBoxBgNormal() {
        return boxBgNormal;
    }

    public void setBoxBgNormal(int boxBgNormal) {
        this.boxBgNormal = boxBgNormal;
        invalidate();
    }

    public int getBoxBgFocus() {
        return boxBgFocus;
    }

    public void setBoxBgFocus(int boxBgFocus) {
        this.boxBgFocus = boxBgFocus;
        invalidate();
    }

    public int getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
        invalidate();
    }

    public int getvPadding() {
        return vPadding;
    }

    public void setvPadding(int vPadding) {
        this.vPadding = vPadding;
        invalidate();
    }

    public int gethPadding() {
        return hPadding;
    }

    public void sethPadding(int hPadding) {
        this.hPadding = hPadding;
        invalidate();
    }

    public InputBoxLayout(Context context) {
        this(context, null);
    }

    public InputBoxLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputBoxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        initAttrs(attrs);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int width = getWidth();

        int gap = (width - (boxSize) * boxNumber) / (boxNumber + 1);
        Log.d(TAG, "onLayout: " + gap);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            child.setVisibility(View.VISIBLE);
            int cWidth = child.getMeasuredWidth();
            int cHeight = child.getMeasuredHeight();


            int cl = (i + 1) * gap + (i * cWidth);
            int cr = cl + cWidth;
            int ct = vPadding;
            int cb = ct + cHeight;
            child.layout(cl, ct, cr, cb);


        }

    }

    private void init() {
        for (int i = 0; i < boxNumber; i++) {
            EditText editText = new EditText(getContext());
            LayoutParams params = new LayoutParams((int) boxSize, (int) boxSize);
            params.bottomMargin = vPadding;
            params.topMargin = vPadding;
            params.leftMargin = hPadding;
            params.rightMargin = hPadding;
            params.gravity = Gravity.CENTER;
            editText.setBackgroundResource(boxBgNormal);
            editText.setLayoutParams(params);
            editText.setTextColor(textColor);
            editText.setTextSize(textSize);
            editText.setGravity(Gravity.CENTER);
            switch (inputType) {
                case INPUT_TYPE_NUMBER:
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    break;
                case INPUT_TYPE_NUMBER_PASSWORD:
                    editText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    break;
                case INPUT_TYPE_TEXT:
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                    break;
                case INPUT_TYPE_TEXT_PASSWORD:
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
            }

            if (i == 0) {
                setBg(editText, true);
            } else setBg(editText, false);
            editText.setSingleLine(true);
            editText.setOnKeyListener(this);
            editText.setId(i);
            editText.setEms(1);
            editText.addTextChangedListener(this);
            mEditTextList.add(editText);
            addView(editText, i);
        }
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.InputBoxLayout);
            inputType = a.getInt(R.styleable.InputBoxLayout_input_type, INPUT_TYPE_NUMBER);
            boxNumber = a.getInt(R.styleable.InputBoxLayout_box_number, 4);
            textSize = a.getInt(R.styleable.InputBoxLayout_text_size, 16);
            textColor = a.getColor(R.styleable.InputBoxLayout_text_color, Color.BLACK);
            boxBgNormal = a.getResourceId(R.styleable.InputBoxLayout_box_bg_normal, R.drawable.box_bg_normal);
            boxBgFocus = a.getResourceId(R.styleable.InputBoxLayout_box_bg_focus, R.drawable.box_bg_fcous);
            boxSize = dp2px(a.getDimension(R.styleable.InputBoxLayout_box_size, 40));
            vPadding = dp2px(a.getDimension(R.styleable.InputBoxLayout_v_padding, 4));
            hPadding = dp2px(a.getDimension(R.styleable.InputBoxLayout_h_padding, 4));
            a.recycle();
        }
    }


    public int dp2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        EditText editText = (EditText) v;
        if (keyCode == KeyEvent.KEYCODE_DEL && editText.getText().length() == 0) {
            if (currentPosition != 0 && event.getAction() == KeyEvent.ACTION_DOWN) {
                mEditTextList.get(currentPosition).setText("");
                currentPosition--;
                mEditTextList.get(currentPosition).requestFocus();
                setBg(mEditTextList.get(currentPosition), true);
                setBg(mEditTextList.get(currentPosition + 1), false);
            }
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (start == 0 && count >= 1 && currentPosition != mEditTextList.size() -1) {
            currentPosition++;
            mEditTextList.get(currentPosition).requestFocus();
            setBg(mEditTextList.get(currentPosition), true);
            setBg(mEditTextList.get(currentPosition - 1), false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            focus();
            commit();
        }

    }

    private void setBg(EditText editText, boolean focus) {
        if (!focus) {
            editText.setBackgroundResource(boxBgNormal);
        } else if (focus) {
            editText.setBackgroundResource(boxBgFocus);
        }
    }


    @Override
    public void setEnabled(boolean enabled) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.setEnabled(enabled);
        }
    }

    private void commit() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean result = true;
        for (int i = 0 ;i < boxNumber; i++){
            EditText editText = (EditText) getChildAt(i);
            String content = editText.getText().toString();
            if ( content.length() == 0) {
                result = false;
                break;
            } else {
                stringBuilder.append(content);
            }

        }
        if (result){
            onCompleteListener.onComplete(stringBuilder.toString());
            setEnabled(false);
        }
    }


    private void focus() {
        EditText editText;
        for (int i = 0; i < getChildCount(); i++) {
            editText = (EditText) getChildAt(i);
            if (editText.getText().length() < 0) {
                editText.requestFocus();
                return;
            }

        }

    }

    public void reset() {
        if (mEditTextList != null) {
            mEditTextList.clear();
        }
        init();
        currentPosition = 0;
        invalidate();
    }
}
