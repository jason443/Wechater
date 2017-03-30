package com.jason.wechater.widght;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jason on 2017/3/29.
 */

public class MyDrawView extends View {

    private static final String TAG = MyDrawView.class.getSimpleName();
    private Paint mPaint;
    private Canvas mCanvas;
    private int view_width=0;//屏幕的宽度
    private int view_height=0;//屏幕的高度
    private Bitmap cacheBitmap=null;//定义一个内存中的图片，该图片将作为缓冲区

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mCanvas = new Canvas();
        view_width=context.getResources().getDisplayMetrics().widthPixels;//获取屏幕宽度
        view_height=context.getResources().getDisplayMetrics().heightPixels;//获取屏幕高度
        //创建一个与该View相同大小的缓存区
        cacheBitmap=Bitmap.createBitmap(view_width,view_height, Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(cacheBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawBitmap(cacheBitmap,0,0,paint);
     //   drawCircular(200,200,150,3,Color.RED);
      //  drawLine(20,20,300,600,8,Color.RED);
     //   drawPicture(0,0,"");
      //  setBBackground(Color.YELLOW);
    }

    public void drawCircular(int x, int y,int r, int w, int color) {
        mPaint.setStrokeWidth(w);
        mPaint.setColor(color);
        mCanvas.drawCircle(x,y,r,mPaint);
        invalidate();
    }

    public void drawLine(int x1,int y1, int x2, int y2, int w, int color) {
        mPaint.setColor(color);
        mPaint.setStrokeWidth(w);
        mCanvas.drawLine(x1,y1,x2,y2,mPaint);
        invalidate();
    }

    public void drawPicture(int x, int y, String path) {
        String url = "/sdcard/wechaterData/HMI绘图/图片/";
        Bitmap bitmap = BitmapFactory.decodeFile(url+path);
        Log.d(TAG, "drawPicture: " + bitmap.getByteCount());
        mCanvas.drawBitmap(bitmap,x,y,mPaint);
        invalidate();
    }

    public void setBBackground(int color) {
        setBackgroundColor(color);
        invalidate();
    }
}
