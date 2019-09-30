package com.Enlaces;

import java.util.ArrayList;

public class Map {
    public ArrayList<Tower> towers;

    public Tower createTower(String name) {
        Tower tower = new Tower(name);
        towers.add(tower);
        return tower;
    }

    public class Tower {
        public ArrayList<Tower> linkedTowers;

        public String getName() {
            return name;
        }

        private String name;

        public Tower(String name) {
            this.name = name;
        }

        public void addLink(Tower tower) {
            linkedTowers.add(tower);
        }

        public void closeLink(Tower tower) {
            linkedTowers.remove(tower);
        }

        @Override
        public String toString() {
            String consoleOut = name + " tiene conexiones a las siguiente ciudades\n";
            for (Tower tower : towers){
                consoleOut += tower.getName() + "\n";
            }
           return consoleOut;
        }
    }

}
