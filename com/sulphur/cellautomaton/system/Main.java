package com.sulphur.cellautomaton.system;

import com.sulphur.cellautomaton.graphics.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main extends AbstractRules {

    private boolean freeze = false;
    private boolean dark = true;

    private Population p = new Population(75,55);

    @Override
    public void update(Manager manager, float deltaTime) {
        try {
            if (manager.getInput().isMouseButton(MouseEvent.BUTTON1)) {
                p.cells[manager.getInput().getMouseX() + manager.getInput().getMouseY() * manager.getWidth()].setPopulated(true);
            }
            if (manager.getInput().isMouseButton(MouseEvent.BUTTON3)) {

                p.cells[manager.getInput().getMouseX() + manager.getInput().getMouseY() * manager.getWidth()].setPopulated(false);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe){
            System.out.println("Tried to place a cell in an illegal spot! : [" + manager.getInput().getMouseX() + "," + manager.getInput().getMouseY() + "]");
        }
        if(manager.getInput().isKeyDown(KeyEvent.VK_SPACE)){
            freeze = !freeze;
        }
        if(manager.getInput().isKeyDown(KeyEvent.VK_E)){
            dark = !dark;
        }
        if(manager.getInput().isKeyDown(KeyEvent.VK_Q)){
            for(int i = 0; i < p.cells.length; i++){
                p.cells[i].setPopulated(false);
            }
        }
        if(freeze){
            p.updatePopulationState();
        }
    }

    @Override
    public void render(Manager manager, Renderer renderer) {
        if(dark) {
            for (int i = 0; i < renderer.getPixelCount(); i++) {
                if (p.cells[i].isPopulated()) {
                    renderer.setPixel(i, 0xFFF3F3F3);
                } else {
                    renderer.setPixel(i, 0xFF0F0F0F);
                }
            }
        }else{
            for (int i = 0; i < renderer.getPixelCount(); i++) {
                if (p.cells[i].isPopulated()) {
                    renderer.setPixel(i, 0xFF0F0F0F);
                } else {
                    renderer.setPixel(i, 0xFFF3F3F3);
                }
            }
        }
    }

    public static void main(String[] args){
        Manager manager = new Manager(new Main());
        manager.start();
    }
}
