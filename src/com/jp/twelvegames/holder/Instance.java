package com.jp.twelvegames.holder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.jp.twelvegames.MyGameThread;
import com.jp.twelvegames.base.MyObject;

public class Instance {

	// �R���e�L�X�g�iActivity�N���X�̃C���X�^���X�j
	public static Context context = null;

	// �Q�[���X���b�h�C���X�^���X�BActivity�N���X���炢�����悤�ɂ��邽�߂ɂ����ł����Ă܂��B
	public static MyGameThread myGameThread = null;

	// OpenGL�ŕ`�悷��I�u�W�F�N�g�̃��X�g�������ł����Ă܂��B
	public static List<MyObject> draw9 = new ArrayList<MyObject>();

	// �f�[�^�x�[�X�p�̃X���b�h�C���X�^���X
	//public static MyDBThread myDBThread = null;

	// �e�N�X�`���p�ł��B
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

	// �J���X�ǉ�
	public static int textureKarasu;

	// ���߂��ɂ���Ă݂�
	public static int textureOtamesi;

	// ������萔�ł��B
	public static final String ASCII = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.";

}
