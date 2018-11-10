package com.sulphur.cellautomaton.system;

import com.sulphur.cellautomaton.graphics.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main extends AbstractRules {

    private boolean freeze = false;
    private boolean dark = false;

    private static int WIDTH;
    private static int HEIGHT;
    private static float SCALE;

    private static int RULESETB;
    private static int RULESETS;

    private Input in;

    private static Population p;


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
        if(in.isKeyDown(KeyEvent.VK_R)) {
            p.regenerate();
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

    public Main(){
        try {
            List<String> instructions = Files.readAllLines(Paths.get("/home/default/IdeaProjects/untitled/src/com/sulphur/cellautomaton/system/instructions.txt"), StandardCharsets.UTF_8);
            WIDTH = Integer.parseInt(instructions.get(0));
            HEIGHT = Integer.parseInt(instructions.get(1));
            SCALE = Float.parseFloat(instructions.get(2));
            RULESETB = Integer.parseInt(instructions.get(3));
            RULESETS = Integer.parseInt(instructions.get(4));
        } catch(IOException e) {
            System.out.println("Couldn't find instructions, reverting to defaults");
            WIDTH = 125;
            HEIGHT = 85;
            SCALE = 2.0f;
            RULESETB = 3;
            RULESETS = 12345;
        }
    }

    public static void main(String[] args){

        Manager manager = new Manager(new Main(),WIDTH,HEIGHT,SCALE);
        p = new Population(WIDTH,HEIGHT,RULESETB,RULESETS);
        manager.start();
    }
}
