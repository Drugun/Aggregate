package net.drugunMC.aggregate;


public interface PlayerWeightInterface {

    default public boolean getHeavy() {
        return false;
    }

    default public void setHeavy(boolean in) {
        return;
    }

}
