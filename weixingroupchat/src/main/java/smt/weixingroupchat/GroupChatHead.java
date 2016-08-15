package smt.weixingroupchat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信头像
 * <p/>
 * Created by Administrator on 2016/8/11.
 */
public class GroupChatHead extends View {


    private Paint mPaint;

    private Context mContext;
    /**
     * 头像集合
     */
    private List<Bitmap> mBitmapList;
    /**
     * 控件的宽
     */
    private int mWidth;
    /**
     * 控件的高
     */
    private int mHeigth;
    /**
     * 每一个小头像的宽度
     */
    private int mOneWidth;
    /**
     * 每一个小头像的高度
     */
    private int mOneHeigth;

    public GroupChatHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    public GroupChatHead(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupChatHead(Context context) {
        this(context, null);
    }


    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        switch (mBitmapList.size()) {
            case 1:
                mOneWidth = mWidth;
                mOneHeigth = mHeigth;
                break;
            case 2:
            case 3:
            case 4:
                mOneWidth = mWidth / 2;
                mOneHeigth = mHeigth / 2;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                mOneWidth = mWidth / 3;
                mOneHeigth = mHeigth / 3;
                break;
            default:
                mOneWidth = mWidth / 3;
                mOneHeigth = mHeigth / 3;
                break;
        }

        mBitmapList = zoomImg(mBitmapList, mOneWidth, mOneHeigth);
        if (mBitmapList != null && mBitmapList.size() > 0) {
            Bitmap bitmap = getBitmap(mBitmapList);
            canvas.drawBitmap(bitmap, 0, 0, mPaint);
        }
    }
    /**
     * 组合图片
     *
     * @param arr
     * @return
     */
    public Bitmap getBitmap(List<Bitmap> arr) {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeigth,
                Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        switch (arr.size()) {
            case 1:
            case 2:
                drawBitmap12(canvas, arr);
                break;
            case 3:
            case 4:
                drawBitmap34(canvas, arr);
                break;
            case 5:
            case 6:
                drawBitmap56(canvas, arr);
                break;
            case 7:
            case 8:
            case 9:
                drawBitmap789(canvas, arr);
                break;
            default:
                drawBitmap789(canvas, arr);
                break;
        }

        return bitmap;
    }

    /**
     * @param canvas
     * @param arr
     */
    public void drawBitmap12(Canvas canvas, List<Bitmap> arr) {
        if (arr.size() == 1) {
            //1
            canvas.drawBitmap(arr.get(0), 0, 0, null);
        } else {
            //2
            canvas.drawBitmap(arr.get(0), 0, mOneHeigth / 2, null);
            canvas.drawBitmap(arr.get(1), mOneWidth, mOneHeigth / 2, null);
        }
    }

    /**
     * @param canvas
     * @param arr
     */
    public void drawBitmap34(Canvas canvas, List<Bitmap> arr) {
        int i = 0;
        if (arr.size() == 3) {
            //3
            i = 1;
            canvas.drawBitmap(arr.get(0), mOneWidth / 2, 0, null);//1
        } else {
            //4
            i = 0;
            canvas.drawBitmap(arr.get(0), 0, 0, null);//1
            canvas.drawBitmap(arr.get(1), mOneWidth, 0, null);//2

        }
        canvas.drawBitmap(arr.get(2 - i), 0, mOneHeigth, null);
        canvas.drawBitmap(arr.get(3 - i), mOneWidth, mOneHeigth, null);

    }

    /**
     * @param canvas
     * @param arr
     */
    public void drawBitmap56(Canvas canvas, List<Bitmap> arr) {
        int i = 0;
        if (arr.size() == 5) {
            //5
            i = 1;
            canvas.drawBitmap(arr.get(0), mOneWidth / 2, mOneHeigth / 2, null);//1
            canvas.drawBitmap(arr.get(1), mOneWidth / 2 + mOneWidth, mOneHeigth / 2, null);//2
        } else {
            //6
            i = 0;
            canvas.drawBitmap(arr.get(0), 0, mOneHeigth / 2, null);//1
            canvas.drawBitmap(arr.get(1), mOneWidth, mOneHeigth / 2, null);//2
            canvas.drawBitmap(arr.get(2), mOneWidth * 2, mOneHeigth / 2, null);//3
        }
        //下部分
        canvas.drawBitmap(arr.get(3 - i), 0, mOneHeigth / 2 + mOneHeigth, null);//1
        canvas.drawBitmap(arr.get(4 - i), mOneWidth, mOneHeigth / 2 + mOneHeigth, null);//2
        canvas.drawBitmap(arr.get(5 - i), mOneWidth * 2, mOneHeigth / 2 + mOneHeigth, null);//3


    }

    /**
     * @param canvas
     * @param arr
     */
    public void drawBitmap789(Canvas canvas, List<Bitmap> arr) {
        //上面部分
        int i = 0;
        if (arr.size() == 7) {
            i = 2;
            canvas.drawBitmap(arr.get(0), mOneWidth, 0, null);//1
        } else if (arr.size() == 8) {
            i = 1;
            canvas.drawBitmap(arr.get(0), mOneWidth / 2, 0, null);//1
            canvas.drawBitmap(arr.get(1), mOneWidth + mOneHeigth / 2, 0, null);//2
        } else {
            canvas.drawBitmap(arr.get(0), 0, 0, null);//1
            canvas.drawBitmap(arr.get(1), mOneWidth, 0, null);//2
            canvas.drawBitmap(arr.get(2), mOneWidth * 2, 0, null);//3
        }
        //中间部分
        canvas.drawBitmap(arr.get(3 - i), 0, mOneHeigth, null);//4
        canvas.drawBitmap(arr.get(4 - i), mOneWidth, mOneHeigth, null);//5
        canvas.drawBitmap(arr.get(5 - i), mOneWidth * 2, mOneHeigth, null);//6
        //下面部分
        canvas.drawBitmap(arr.get(6 - i), 0, mOneHeigth * 2, null);//4
        canvas.drawBitmap(arr.get(7 - i), mOneWidth, mOneHeigth * 2, null);//5
        canvas.drawBitmap(arr.get(8 - i), mOneWidth * 2, mOneHeigth * 2, null);//6
    }

    /**
     * 处理图片
     *
     * @param mBitmapList 所要转换的bitmap
     * @param newWidth    新的宽
     * @param newHeight   新的高
     * @return 指定宽高的bitmap
     */
    public List<Bitmap> zoomImg(List<Bitmap> mBitmapList, int newWidth, int newHeight) {
        if (mBitmapList == null || mBitmapList.size() == 0) {
            return null;
        }

        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        for (int i = 0; i < mBitmapList.size(); i++) {

            // 获得图片的宽高
            int width = mBitmapList.get(i).getWidth();
            int height = mBitmapList.get(i).getHeight();
            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // 得到新的图片
            Bitmap newbm = Bitmap.createBitmap(mBitmapList.get(i), 0, 0, width, height, matrix, true);
            bitmaps.add(newbm);
        }
        return bitmaps;
    }


    /**
     * 设置图片集
     *
     * @param bm
     */
    public void setBitmaps(List<Bitmap> bm) {
        mBitmapList = bm;
        //重绘
        invalidate();
    }

}
