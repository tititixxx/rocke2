package action;

import base.GameObject;

public class ActionAdapter implements Action {

    @Override
    public boolean run(GameObject owner) {
        return false;
    }

    @Override
    public void reset() {

    }

    public void add(){

    }
}
