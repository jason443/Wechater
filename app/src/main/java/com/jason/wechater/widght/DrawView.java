package com.jason.wechater.widght;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawView extends View{
    private int view_width=0;//屏幕的宽度
    private int view_height=0;//屏幕的高度
    private float preX;//起始点的x坐标
    private float preY;//起始点的y坐标
    private Path path;//路径
    public Paint paint;//画笔
    public boolean isClean = false;
    Bitmap cacheBitmap=null;//定义一个内存中的图片，该图片将作为缓冲区
    Canvas cacheCanvas=null;//定义cacheBitmap上的Canvas对象
    /*
     * 功能：构造方法
     * */
    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view_width=context.getResources().getDisplayMetrics().widthPixels;//获取屏幕宽度
        view_height=context.getResources().getDisplayMetrics().heightPixels;//获取屏幕高度
        //创建一个与该View相同大小的缓存区
        cacheBitmap=Bitmap.createBitmap(view_width,view_height, Bitmap.Config.ARGB_8888);
        cacheCanvas=new Canvas();//创建一个新的画布
        path=new Path();
        //在cacheCanvas上绘制cacheBitmap
        cacheCanvas.setBitmap(cacheBitmap);
        paint=new Paint(Paint.DITHER_FLAG);//Paint.DITHER_FLAG防抖动的
        paint.setColor(Color.RED);
        //设置画笔风格
        paint.setStyle(Paint.Style.STROKE);//设置填充方式为描边
        paint.setStrokeJoin(Paint.Join.ROUND);//设置笔刷转弯处的连接风格
        paint.setStrokeCap(Paint.Cap.ROUND);//设置笔刷的图形样式(体现在线的端点上)
        paint.setStrokeWidth(1);//设置默认笔触的宽度为1像素
        paint.setAntiAlias(true);//设置抗锯齿效果
        paint.setDither(true);//使用抖动效果

    }

    public void startDraw() {
        paint.setXfermode(null);//取消擦除效果
        paint.setStrokeWidth(1);//初始化画笔的宽度
        paint.setColor(Color.RED);//设置笔的颜色为红色
        isClean = false;
    }

    public void cleanDraw() {
        cacheCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    /*
     * 功能：重写onDraw方法
     * */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFFFFFFFF);//设置背景色
        Paint bmpPaint=new Paint();//采用默认设置创建一个画笔
        canvas.drawBitmap(cacheBitmap, 0, 0,bmpPaint);//绘制cacheBitmap
        canvas.drawPath(path, paint);//绘制路径
        canvas.save(Canvas.ALL_SAVE_FLAG);//保存canvas的状态
        //恢复canvas之前保存的状态，防止保存后对canvas执行的操作对后续的绘制有影响
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取触摸事件发生的位置
        float x=event.getX();
        float y=event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //将绘图的起始点移到(x,y)坐标点的位置
                path.moveTo(x, y);
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                //保证横竖绘制距离不能超过625
                float dx=Math.abs(x-preX);
                float dy=Math.abs(y-preY);
                if(dx>5||dy>5){
                    //.quadTo贝塞尔曲线，实现平滑曲线(对比lineTo)
                    //x1，y1为控制点的坐标值，x2，y2为终点的坐标值
                    path.quadTo(preX, preY, (x+preX)/2, (y+preY)/2);
                    preX=x;
                    preY=y;
                }
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path, paint);//绘制路径
                path.reset();
                break;
        }
        invalidate();
        return true;//返回true,表明处理方法已经处理该事件
    }

    public void clear(){
        isClean = true;
        //设置图形重叠时的处理方式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //设置笔触的宽度
        paint.setStrokeWidth(50);
    }

    public void save(){
        try{
            saveBitmap("myPitcture");
        }catch(IOException e){
            e.printStackTrace();
            Log.d("1111", "save: " + "1111");
        }
    }

    public Bitmap getBitmap() {
        return cacheBitmap;
    }

    private void saveBitmap(String fileName) throws IOException {
        File file=new File(getSDPath()+fileName+".png");
        file.createNewFile();
        FileOutputStream fileOS=new FileOutputStream(file);
        //将绘图内容压缩为PNG格式输出到输出流对象中
        cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOS);
        fileOS.flush();//将缓冲区中的数据全部写出到输出流中
        fileOS.close();//关闭文件输出流对象
    }

    //获得SD卡的根目录
    public String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在




        if   (sdCardExist)      //如果SD卡存在，则获取跟目录
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();

    }
}