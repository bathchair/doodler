package com.example.doodler;

import android.graphics.Path;

public class DrawStroke {

    public int color;
    public int strokeWidth;
    public Path path;

    public DrawStroke(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}