package com.sulphur.cellautomaton.system;

import java.util.ArrayList;
import java.util.Arrays;

public class Population {
    Cell[] cells;
    private int width;
    private int height;

    protected ArrayList<Integer> Br = new ArrayList<>();
    protected ArrayList<Integer> Sr = new ArrayList<>();

    public Population(int width, int height, int B, int S){
        this.width = width;
        this.height = height;
        cells = new Cell[width * height];
        regenerate();
        while(B > 0){
            Br.add(B % 10);
            B /= 10;
        }
        while(S > 0){
            Sr.add(S % 10);
            S /= 10;
        }
        if(S == 0) {
            Sr.add(0);
        }
    }

    public void updatePopulationState(){
        for(int y = 1; y < height - 1; y++){
            for(int x = 1; x < width - 1; x++){
                int result = 8;
                result -= cells[(x - 1) + (y - 1) * width].isPopulated() ? 0 : 1;
                result -= cells[(x    ) + (y - 1) * width].isPopulated() ? 0 : 1;
                result -= cells[(x + 1) + (y - 1) * width].isPopulated() ? 0 : 1;
                result -= cells[(x - 1) + (y    ) * width].isPopulated() ? 0 : 1;
                result -= cells[(x + 1) + (y    ) * width].isPopulated() ? 0 : 1;
                result -= cells[(x - 1) + (y + 1) * width].isPopulated() ? 0 : 1;
                result -= cells[(x    ) + (y + 1) * width].isPopulated() ? 0 : 1;
                result -= cells[(x + 1) + (y + 1) * width].isPopulated() ? 0 : 1;
                cells[x + y * width].updateNeighbours(result);
            }
        }
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                cells[x + y * width].updateCell(this);
            }
        }
    }

    public void regenerate(){
        for(int i = 0 ; i< cells.length; i++){
            this.cells[i] = new Cell((int)Math.round(Math.random()) == 1);
        }
    }
}
