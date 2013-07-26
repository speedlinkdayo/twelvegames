package com.jp.twelvegames;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.jp.twelvegames.holder.Instance;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	public void onClick(View v){

	// OpenGLスレッド開始(onDrawFrameが定期的に呼ばれるようになる)
	MyGLSurfaceView myGLSurfaceView = new MyGLSurfaceView(this);
	setContentView(myGLSurfaceView);

	// ゲームスレッド開始(runが定期的に呼ばれるようになる)
	Instance.myGameThread = new MyGameThread();
	Instance.myGameThread.start();
	Instance.myGameThread.mActive = true;
	//Instance.myDBThread.start();
}

@Override
public void onResume() {
	super.onResume();
	
}

@Override
public void onPause() {
	super.onPause();
	Instance.myGameThread.mActive = false;

	// ゲームを閉じる際にメモリ上に残っているオブジェクトを削除する。
	// ゲームを閉じる＝終了したとみなします。
while (Instance.draw9.size() != 0) {
	 Instance.draw9.remove(0);
	 }
}
}

