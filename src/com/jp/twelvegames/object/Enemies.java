package com.jp.twelvegames.object;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Enemies {

	private static final int COL = 5;
	private static final int ROW = 5;
	private static final int HIT_DATA = -1;
	private static final int NO_DATA = -5;
	private float enemy_x = 500;
	private float enemy_y = 50;
	private float enemy_w = 50;
	private float enemy_h = 50;
	private float space_w = 80;
	private float space_h = 70;
	private int[][] datas;
	private Drawable[] imgs;
	private Drawable bang_img;

	public Enemies(Drawable[] imgs, Drawable bang_img, float x, float y) {
		this.imgs = imgs;
		this.bang_img = bang_img;
		datas = new int[COL][ROW];
		enemy_x = x;
		enemy_y = y;
	}

	public void init() {
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				datas[i][j] = 0;
			}
		}
	}

	public void move() {
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				if (datas[i][j] >= 0) {
					datas[i][j] = (datas[i][j] + 1) % 4;
				}
			}
		}
	}

	public int checkHit(Point p) {
		int count = 0;
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				if (datas[i][j] < 0)
					continue;
				float x1 = enemy_x + space_w * i;
				float y1 = enemy_y + space_h * j;
				float x2 = enemy_w;
				float y2 = enemy_h;
				if (p.x > x1 && p.x < x2 && p.y > y1 && p.y < y2) {
					datas[i][j] = HIT_DATA;
					count++;
				}
			}

		}
		return count;
	}
	public boolean isFinished(){
		boolean f = true;
		for(int i =0;i<COL;i++){
			for(int j=0;j<ROW;j++){
				if(datas[i][j] >=0) f=false;
			}
		}
		return f;
	}
	
	public void draw(Canvas canvas){
		for(int i =0;i<COL;i++){
			for(int j=0;j<ROW;j++){
				int n = datas[i][j];
				Rect r = new Rect((int)(enemy_x+space_w*i),(int)(enemy_y+space_h*j),(int)(enemy_x+space_w *i +enemy_w),(int)(enemy_y+space_h*j+enemy_h));
				if(n>=0){
					imgs[n].setBounds(r);
					imgs[n].draw(canvas);
				}else if(n<0){
					if (n!=NO_DATA){
						datas[i][j]-= 1;
						bang_img.setBounds(r);
						bang_img.draw(canvas);
					}
				}
			}
		}
	}
}
