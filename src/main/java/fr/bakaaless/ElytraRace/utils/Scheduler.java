/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.utils;

import fr.bakaaless.ElytraRace.game.ElytraRace;
import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;

import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    private final Timer timer;

    public Scheduler(){
        this.timer = new Timer();
    }

    public void start(){
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() { ElytraPlugin.getInstances().forEach(ElytraRace::progress);
            }
        }, 0L, 50L);
    }

    public void stop(){
        this.timer.cancel();
    }
}
