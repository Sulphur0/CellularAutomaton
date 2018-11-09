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

    public void updateCell(Population p){
        if(populated){
            for(Integer i : p.Sr){
                if(neighbours == i){
                    return;
                }
            }
            populated = false;
            return;
        }
        for(Integer i : p.Br){
            if(neighbours == i){
                populated = true;
            }
        }
    }

    public boolean isPopulated() { return populated; }
    public void setPopulated(boolean b) { populated = b; }
}
