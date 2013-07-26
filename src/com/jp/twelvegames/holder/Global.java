package com.jp.twelvegames.holder;

import java.util.Random;



public class Global {
	// ユーザーID
	public static String UserId;
	public static float ichigoX;
	public static float ichigoY;
	public static float ichigoX1;
	public static float ichigoY1;

	public static boolean IchigoNageta;
	public static int IchigoNagetaYo;

	public static float oharyoX;
	public static float oharyoY;
	public static float oharyoX1;
	public static float oharyoY1;
	public static float oharyoX2;
	public static float oharyoY2;
	public static float oharyoX3;
	public static float oharyoY3;
	public static float oharyoX4;
	public static float oharyoY4;

	// カラス
	public static float KarasuX;
	public static float KarasuY;

	public static float KarasuSqrt;
	public static float oharyoSqrt;
	public static float ichigoSsqrt;
	public static float missIchigoSqrt;
	// public static Mojiretu userIdmoji = new Mojiretu("", 1.0f, 1.0f);
	//public static Mojiretu score = new Mojiretu("", 9.0f, 8.0f);
	//public static Mojiretu UserScore = new Mojiretu("", 5.0f, 8.0f);

	// 点数
	public static int hitScore;
	// 当たり判定
	public static boolean hitSita;

	// ランダムな値の取得 float
	public static Random rand = new Random(System.currentTimeMillis());

}
