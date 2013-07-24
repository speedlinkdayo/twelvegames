package com.jp.twelvegames.base;

import javax.microedition.khronos.opengles.GL10;

public abstract class MyObject {

	// MyGameThreadから1秒間に60回呼ばれ、入力情報等を元に位置計算を行う。
	public void calc() {
	}

	// OpenGLから1秒間に約60回呼ばれ、位置情報にもとづいて描画処理を行う。
	public void draw(GL10 gl) {
	}
}
