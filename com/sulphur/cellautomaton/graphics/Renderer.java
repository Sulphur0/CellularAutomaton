package com.sulphur.cellautomaton.graphics;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pW, pH;
    private int[] p;

    public Renderer(Manager manager){
        pW = manager.getWidth();
        pH = manager.getHeight();

        p = ((DataBufferInt)manager.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear(){
        for(int i  = 0; i < p.length; i++){
            p[i] = 0x00000000;
        }
    }

    public void setPixel(int x, int y, int color){
        if(x < 0 || x >= pW || y < 0 || y >= pH){
            return;
        }
        p[x + y * pW] = color;
    }

    public void setPixel(int i, int color){
        p[i] = color;
    }

    // Getters
    public int getWidth() { return pW; }
    public int getHeight() { return pH; }
    public int getPixelCount() { return p.length; }
}
