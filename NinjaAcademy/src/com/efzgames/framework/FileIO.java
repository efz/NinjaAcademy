package com.efzgames.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Typeface;

public interface FileIO {
	
	public Typeface readFont(String fileName);
	
    public InputStream readAsset(String fileName) throws IOException;

    public InputStream readFile(String fileName) throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;
}
