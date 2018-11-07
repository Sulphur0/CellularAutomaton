package com.sulphur.cellautomaton.system;

import com.sulphur.cellautomaton.graphics.*;

public class Main extends AbstractRules {

    private Cell[] cells;

    private Population p = new Population(75,55);

    @Override
    public void update(Manager manager, float deltaTime) {
        p.updatePopulationState();
    }

    @Override
    public void render(Manager manager, Renderer renderer) {
        for(int i = 0; i < renderer.getPixelCount(); i++){
            if(p.cells[i].isPopulated()){
                renderer.setPixel(i, 0xFFF3F3F3);
            }else{
                renderer.setPixel(i, 0xFF0F0F0F);
            }
        }
    }

    public static void main(String[] args){
        Manager manager = new Manager(new Main());
        manager.start();
    }
}
