/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.game;

import fr.bakaaless.ElytraRace.utils.VectorUtils;
import net.minecraft.server.v1_15_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_15_R1.ParticleParam;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_15_R1.CraftParticle;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class Circle {

    private int id;
    private Location location;
    private double radius;
    private Color color;
    private double speed;
    private double depth;
    private boolean showHitBox;

    private transient Location[] points;
    private transient Set<int[]> edges;

    public Circle(final int id, final Location location, final double radius, final Color color, final double speed, final double depth, final boolean showHitBox) {
        this.id = id;
        this.location = location;
        this.radius = radius;
        this.color = color;
        this.speed = speed;
        this.depth = depth;
        this.showHitBox = showHitBox;
        this.points = this.getHitboxPoints();
    }

    public void render(){
        final int particles = 16;
        final double twoPI = 2*Math.PI;
        final Vector nv = this.getLocation().getDirection().normalize();
        final Vector ya = VectorUtils.perp(nv, new Vector(0, 1, 0)).normalize();
        final Vector xa = ya.getCrossProduct(nv).normalize();
        nv.multiply(-1);
        for (double theta = 0; theta < particles; theta += twoPI / particles) {
            double angle = twoPI * theta / particles + Math.random() % (twoPI / particles);
            double ax = Math.cos(angle) * radius;
            double az = Math.sin(angle) * radius;
            double xi = xa.getX() * ax + ya.getX() * az;
            double yi = xa.getY() * ax + ya.getY() * az;
            double zi = xa.getZ() * ax + ya.getZ() * az;
            double x = xi;
            double y = yi;
            double z = zi;
            final Location location = this.getLocation().clone().add(new Vector(x, y, z));
            location.getWorld().spawnParticle(Particle.REDSTONE, location, 1, new Particle.DustOptions(this.color, 1));
        }
        if (this.showHitBox) {
            if(this.points == null)
                this.points = this.getHitboxPoints();
            int i = 0;
            for (final Location point : this.points) {
                point.getWorld().spawnParticle(Particle.REDSTONE, point, 1, new Particle.DustOptions((i == 0 ? Color.RED : ((i == 1 || i == 2 || i == 4) ? Color.ORANGE : Color.NAVY)), 1));
                i++;
            }

            for (int[] index : this.edges) {
                final Location point1 = this.points[index[0]];
                final Location point2 = this.points[index[1]];
                point2.getWorld().spawnParticle(Particle.REDSTONE, point2, 1, new Particle.DustOptions(Color.NAVY, 1));
                final Vector vector = VectorUtils.from(point2, point1);
                for(int j = 1; j <= 5; j++){
                    final Location tempLoc = point2.clone().add(j * vector.getX()/5, j * vector.getY()/5, j * vector.getZ()/5);
                    tempLoc.getWorld().spawnParticle(Particle.REDSTONE, tempLoc, 1, new Particle.DustOptions(Color.AQUA, 1));
                }
            }
        }
    }

    public Location[] getHitboxPoints(){
        final Location[] points = new Location[8];
        final Vector nv = this.getLocation().getDirection().normalize();
        final Vector ya = VectorUtils.perp(nv, new Vector(0, 1, 0)).normalize();
        final Vector xa = ya.getCrossProduct(nv).normalize();
        nv.multiply(-this.depth);
        final Vector toMiddlePoint = new Vector(nv.getX() * this.depth, this.depth * nv.getY(), this.depth * nv.getZ());
        double xb = xa.getX() * radius + ya.getX() * radius;
        double yb = xa.getY() * radius + ya.getY() * radius;
        double zb = xa.getZ() * radius + ya.getZ() * radius;
        double xc = -xa.getX() * radius + ya.getX() * radius;
        double zc = -xa.getZ() * radius + ya.getZ() * radius;
        double xd = xa.getX() * radius - ya.getX() * radius;
        double yd = xa.getY() * radius - ya.getY() * radius;
        double zd = xa.getZ() * radius - ya.getZ() * radius;
        double xe = -xa.getX() * radius + -ya.getX() * radius;
        double ze = -xa.getZ() * radius + -ya.getZ() * radius;
        points[0] = this.location.clone().add(toMiddlePoint).add(xb, yb, zb);
        points[1] = this.location.clone().add(toMiddlePoint).add(xc, yb, zc);
        points[2] = this.location.clone().add(toMiddlePoint).add(xd, yd, zd);
        points[3] = this.location.clone().add(toMiddlePoint).add(xe, yd, ze);
        points[4] = this.location.clone().subtract(toMiddlePoint).add(xb, yb, zb);
        points[5] = this.location.clone().subtract(toMiddlePoint).add(xc, yb, zc);
        points[6] = this.location.clone().subtract(toMiddlePoint).add(xd, yd, zd);
        points[7] = this.location.clone().subtract(toMiddlePoint).add(xe, yd, ze);

        if(this.edges == null)
            this.edges = new HashSet<>();
        else
            this.edges.clear();
        this.edges.add(new int[]{0, 1});
        this.edges.add(new int[]{0, 2});
        this.edges.add(new int[]{0, 4});
        this.edges.add(new int[]{1, 5});
        this.edges.add(new int[]{1, 3});
        this.edges.add(new int[]{2, 6});
        this.edges.add(new int[]{2, 3});
        this.edges.add(new int[]{3, 7});
        this.edges.add(new int[]{4, 5});
        this.edges.add(new int[]{4, 6});
        this.edges.add(new int[]{5, 7});
        this.edges.add(new int[]{6, 7});


        return points;
    }

    public boolean isLocationInCircle(final Location location){
        if (!this.location.getWorld().equals(location.getWorld()))
            return false;
        if(this.points == null)
            this.points = this.getHitboxPoints();
        final Vector vectorI = VectorUtils.from(this.points[0], this.points[1]);
        final Vector vectorJ = VectorUtils.from(this.points[0], this.points[2]);
        final Vector vectorK = VectorUtils.from(this.points[0], this.points[4]);
        final Vector vectorV = VectorUtils.from(this.points[0], location);
        return (0D < vectorV.dot(vectorI) && vectorV.dot(vectorI) < vectorI.dot(vectorI))
                && (0D < vectorV.dot(vectorJ) && vectorV.dot(vectorJ) < vectorJ.dot(vectorJ))
                && (0D < vectorV.dot(vectorK) && vectorV.dot(vectorK) < vectorK.dot(vectorK));
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        this.points = this.getHitboxPoints();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.points = this.getHitboxPoints();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
        this.points = this.getHitboxPoints();
    }

    public boolean isShowingHitBox() {
        return showHitBox;
    }

    public void setShowHitBox(boolean showHitBox) {
        this.showHitBox = showHitBox;
    }

}
