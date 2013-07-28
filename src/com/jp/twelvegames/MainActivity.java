package com.jp.twelvegames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jp.twelvegames.holder.Instance;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	//TODO ����L����񓮍쌟�؂̃S�~
	public void onClick(View v){

	// OpenGL�X���b�h�J�n(onDrawFrame������I�ɌĂ΂��悤�ɂȂ�)
	MyGLSurfaceView myGLSurfaceView = new MyGLSurfaceView(this);
	setContentView(myGLSurfaceView);

	// �Q�[���X���b�h�J�n(run������I�ɌĂ΂��悤�ɂȂ�)
	Instance.myGameThread = new MyGameThread();
	Instance.myGameThread.start();
	Instance.myGameThread.mActive = true;
	//Instance.myDBThread.start();
}
	//SurfaceView�̃T���v���N��
	public void onClick_drawSample(View v){
		Intent intent = new Intent(this,com.jp.twelvegames.SampleDrawActivity.class);
		startActivity(intent);	
	}

	//�^�c�Q�[���X�^�[�g
	public void onClick_tatu(View v){
		Intent intent = new Intent(this,com.jp.twelvegames.TatuActivity.class);
		startActivity(intent);
	}
@Override
public void onResume() {
	super.onResume();
	
}

@Override
public void onPause() {
	super.onPause();
	//Instance.myGameThread.mActive = false;

	// �Q�[�������ۂɃ�������Ɏc���Ă���I�u�W�F�N�g���폜����B
	// �Q�[������遁�I�������Ƃ݂Ȃ��܂��B
while (Instance.draw9.size() != 0) {
	 Instance.draw9.remove(0);
	 }
}
}

