/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.game;

import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.*;

public class ElytraRace {

    private final UUID uuid;
    private String name;
    private Location waiting;
    private Location start;
    private Location returnLocation;
    private State state;

    private final List<Circle> circles;

    private transient List<Player> players;

    public ElytraRace(final String name){
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.state = State.PRACTICE;
        this.circles = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public ElytraRace(UUID uuid, String name, Location waiting, Location start, Location returnLocation, State state, List<Circle> circles) {
        this.uuid = uuid;
        this.name = name;
        this.waiting = waiting;
        this.start = start;
        this.returnLocation = returnLocation;
        this.state = state;
        this.circles = circles;
        this.players = new ArrayList<>();
    }

    public void refreshCirclesId(){
        for (int i = 0; i < this.getCircles().size(); i++) {
            this.getCircles().get(i).setId(i);
        }
    }

    public void progress(){
        if(this.players == null)
            this.players = new ArrayList<>();
        for (final Circle circle : this.getCircles()) {
            circle.render();
            for (final Player player : ElytraPlugin.getInstance().getServer().getOnlinePlayers()) {
                if (circle.isLocationInCircle(player.getLocation())) {
                    player.setVelocity(player.getVelocity().add(player.getLocation().getDirection().clone().multiply(circle.getSpeed())));
                }
            }
        }
    }

    public void destroy() {
        this.players.clear();
        ElytraPlugin.getInstances().remove(this);
        try {
            GameManager.remove(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Circle> getCircles() {
        return circles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPracticeMode() {
        return this.state.equals(State.PRACTICE);
    }

    public State getState() {
        return this.state;
    }

    public void setState(final State state) {
        this.state = state;
    }

    public Location getWaiting() {
        return waiting;
    }

    public void setWaiting(Location waiting) {
        this.waiting = waiting;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(Location returnLocation) {
        this.returnLocation = returnLocation;
    }
}
