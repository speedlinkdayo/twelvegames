package com.jp.twelvegames.view;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jp.twelvegames.R;
import com.jp.twelvegames.TatuActivity;
import com.jp.twelvegames.object.Ball;
import com.jp.twelvegames.object.Chara;
import com.jp.twelvegames.object.Enemies;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class TatuGameView extends SurfaceView implements SurfaceHolder.Callback {

	private static final long GAME_TIMES = 10000; // ゲームのプレイ時間
	private ScheduledExecutorService executor; // スレッド用インスタンス
	private SurfaceHolder holder; // サーフェイスのホルダ
	private TatuActivity tatugameActivity; // アクティビティクラス
	private Enemies enemies; // 敵(今回タツ)管理クラス
	private Chara chara;// キャラクター管理クラス
	private Ball ball; // ボール管理クラス
	private Drawable back; // 背景イメージ
	private float score_x = 40; // 点数の横位置
	private float score_y = 40; // 点数の縦位置
	private float char_x = 100; // キャラクタの横位置
	private float char_y = 350; // キャラクタの縦位置
	private float enemy_x = 400; // 辰
	private float enemy_y = 50;
	private float end_x = 200;
	private float end_y = 200;
	private int point = 0;
	private boolean game_end = true; // ゲーム終了チェック
	private long start_time;
	private Point press_loc; // タッチした場所の保管

	public TatuGameView(Context context) {
		super(context);
		init(context);
	}

	public TatuGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void init(Context context) {
		holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		tatugameActivity = (TatuActivity) context;
		setBoardSize();
	}

	private void setBoardSize() {
		float w = tatugameActivity.weight;
		float h = tatugameActivity.height;
		float dw = w / 854f;
		float dh = h / 480f;
		score_x *= dw;
		score_y *= dh;
		char_x *= dw;
		char_y *= dh;
		enemy_x *= dw;
		enemy_y *= dh;
		end_x *= dw;
		end_y *= dh;

		Resources res = tatugameActivity.getResources();
		back = res.getDrawable(R.drawable.back);
		back.setBounds(new Rect(0,0,(int)tatugameActivity.weight,(int)tatugameActivity.height));
		Drawable ball_img = res.getDrawable(R.drawable.ball);
		ball = new Ball(ball_img, char_x,char_y);
		Drawable bang_img = res.getDrawable(R.drawable.bang);
		Drawable[] char_img = new Drawable[3];
		char_img[0] = res.getDrawable(R.drawable.chara);
		// char_img[1]
		// char_img[2]
		chara = new Chara(char_img, char_x, char_y);
		Drawable[] dragon_img = new Drawable[4];
		dragon_img[0] = res.getDrawable(R.drawable.dragon);
		enemies = new Enemies(dragon_img, bang_img, enemy_x, enemy_y);
	}

	public void start() {
		try {
			executor.shutdown();
		} catch (Exception e) {
			enemies.init();
			ball.init();
			point = 0;
			start_time = SystemClock.currentThreadTimeMillis();
			game_end = false;
			executor = Executors.newSingleThreadScheduledExecutor();
			executor.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
				}
			}, 100, 100, TimeUnit.MILLISECONDS);
			showMsg("start");
		}
	}

	public void showMsg(String s) {
		Toast toast = Toast.makeText(tatugameActivity, s, Toast.LENGTH_SHORT);
		toast.show();
	}

	public void gameOver() {
		game_end = true;
		executor.shutdown();
		draw();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (game_end) {
			return true;
		}
		int n = event.getAction();
		float x = event.getX();
		float y = event.getY();
		switch (n) {
		case MotionEvent.ACTION_DOWN:
			press_loc = new Point((int) x, (int) y);
			break;
		case MotionEvent.ACTION_UP:
			float dx = x - press_loc.x;
			float dy = press_loc.y - y;
			ball.flying(new Point((int) dx, (int) dy));
			point--;
			if (point < 0) {
				point = 0;
			}
			break;

		}
		return true;
	}

	public void draw(){
		Canvas canvas = holder.lockCanvas();
		chara.draw(canvas);
		enemies.draw(canvas);
		ball.draw(canvas);
		Paint p  = new Paint();
		p.setTextSize(70);
		p.setFakeBoldText(true);
		p.setColor(Color.RED);
		canvas.drawText("GAME OVER", end_x, end_y, p);
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder arg0) {
		draw();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		executor.shutdown();

	}

}
