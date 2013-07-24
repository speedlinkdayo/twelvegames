package com.jp.twelvegames.base;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class TextureManager {
	private static Map<Integer, Integer> mTextures = new Hashtable<Integer, Integer>();
	private static final BitmapFactory.Options options = new BitmapFactory.Options();
	static {
		options.inScaled = false;
		options.inPreferredConfig = Config.ARGB_8888;
	}

	public static final void addTexture(int resId, int texId) {
		mTextures.put(resId, texId);
	}

	public static final void deleteAll(GL10 gl) {
		List<Integer> keys = new ArrayList<Integer>(mTextures.keySet());
		for (Integer key : keys) {
			deleteTexture(gl, key);
		}
	}

	public static final void deleteTexture(GL10 gl, int resId) {
		if (mTextures.containsKey(resId)) {
			int[] texId = new int[1];
			texId[0] = mTextures.get(resId);
			gl.glDeleteTextures(1, texId, 0);
			mTextures.remove(resId);
		}
	}

	public static final int loadTexture(GL10 gl, Resources resources, int resId) {
		int[] textures = new int[1];
		Bitmap bmp = BitmapFactory.decodeResource(resources, resId, options);
		if (bmp == null) {
			return 0;
		}
		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		bmp.recycle();
		
		TextureManager.addTexture(resId, textures[0]);
		return textures[0];
	}
}
