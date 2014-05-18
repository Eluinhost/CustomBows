package com.publicuhc.custombows.arrows;

import com.publicuhc.custombows.PitchYaw;
import org.bukkit.entity.Arrow;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Offset the arrow by the variance given
 */
public class RandomOffsetArrowBehaviour implements ArrowBehaviour {

    private final float m_yawVariance;
    private final float m_pitchVariance;
    private final Random m_random = new Random();
    private final Plugin m_plugin;

    public RandomOffsetArrowBehaviour(Plugin plugin, float yawVariance, float pitchVariance) {
        m_yawVariance = yawVariance;
        m_pitchVariance = pitchVariance;
        m_plugin = plugin;
    }

    private Vector addVariance(Vector v){
        PitchYaw original = getPitchYaw(v);

        float pitch = original.getPitch() + (m_random.nextFloat() * 2F * m_pitchVariance) - m_pitchVariance;
        float yaw = original.getYaw() + (m_random.nextFloat() * 2F * m_yawVariance) - m_yawVariance;

        return getVector(new PitchYaw(pitch, yaw)).multiply(v.length());
    }

    private Vector getVector(PitchYaw pitchYaw){
        Vector vector = new Vector();

        double rotX = pitchYaw.getYaw();
        double rotY = pitchYaw.getPitch();

        vector.setY(-Math.sin(Math.toRadians(rotY)));

        double xz = Math.cos(Math.toRadians(rotY));

        vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
        vector.setZ(xz * Math.cos(Math.toRadians(rotX)));

        return vector;
    }

    private PitchYaw getPitchYaw(Vector vector) {
        double _2PI = 2 * Math.PI;
        double x = vector.getX();
        double z = vector.getZ();

        if (x == 0 && z == 0) {
            float pitch = vector.getY() > 0 ? -90 : 90;
            return new PitchYaw(pitch,0F);
        }

        double theta = Math.atan2(-x, z);
        float yaw = (float) Math.toDegrees((theta + _2PI) % _2PI);

        double x2 = x * x;
        double z2 = z * z;
        double xz = Math.sqrt(x2 + z2);
        float pitch = (float) Math.toDegrees(Math.atan(-vector.getY() / xz));

        return new PitchYaw(pitch, yaw);
    }

    @Override
    public void processArrow(Arrow arrow) {
        arrow.setVelocity(addVariance(arrow.getVelocity()));
        arrow.setMetadata("ArrowType", new FixedMetadataValue(m_plugin, "SHOTGUN"));
    }
}
