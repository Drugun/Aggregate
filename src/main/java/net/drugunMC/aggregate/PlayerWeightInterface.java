package net.drugunMC.aggregate;


public interface PlayerWeightInterface {

    default public int getArmourWeight() {
        return 0;
    }

    default public void setArmourWeight(int in) {
        return;
    }

}
