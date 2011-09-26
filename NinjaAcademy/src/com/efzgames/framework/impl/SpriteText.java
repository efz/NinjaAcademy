package com.efzgames.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;
import com.efzgames.framework.impl.GLGraphics;

public class SpriteText {

	private int height;
	private int width;
	private Texture texture;
	private TextureRegion textureRegion;
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public SpriteText(GLGame game, String text, Typeface font, int size, int color, int shadowcolor,
			int minwidth, int minheight) {
		Paint textPaint = new Paint();
		textPaint.setShadowLayer(0.1f, 4, 4, shadowcolor);
		create(game, text, font, size, color, textPaint, minwidth, minheight);
	}
	
	public SpriteText(GLGame game, String text, Typeface font, int size, int color,
			int minwidth, int minheight) {
		Paint textPaint = new Paint();
		create(game, text, font, size, color, textPaint, minwidth, minheight);
	}

	private void create(GLGame game, String text, Typeface font, int size,
			int color, Paint textPaint,int minwidth, int minheight) {
		
		textPaint.setTypeface(font);
		textPaint.setTextSize(size);
		textPaint.setAntiAlias(true);
		textPaint.setColor(color);
	
		int descent = (int) Math.ceil(textPaint.descent());
		int ascent = (int) Math.ceil(-textPaint.ascent());
		
		int textHeight = ascent + descent;
	    int textWidth =  (int) Math.ceil(textPaint.measureText(text)) ;
	    width =  Math.max(textWidth, minwidth) ;
		height = Math.max(textHeight,minheight) ;		

		
		// Create an empty, mutable bitmap
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_4444);
		// get a canvas to paint over the bitmap
		Canvas canvas = new Canvas(bitmap);
		bitmap.eraseColor(0);

		// draw the text centered
		canvas.drawText(text, (width - textWidth)/2, ascent+ (height - textHeight)/2, textPaint);

		texture = new Texture(game.getGLGraphics(), bitmap);
		textureRegion = new TextureRegion(texture, 0, 0, width, height);
	}

	public void reload() {
		texture.reload();
	}
	
	public void draw(SpriteBatcher batcher,float x, float y){
		batcher.beginBatch(texture);
		batcher.drawSprite(x, y, width, height, textureRegion);
		batcher.endBatch();
	}
}
