package com.sulphur.cellautomaton.system;

public class Cell {
    private boolean populated;
    private int neighbours;

    public Cell(boolean populated){
        this.populated = populated;
    }

    public void updateNeighbours(int neighbourCount){
        neighbours = neighbourCount;
    }

    public void updateCell(){
        if(populated){
            if(neighbours < 2 || neighbours > 3){
                populated = false;
                return;
            }
        }
        if(neighbours == 3){
            populated = true;
        }
    }

    public boolean isPopulated() { return populated; }
}
