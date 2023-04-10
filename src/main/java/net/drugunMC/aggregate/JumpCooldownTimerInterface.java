package net.drugunMC.aggregate;


public interface JumpCooldownTimerInterface {

    default public int getJumpPreventionTicks() {
        return 0;
    }

    default public void setJumpPreventionTicks(int in) {
        return;
    }

}
