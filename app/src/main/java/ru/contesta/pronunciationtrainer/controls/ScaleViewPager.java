package ru.contesta.pronunciationtrainer.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;
import android.content.res.TypedArray;
import android.util.SparseArray;


import java.util.ArrayList;
import java.util.Collections;

import ru.contesta.pronunciationtrainer.R;

public class ScaleViewPager extends ViewPager {

    private static final String TAG = "ScaleViewPager";
    private ArrayList<Integer> childCenterXAbs = new ArrayList<>();
    private SparseArray<Integer> childIndex = new SparseArray<>();

    private float mScaleMax = 1.0f;
    private float mScaleMin = 0.914f;
    private float mCoverWidth = 40f;

    private int mCurrentPagePosition = 0;

    public float SmallScaleFactor = 1f;
    public float SmallAlphaFactor = 1f;

    public float BaseShadow = 0.0f;
    public float MinShadow = BaseShadow * SmallScaleFactor;

    // public float PageMargin = BaseShadow;
    // public float OtherPagesWidth = 0.0f;

    public ScaleViewPager(Context context) {
        super(context);
        init(context, null);
    }

    public ScaleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleViewPager);
            mScaleMax = typedArray.getFloat(R.styleable.ScaleViewPager_svp_maxScale, 1f);
            mScaleMin = typedArray.getFloat(R.styleable.ScaleViewPager_svp_minScale, 0.914f);
            mCoverWidth = typedArray.getDimension(R.styleable.ScaleViewPager_svp_coverWidth, 40f);
            typedArray.recycle();
        }

        // setChildrenDrawingOrderEnabledCompat(true) is called by default; makes getChildDrawingOrder work
        setPageTransformer(true, new SPageTransformer());
        setClipToPadding(false);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            boolean wrapHeight = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST;
            if (wrapHeight) {
                View child = getChildAt(mCurrentPagePosition);
                if (child != null) {
                    child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    int h = child.getMeasuredHeight();

                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void reMeasureCurrentPage(int position) {
        mCurrentPagePosition = position;
        requestLayout();
    }

    /**
     * Set the left and right reduction factor
     *
     * @param scaleMin
     */
    public void setMinScale(float scaleMin) {
        mScaleMin = scaleMin;
    }

    /**
     * Set the amplification factor in the middle
     *
     * @param scaleMax
     */
    public void setMaxScale(float scaleMax) {
        mScaleMax = scaleMax;
    }

    /**
     * Set the size of the overlap
     *
     * @param coverWidth
     */
    public void setCoverWidth(float coverWidth) {
        mCoverWidth = coverWidth;
    }

    /**
     * @param childCount
     * @param n
     * @return Drawing index of child at position n
     */
    @Override
    protected int getChildDrawingOrder(int childCount, int n) {
        if (n == 0 || childIndex.size() != childCount) {
            childCenterXAbs.clear();
            childIndex.clear();
            int viewCenterX = getViewCenterX(this);
            for (int i = 0; i < childCount; ++i) {
                int indexAbs = Math.abs(viewCenterX - getViewCenterX(getChildAt(i)));

                // The two distances are the same, and the later one does self-increment, thus keeping the abs different
                if (childIndex.get(indexAbs) != null) {
                    ++indexAbs;
                }
                childCenterXAbs.add(indexAbs);
                childIndex.append(indexAbs, i);
            }
            Collections.sort(childCenterXAbs);// 1,0,2  0,1,2
        }
        // The item is a little further from the center point, just draw it first (the nearest is the middle enlarged item,the last draw)
        return childIndex.get(childCenterXAbs.get(childCount - 1 - n));
    }

    private int getViewCenterX(View view) {
        int[] array = new int[2];
        view.getLocationOnScreen(array);
        return array[0] + view.getWidth() / 2;
    }

    class SPageTransformer implements ViewPager.PageTransformer {

        private float reduceX = 0.0f;
        private float itemWidth = 0;
        private float offsetPosition = 0f;

        @Override
        public void transformPage(View view, float position) {
            if (offsetPosition == 0f) {
                float paddingLeft = ScaleViewPager.this.getPaddingLeft();
                float paddingRight = ScaleViewPager.this.getPaddingRight();
                float width = ScaleViewPager.this.getMeasuredWidth();
                offsetPosition = paddingLeft / (width - paddingLeft - paddingRight);
            }
            float currentPos = position - offsetPosition;
            if (itemWidth == 0) {
                itemWidth = view.getWidth();
                // Half the size of x that decreases due to the reduction of the left and right sides
                reduceX = (2.0f - mScaleMax - mScaleMin) * itemWidth / 2.0f;
            }
            if (currentPos <= -1.0f) {
                view.setTranslationX(reduceX + mCoverWidth);
                view.setScaleX(mScaleMin);
                view.setScaleY(mScaleMin);
            } else if (currentPos <= 1.0) {
                float scale = (mScaleMax - mScaleMin) * Math.abs(1.0f - Math.abs(currentPos));
                float translationX = currentPos * -reduceX;

                // Critical in the middle of the two views, then the two views in the same layer, the left view needs to move the value covered in the positive direction of the X-axis
                if (currentPos <= -0.5) {
                    view.setTranslationX(translationX + mCoverWidth * Math.abs(Math.abs(currentPos) - 0.5f) / 0.5f);
                } else if (currentPos <= 0.0f) {
                    view.setTranslationX(translationX);
                } else if (currentPos >= 0.5) {
                    // Critical in the middle of the two views, then the two views in the same layer
                    view.setTranslationX(translationX - mCoverWidth * Math.abs(Math.abs(currentPos) - 0.5f) / 0.5f);
                } else {
                    view.setTranslationX(translationX);
                }
                view.setScaleX(scale + mScaleMin);
                view.setScaleY(scale + mScaleMin);
            } else {
                view.setScaleX(mScaleMin);
                view.setScaleY(mScaleMin);
                view.setTranslationX(-reduceX - mCoverWidth);
            }

        }
    }

}
