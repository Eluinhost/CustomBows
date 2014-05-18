package com.publicuhc.custombows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.Random;

public class RandomOffsetArrow implements Runnable {

    private static final Random m_random = new Random();

    private final Vector m_baseVector;
    private final Location m_location;
    private final ProjectileSource m_shooter;

    public RandomOffsetArrow(Vector vector, Location shootLocation, ProjectileSource shooter){
        m_baseVector = vector;
        m_location = shootLocation;
        m_shooter = shooter;
    }

    @Override
    public void run() {
        Arrow a = (Arrow) m_location.getWorld().spawnEntity(m_location, EntityType.ARROW);
            a.setVelocity(addVariance(m_baseVector));
            a.setShooter(m_shooter);
            a.setMetadata("ArrowType", new FixedMetadataValue(CustomBows.getInstance(), "SHOTGUN"));
            a.setFireTicks(10000);
        }

    private Vector addVariance(Vector v){

        PitchYaw original = getPitchYaw(v);

        float pitch = original.getPitch() + (m_random.nextFloat()*20F)-10F;
        float yaw = original.getYaw() + (m_random.nextFloat()*20F)-10F;

        return getVector(new PitchYaw(pitch, yaw)).multiply(v.length());
    }

    private class PitchYaw{
        private float pitch;
        private float yaw;
        public PitchYaw(float pitch,float yaw){
            this.pitch = pitch;
            this.yaw = yaw;
        }

        public float getYaw() {
            return yaw;
        }

        public float getPitch() {
            return pitch;
        }
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

        return new PitchYaw(pitch,yaw);
    }
}
