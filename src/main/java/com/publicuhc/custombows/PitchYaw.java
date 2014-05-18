package com.publicuhc.custombows;

public class PitchYaw{

    private final float m_pitch;
    private final float m_yaw;

    public PitchYaw(float pitch,float yaw) {
        this.m_pitch = pitch;
        this.m_yaw = yaw;
    }

    public float getYaw() {
        return m_yaw;
    }

    public float getPitch() {
        return m_pitch;
    }
}