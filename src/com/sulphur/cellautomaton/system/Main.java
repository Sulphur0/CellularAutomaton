package com.sulphur.cellautomaton.system;

import com.sulphur.cellautomaton.graphics.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main extends AbstractRules {

    private boolean freeze = false;
    private boolean dark = true;

    private final static int WIDTH = 125;
    private final static int HEIGHT = 85;
    private final static float SCALE = 6.0f;

    private Input in;

    /*
    nice patterns:
        B1357/S1357     -> replicator
        B2/S            -> seeds
        B2/S0           -> Live Free Or Die
        B3/S012345678   -> Life w/o death
        B3/S12          -> flock
        B3/S12345       -> maze
        B3/S23          -> GOL
        B36/S125        -> 2x2
        B368/S245       -> Move
        B38/S23         -> Pedestrian
    */
    private Population p = new Population(WIDTH,HEIGHT,3,23);


    @Override
    public void update(Manager manager, float deltaTime) {
        in = manager.getInput();
        try {
            if (in.isMouseButton(MouseEvent.BUTTON1)) {
                p.cells[in.getMouseX() + in.getMouseY() * manager.getWidth()].setPopulated(true);
            }
            if (in.isMouseButton(MouseEvent.BUTTON3)) {
                p.cells[in.getMouseX() + in.getMouseY() * manager.getWidth()].setPopulated(false);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe){
            System.out.println("Tried to place a cell in an illegal spot! : [" + in.getMouseX() + "," + in.getMouseY() + "]");
        }
        if(in.isKeyDown(KeyEvent.VK_SPACE)){
            freeze = !freeze;
        }
        if(in.isKeyDown(KeyEvent.VK_E)){
            dark = !dark;
        }
        if(in.isKeyDown(KeyEvent.VK_Q)){
            for(int i = 0; i < p.cells.length; i++){
                p.cells[i].setPopulated(false);
            }
        }
        if(freeze || in.isKeyDown(KeyEvent.VK_X)){
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
        Manager manager = new Manager(new Main(),WIDTH,HEIGHT,SCALE);
        manager.start();
    }
}
