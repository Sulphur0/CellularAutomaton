package com.sulphur.cellautomaton.graphics;

public abstract class AbstractRules {

    public abstract void update(Manager manager, float deltaTime);
    public abstract void render(Manager manager, Renderer renderer);

}
