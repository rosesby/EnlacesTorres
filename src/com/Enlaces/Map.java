package com.Enlaces;

import java.util.ArrayList;

public class Map {
    public ArrayList<City> cities;

    public Map() {
        cities = new ArrayList<City>();
    }

    public City createCity(String name) {
        City city = new City(name);
        cities.add(city);
        return city;
    }

    public void createRelation(City city1, City city2){
        city1.addLink(city2);
    }

    public boolean checkIfTowerExistsByName(String name) {
        return cities.stream().anyMatch(city -> city.getName().equals(name));
    }

    public class City {
        private ArrayList<City> linkedCities;
        private String name;

        public City(String name) {
            this.name = name;
            linkedCities = new ArrayList<City>();
        }

        public void addLink(City city) {
            linkedCities.add(city);
        }

        public void closeLink(City city) {
            linkedCities.remove(city);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            String consoleOut = name + " tiene conexiones a las siguiente ciudades\n";
            for (City city : cities) {
                consoleOut += city.getName() + "\n";
            }
            return consoleOut;
        }
    }

}
