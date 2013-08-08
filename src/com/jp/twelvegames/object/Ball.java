package com.jp.twelvegames.object;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Ball {
	private float HOLI = 400;
	private Drawable img;
	private float x = 100;
	private float y = 400;
	private float ball_x = 150;
	private float ball_y = 300;
	private float ball_w = 20;
	private float ball_h = 20;
	private float g = 10.0f;
	private float gd = 1.0f;
	private float s =10.0f;
	private boolean flying = false;
	
	public Ball(Drawable img,float x,float y){
		this.img = img;
		this.x =x;
		this.y =y;
	}
	public void init(){
		flying = false;
	}
	
	public void flying(Point p){
		ball_x =x;
		ball_y =y;
		flying = true;
		s = p.x/10;
		if(s > 25)s =25;
	}
	
	public boolean isFlying(){
		return flying;
	}

	public void move(){
		if(flying){
			ball_x += s;
			ball_y -=g;
			g -=gd;
		}
		if(ball_y > HOLI){
			flying = false;
		}
	}
	
	public Point getCenter(){
		return new Point((int)(ball_x + ball_w/2),(int)(ball_y+ball_h/2));
	}
	public void draw(Canvas canvas){
		if(flying){
			Rect r = new Rect((int)(ball_x),(int)(ball_y),(int)(ball_x+ball_w),(int)(ball_y+ball_h));
			img.setBounds(r);
			img.draw(canvas);
		}
	}
}
