package com.jp.twelvegames;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.jp.twelvegames.base.MyObject;
import com.jp.twelvegames.base.TextureManager;
import com.jp.twelvegames.holder.Instance;
import com.jp.twelvegames.holder.Key;

public class MyGLSurfaceView extends GLSurfaceView implements
		GLSurfaceView.Renderer, GestureDetector.OnGestureListener,
		GestureDetector.OnDoubleTapListener {

	private final GestureDetector gestureDetector;

	// fps
	private int mFps = 0;
	private long mFpsNowTime = 0;
	private long mFpsCountStartTime = System.currentTimeMillis();

	public MyGLSurfaceView(Context context) {
		super(context);
		Instance.context = context;
		gestureDetector = new GestureDetector(context, this);
		setFocusable(true);
		setRenderer(this);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig eglconfig) {
		// TODO:起動に重くなってきたらこいつのせいなのでローディング画面でもつくかな。
		Instance.textureNeko1 = TextureManager.loadTexture(gl, Instance.context
				.getResources(), R.drawable.neko1);
		Instance.textureNeko2 = TextureManager.loadTexture(gl, Instance.context
				.getResources(), R.drawable.neko2);
//		Instance.textureTareFont = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.tarefont);
//		Instance.textureMyCar = TextureManager.loadTexture(gl, Instance.context
//				.getResources(), R.drawable.f0032891);
//		Instance.textureHandle = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.handle);
//		Instance.textureCourse = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.courace_yamanasi);
//		Instance.textureOharyo2 = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.ohara2);
//		Instance.textureOharyo3 = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.ohara3);
//		Instance.textureIchigo = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.ichigo);
//		// test
//		Instance.tesxtureTestGirl = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.testgirl);
//		Instance.textureKarasu = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.karasu);
//		// 画面クリアカラーをセット
//		Instance.textureOtamesi = TextureManager.loadTexture(gl,
//				Instance.context.getResources(), R.drawable.haikei);
//		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// gl.glClearColor(0.6f, 0.8f, 1.0f, 0.0f);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 縦3：横2で描画する TODO:縦長画面固定としようかな。
		int w = 0;
		int h = 0;
		while (w < width && h < height) {
			w += 3;
			h += 2;
		}
		// 余白部分の上下左右均等にするため値を求める。
		int offsetx = (width - w) / 2;
		int offsety = (height - h) / 2;
		gl.glViewport(offsetx, offsety, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		// 視体積を設定する。
		// Y軸で回転させる場合、Z座標の奥行を確保しておかないとZクリッピングされておかしくなる。
		gl.glOrthof(-1.5f, 1.5f, -1.0f, 1.0f, -10.0f, 10.0f);

	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// fps
		mFpsNowTime = System.currentTimeMillis();
		if (mFpsNowTime - mFpsCountStartTime >= 1000) {
			Log.d(getClass().toString(), String.valueOf(mFps));
			mFps = 0;
			mFpsCountStartTime = mFpsNowTime;
		}
		mFps++;

		// draw init
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // TODO:GL10.GL_DEPTH_BUFFER_BITは不要かな？
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		// //TODO:カラーバッファを使うようなら活性化しないとね。

		// draw main
		for (int i = 0; i < Instance.draw9.size(); i++) {
			MyObject obj = Instance.draw9.get(i);
			obj.draw(gl);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Key.touch = true;
			break;
		case MotionEvent.ACTION_UP:
			Key.touch = false;
			break;
		}

		// タッチ処理をGestureDetectorにまかせる
		gestureDetector.onTouchEvent(event);
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	public boolean onScroll(MotionEvent e0, MotionEvent e1, float distanceX,
			float distanceY) {
		Key.distanceX = distanceX;
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDoubleTap(MotionEvent arg0) {
		return false;
	}
}
