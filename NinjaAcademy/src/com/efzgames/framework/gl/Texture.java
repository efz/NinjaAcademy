package com.efzgames.framework.gl;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.opengl.GLUtils;

import com.efzgames.framework.FileIO;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.impl.GLGraphics;

public class Texture {
	GLGraphics glGraphics;
	FileIO fileIO;
	String fileName;
	int textureId;
	int minFilter;
	int magFilter;
	public int width;
	public int height;
	boolean mipmapped;

	Bitmap fileBitmap;

	public Texture(GLGraphics glGraphics, Bitmap fileBitmap) {
		this.glGraphics = glGraphics;
		this.fileBitmap = fileBitmap;
		this.mipmapped = false;
		
		load(fileBitmap);
	}

	public Texture(GLGame glGame, String fileName) {
		this(glGame, fileName, false);
	}

	public Texture(GLGame glGame, String fileName, boolean mipmapped) {
		this.glGraphics = glGame.getGLGraphics();
		this.fileIO = glGame.getFileIO();
		this.fileName = fileName;
		this.mipmapped = mipmapped;
		load();
	}

	private void load() {
		

		InputStream in = null;
		Bitmap bitmap = null;
		try {
			in = fileIO.readAsset(fileName);
			bitmap = BitmapFactory.decodeStream(in);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load texture '" + fileName
					+ "'", e);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
				}
		}
		
		load(bitmap);

	}

	private void load(Bitmap bitmap) {
		GL10 gl = glGraphics.getGL();
		int[] textureIds = new int[1];
		gl.glGenTextures(1, textureIds, 0);
		textureId = textureIds[0];

		if (mipmapped) {
			createMipmaps(gl, bitmap);
		} else {
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			setFilters(GL10.GL_NEAREST, GL10.GL_NEAREST);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
			width = bitmap.getWidth();
			height = bitmap.getHeight();
			if(fileBitmap == null)
				bitmap.recycle();
		}
	}

	private void createMipmaps(GL10 gl, Bitmap bitmap) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		setFilters(GL10.GL_LINEAR_MIPMAP_NEAREST, GL10.GL_LINEAR);

		int level = 0;
		int newWidth = width;
		int newHeight = height;
		while (true) {
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, level, bitmap, 0);
			newWidth = newWidth / 2;
			newHeight = newHeight / 2;
			if (newWidth <= 0 || newHeight <= 0)
				break;

			Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newWidth,
					newHeight, true);
			bitmap.recycle();
			bitmap = newBitmap;
			level++;
		}

		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		bitmap.recycle();
	}

	public void reload() {
		if(fileBitmap == null)
			load();
		else
			load(fileBitmap);
		
		bind();
		setFilters(minFilter, magFilter);
		glGraphics.getGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
	}

	public void setFilters(int minFilter, int magFilter) {
		this.minFilter = minFilter;
		this.magFilter = magFilter;
		GL10 gl = glGraphics.getGL();
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				minFilter);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				magFilter);
	}

	public void bind() {
		GL10 gl = glGraphics.getGL();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
	}

	public void dispose() {
		GL10 gl = glGraphics.getGL();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
		int[] textureIds = { textureId };
		gl.glDeleteTextures(1, textureIds, 0);
		if (fileBitmap != null)
			fileBitmap.recycle();
	}
}