package com.jp.twelvegames.object;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Chara {

	private Drawable[] imgs; //•\¦‚·‚éDrawble‚Ì”z—ñ
	private float chara_x = 100;
	private float chara_y = 350;
	private float chara_w =100;
	private float chara_h = 100;
	private int show_img = 0;
	private boolean throwing = false; //“Š‚°‚Ä‚¢‚é‚©‚Ç‚¤‚©
	
	public Chara(Drawable[] imgs,float x,float y){
		this.imgs = imgs;
		chara_x = x;
		chara_y = y;
		init();
	}
	public void init(){
		show_img=0;
		throwing = false;
	}
	public void throwing(){
		if(throwing){
			return;
		}
		show_img = 0;
		throwing = true;
	}
	public void draw(Canvas canvas){
		int n = show_img;
		if(n >= imgs.length-1){
			n = imgs.length-1;
			Rect r = new Rect((int)(chara_x),(int) (chara_y),(int)(chara_x + chara_w),(int)(chara_y + chara_h));
			imgs[n].setBounds(r);
			imgs[n].draw(canvas);
		}
	}
}
