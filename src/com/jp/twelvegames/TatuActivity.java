package com.jp.twelvegames;

import com.jp.twelvegames.view.TatuGameView;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/*
 * タツのゲームクラスです
 */
public class TatuActivity extends Activity {
	public float weight;
	public float height;
	private TatuGameView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		WindowManager manager = window.getWindowManager();
		Display disp = manager.getDefaultDisplay();
		Point size = new Point();
		disp.getSize(size);
		weight = size.x;
		height = size.y;
		setContentView(R.layout.tatu_game);
		view = (TatuGameView) this.findViewById(R.id.tatuGameView01);
		view.start();
	}
	
}
