package com.jp.twelvegames.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.jp.twelvegames.base.MyObject;
import com.jp.twelvegames.holder.Instance;
import com.jp.twelvegames.holder.Key;



public class Neko extends MyObject {
	private FloatBuffer mVertexBuffer;
	private FloatBuffer mCoordBuffer;

	public int mNowTexture = 0;

	public int mCount = 0;
	
	public float mLx = 2.5f;
	public float mLxPower = 0.008f;

	public float mRotate = 0.0f;
	public float mRotatePower = 0.0f;
	
	public Neko() {
		float[] vertices = {
				-0.5f, -0.5f,
				 0.5f, -0.5f,
				-0.5f,  0.5f,
				 0.5f,  0.5f };
		
		float[] coords = { 
				0.0f, 1.0f, 
				1.0f, 1.0f, 
				0.0f, 0.0f, 
				1.0f, 0.0f };
		
		ByteBuffer bb1 = ByteBuffer.allocateDirect(vertices.length * 4);
		bb1.order(ByteOrder.nativeOrder());
		mVertexBuffer = bb1.asFloatBuffer(); // TODO:asIntBufferの方が高速なのか？
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);

		ByteBuffer bb3 = ByteBuffer.allocateDirect(coords.length * 4);
		bb3.order(ByteOrder.nativeOrder());
		mCoordBuffer = bb3.asFloatBuffer();
		mCoordBuffer.put(coords);
		mCoordBuffer.position(0);
	}

	@Override
	public void calc() {
		//基本、17ms*10間隔でアニメーションのコマを切り替える。
		if (mCount > 10) {
			mCount = 0;
			if (mNowTexture == Instance.textureNeko1) {
				mNowTexture = Instance.textureNeko2;
			}else{
				mNowTexture = Instance.textureNeko1;
			}
		}
		
		//但し、タッチしていると早く動く。
		if (Key.touch) {
			mCount = mCount + 4;
			mLx -= mLxPower * 4;
		} else {
			mCount = mCount + 1;
			mLx -= mLxPower;
		}
	}

	@Override
	public void draw(GL10 gl) {
		// draw trans
		gl.glPushMatrix();
		gl.glScalef(0.5f, 0.5f, 1.0f);
		gl.glTranslatef(mLx, 0.0f, 0.0f);
		
		// draw main
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mNowTexture);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, mVertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mCoordBuffer);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glPopMatrix();
	}
}
