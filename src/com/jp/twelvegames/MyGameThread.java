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
			// 1�b�Ԃ�60����x(17ms)�����������Ȃ��Ƃ������ƁB
			mNowTime = System.currentTimeMillis();
			mDiff = mNowTime - mLastUpdateTime;
			while (mDiff >= 17) {
				mDiff -= 17;
				dispatch();
			}
			mLastUpdateTime = mNowTime - mDiff;
			// �܂��Ă���Ԃ������̓X���[�v���đ��v���Z�X�����s���Ă�����B
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
		// �f���p�Ƀh���[�L���[�ɃI�u�W�F�N�g��ǉ�����B
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
		// ��������������V�[���ݒ�B
		scene = SCENE_DEMO;

		// �N�G���[�T���v��
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
		// �f���p�I�u�W�F�N�g�̍��W�v�Z����
		for (int i = 0; i < Instance.draw9.size(); i++) {
			MyObject obj = Instance.draw9.get(i); // TODO:���[�J���ϐ��g���Ă�̂Ō�ŏ�����
			obj.calc();
		}
		// ��莞�Ԃ��߂�����^�C�g����ʂ�
		if (mCount > 330) { // TODO:�@��ɂ���Ď��Ԃ���Ȃ��͂������ǋC�ɂȂ�ˁB
			// �h���[�L���[����I�u�W�F�N�g��S�폜
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
		// �^�C�g���p�I�u�W�F�N�g�̍��W�v�Z����
		for (int i = 0; i < Instance.draw9.size(); i++) {
			MyObject obj = Instance.draw9.get(i);
			obj.calc();
		}
		// �^�C�g����ԂŃ^�b�`������Q�[����ʂ̏���
		if (Key.touch) {
			// �h���[�L���[����I�u�W�F�N�g��S�폜
			while (Instance.draw9.size() != 0) {
				Instance.draw9.remove(0);
			}
			// �����ƃ^�b�`���Ă���ƃ^�C�g����ʂ��΂���Ă��܂��̂ŃL�[�N���A����B
			Key.touch = false;
			// �Q�[���p�Ƀh���[�L���[�ɃI�u�W�F�N�g��ǉ�����B
			// Instance.draw9.add(new Course());
			// //TODO:�y���W���������ĕ\���������߂�ׂ�����ˁB�ق�Ƃ́B
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
		// �Q�[���p�I�u�W�F�N�g�̍��W�v�Z����
		for (int i = 0; i < Instance.draw9.size(); i++) {
			MyObject obj = Instance.draw9.get(i);
			obj.calc();
		}
	}

	// ���߂�����
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
	// // TODO �����������ꂽ catch �u���b�N
	// e1.printStackTrace();
	// }
	//
	// }

	private void sceneGameOver() {
		scene = SCENE_INIT;
		mActive = false;
	}
}
