package com.jp.twelvegames.holder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.jp.twelvegames.MyGameThread;
import com.jp.twelvegames.base.MyObject;

public class Instance {

	// コンテキスト（Activityクラスのインスタンス）
	public static Context context = null;

	// ゲームスレッドインスタンス。Activityクラスからいじれるようにするためにここでもってます。
	public static MyGameThread myGameThread = null;

	// OpenGLで描画するオブジェクトのリストをここでもってます。
	public static List<MyObject> draw9 = new ArrayList<MyObject>();

	// データベース用のスレッドインスタンス
	//public static MyDBThread myDBThread = null;

	// テクスチャ用です。
	public static int textureNeko1;
	public static int textureNeko2;
	public static int textureTareFont;
	public static int textureMyCar;
	public static int textureHandle;
	public static int textureCourse;
	// keikotestu
	public static int textureOharyo2;
	public static int textureOharyo3;

	// keikotest2
	public static int textureIchigo;

	// keikotest3
	public static int tesxtureTestGirl;

	// カラス追加
	public static int textureKarasu;

	// ためしにやってみる
	public static int textureOtamesi;

	// 文字列定数です。
	public static final String ASCII = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.";

}
