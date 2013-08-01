package com.jp.twelvegames.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SampleDrawView extends SurfaceView implements
		SurfaceHolder.Callback {
	private SurfaceHolder holder;
	private int[][] data;
	private int x = 100;
	private int y = 100;

	public SampleDrawView(Context context) {
		super(context);
		init();
	}

	public SampleDrawView(Context context, AttributeSet attrs) {
		super(context,attrs);
		init();
	}

	public void init() {
		holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		requestFocus();
		data = new int[25][2];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = 100;
			data[i][1] = 100;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		draw();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int n = event.getAction();
		x = (int) event.getX();
		y = (int) event.getY();
		switch (n) {
		case MotionEvent.ACTION_DOWN:
			addPoint(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			addPoint(x, y);
			break;
		}
		draw();
		return true;
	}

	public void addPoint(int x, int y) {
		for (int i = data.length - 2; i >= 0; i--) {
			data[i + 1][0] = data[i][0];
			data[i + 1][1] = data[i][1];
		}
		data[0][0] = x;
		data[0][1] = y;
	}

	public void draw() {
		Canvas c = holder.lockCanvas();
		c.drawColor(Color.WHITE);
		Paint p = new Paint();
		p.setStyle(Style.FILL);
		p.setColor(Color.RED);
		for (int i = 0; i < data.length; i++) {
			p.setAlpha(255 - i * 10);
			int x = data[i][0];
			int y = data[i][1];
			RectF r = new RectF(x - 50, y-50, x + 50, y + 50);
			c.drawArc(r, 0f, 360f, true, p);
		}
		holder.unlockCanvasAndPost(c);
	}
}
