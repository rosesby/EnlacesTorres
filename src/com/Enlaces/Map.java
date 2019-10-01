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

    public void createRelation(String strCity1, String strCity2) {
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);
        city1.addLink(city2);
    }

    public boolean checkIfTowerExistsByName(String strCityName) {
        return cities.stream().
                anyMatch(city -> city.getName().equals(strCityName));
    }

    public City getExistingCityByName(String strCityName) {
        return cities.stream()
                .filter(city -> city.getName().equals(strCityName))
                .findFirst()
                .get();
    }

    public class City {
        private ArrayList<City> linkedCities;
        private String name;

        public City(String name) {
            this.name = name;
            linkedCities = new ArrayList<City>();
        }

        public void addLink(City city) {
            if(city.getName() == this.name) System.out.println("City cannot be linked to itself");
            if(linkedCities.contains(city)) System.out.println("Link from " + this.name + " to " + city.getName() + " already exists");
            else {linkedCities.add(city);
            System.out.println(this.name + " was linked to " + city.getName());}
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
