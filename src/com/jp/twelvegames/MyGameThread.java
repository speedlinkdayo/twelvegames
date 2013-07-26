package com.jp.twelvegames;


import android.os.Handler;
import android.util.Log;

import com.jp.twelvegames.base.MyObject;
import com.jp.twelvegames.holder.Instance;
import com.jp.twelvegames.holder.Key;
import com.jp.twelvegames.object.Neko;

public class MyGameThread extends Thread {

	Handler mHandler = new Handler();

	public static final int SCENE_INIT = 0;
	public static final int SCENE_DEMO = 10;
	public static final int SCENE_TITLE = 20;
	public static final int SCENE_GAME_MAIN = 30;
	public static final int SCENE_GAME_OVER = 40;

	public static int scene = SCENE_INIT;

	public boolean mActive = true;
	public int mCount = 0;

	private long mLastUpdateTime = System.currentTimeMillis();
	private long mNowTime = 0;
	private long mDiff = 0;

	@Override
	public void run() {
		while (mActive) {
			// 1秒間に60回程度(17ms)しか処理しないということ。
			mNowTime = System.currentTimeMillis();
			mDiff = mNowTime - mLastUpdateTime;
			while (mDiff >= 17) {
				mDiff -= 17;
				dispatch();
			}
			mLastUpdateTime = mNowTime - mDiff;
			// まっている間こっちはスリープして他プロセスを実行してあげる。
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	private void dispatch() {
		switch (scene) {
		case SCENE_INIT:
			sceneInit();
			break;
		case SCENE_DEMO:
			sceneDemo();
			break;
		case SCENE_TITLE:
			sceneTitle();
			break;
		case SCENE_GAME_MAIN:
			sceneMain();
			// sceneUserData();
			break;
		case SCENE_GAME_OVER:
			sceneGameOver();
			break;
		}
	}

	private void sceneInit() {
		// デモ用にドローキューにオブジェクトを追加する。
//		Instance.draw9.add(new Moji1("c", -5.0f, 0.4f, 0.1f, 200));
//		Instance.draw9.add(new Moji1("r", -4.3f, 0.4f, 0.1f, 205));
//		Instance.draw9.add(new Moji1("e", -3.6f, 0.4f, 0.1f, 210));
//		Instance.draw9.add(new Moji1("a", -2.9f, 0.4f, 0.1f, 215));
//		Instance.draw9.add(new Moji1("t", -2.2f, 0.4f, 0.1f, 220));
//		Instance.draw9.add(new Moji1("e", -1.5f, 0.4f, 0.1f, 225));
//		Instance.draw9.add(new Moji1("d", -0.8f, 0.4f, 0.1f, 230));
//		Instance.draw9.add(new Moji1("b", 0.6f, 0.4f, 0.1f, 235));
//		Instance.draw9.add(new Moji1("y", 1.3f, 0.4f, 0.1f, 240));
//		Instance.draw9.add(new Moji1("k", -2.0f, -1.0f, 0.15f, 245));
//		Instance.draw9.add(new Moji1("o", -1.3f, -1.0f, 0.15f, 250));
//		Instance.draw9.add(new Moji1("n", -0.6f, -1.0f, 0.15f, 255));
//		Instance.draw9.add(new Moji1("a", 0.1f, -1.0f, 0.15f, 260));
//		Instance.draw9.add(new Moji1("n", 0.8f, -1.0f, 0.15f, 265));
		// 準備完了したらシーン設定。
		scene = SCENE_DEMO;

		// クエリーサンプル
//		try {
//			UserBean user = new UserBean();
//			user.setUid("aaa");
//			UserDao dao = new UserDao();
//			UserBean result = dao.select(user);
//			Log.d("test", result.getPass());
//		} catch (Exception e) {
//		}

	}

	private void sceneDemo() {
		// デモ用オブジェクトの座標計算処理
		for (int i = 0; i < Instance.draw9.size(); i++) {
			MyObject obj = Instance.draw9.get(i); // TODO:ローカル変数使ってるので後で消そう
			obj.calc();
		}
		// 一定時間が過ぎたらタイトル画面へ
		if (mCount > 330) { // TODO:機種によって時間かわないはずだけど気になるね。
			// ドローキューからオブジェクトを全削除
			while (Instance.draw9.size() != 0) {
				Instance.draw9.remove(0);
			}
//			Instance.draw9.add(new Moji2("S", 0.0f, 0.0f));
//			Instance.draw9.add(new Moji2("t", 0.5f, 0.0f));
//			Instance.draw9.add(new Moji2("a", 1.0f, 0.0f));
//			Instance.draw9.add(new Moji2("r", 1.5f, 0.0f));
//			Instance.draw9.add(new Moji2("t", 2.0f, 0.0f));

			scene = SCENE_TITLE;
		}
		// aaa
		mCount++;
	}

	private void sceneTitle() {
		// タイトル用オブジェクトの座標計算処理
		for (int i = 0; i < Instance.draw9.size(); i++) {
			MyObject obj = Instance.draw9.get(i);
			obj.calc();
		}
		// タイトル状態でタッチしたらゲーム画面の準備
		if (Key.touch) {
			// ドローキューからオブジェクトを全削除
			while (Instance.draw9.size() != 0) {
				Instance.draw9.remove(0);
			}
			// ずっとタッチしているとタイトル画面を飛ばされてしまうのでキークリアする。
			Key.touch = false;
			// ゲーム用にドローキューにオブジェクトを追加する。
			// Instance.draw9.add(new Course());
			// //TODO:Ｚ座標をいじって表示順をきめるべきだよね。ほんとは。
			// Instance.draw9.add(new Handle());
			// Instance.draw9.add(new MyCar());
			// Instance.draw9.add(new Neko());
			//new UserData();
			// Log.i(Global.UserId, Global.UserId);
			//comment
			//Instance.draw9.add(new Haikei());
			//Instance.draw9.add(new Mojiretu("keiko", -13.0f, 8.0f));
			//Instance.draw9.add(new Oharyo());
			//Instance.draw9.add(new Ichigo());
			//Instance.draw9.add(new Karasu());
			// Instance.draw9.add(new TestGirl());
			//Instance.draw9.add(Global.score);
			//Instance.draw9.add(Global.UserScore);
			Instance.draw9.add(new Neko());
			scene = SCENE_GAME_MAIN;
		}
	}

	private void sceneMain() {
		// ゲーム用オブジェクトの座標計算処理
		for (int i = 0; i < Instance.draw9.size(); i++) {
			MyObject obj = Instance.draw9.get(i);
			obj.calc();
		}
	}

	// だめだった
	// private void sceneUserData() {
	// UserDao dao = new UserDao();
	// UserBean bn = new UserBean();
	// bn.setUid("konan0524");
	// try {
	// dao.select(bn);
	// bn.setScore(Global.hitScore);
	// bn.setUid("konan0524");
	// dao.updateScore(bn);
	// Global.UserScore.mValue = String.valueOf(bn.getScore());
	//
	// } catch (Exception e1) {
	// // TODO 自動生成された catch ブロック
	// e1.printStackTrace();
	// }
	//
	// }

	private void sceneGameOver() {
		scene = SCENE_INIT;
		mActive = false;
	}
}
