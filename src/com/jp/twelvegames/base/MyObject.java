package com.jp.twelvegames.base;

import javax.microedition.khronos.opengles.GL10;

public abstract class MyObject {

	// MyGameThread����1�b�Ԃ�60��Ă΂�A���͏�񓙂����Ɉʒu�v�Z���s���B
	public void calc() {
	}

	// OpenGL����1�b�Ԃɖ�60��Ă΂�A�ʒu���ɂ��ƂÂ��ĕ`�揈�����s���B
	public void draw(GL10 gl) {
	}
}
