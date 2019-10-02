package com.Enlaces;

import com.sun.source.tree.NewArrayTree;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public void createCityIfDoesNotExists(String strCity){
        boolean check = checkIfTowerExistsByName(strCity);
        if(check){
            System.out.println("Already Exists");
        }
        else{
            createCity(strCity);
            System.out.println("City was created");
        }
    }

    public boolean checkIfCanTravelFromTo(String strCity1, String strCity2){
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);

        for (City city : city1.linkedCities){
            searchWayToCity(city1,city2);
        }
        return false;
    }

    //Breadth First Search
    public boolean searchWayToCityBFS(String strCity1, String strCity2) {
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);
        return true;
    }

    //Depth First Search
    public boolean searchWayToCityDFS(String strCity1, String strCity2){
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);

        Queue<City> actualSearch = new LinkedList<City>();
        ArrayList<City> visitedCities = new ArrayList<City>();

        actualSearch.add(city1); //add first node to queue
        while (visitedCities.size()<cities.size()) {
            //add childs to queue
            city1.getLinkedCities().forEach(city -> actualSearch.add(city));
            //remove actual node from queue
            System.out.println("The first element of the queue is delted");
            actualSearch.remove();

            //add actual node to visited nodes


            if (actualSearch.contains(city2)) return true;
        }
        return false;
    }

    public boolean searchWayToCity(City city1, City city2){
        boolean res = city1.linkedCities.stream().anyMatch(city -> city.linkedCities.contains(city2));
        return res;
    }

    public void closeAllConnectionsBetween(String strCity1, String strCity2){
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);

        //TODO apply exception istead of if
        if(city1.linkedCities.contains(city2)) city1.linkedCities.remove(city2);
        if(city2.linkedCities.contains(city1)) city2.linkedCities.remove(city1);

        System.out.println("All connections deleted between " + city1 + " & " + city2);
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

        public ArrayList<City> getLinkedCities() {
            return linkedCities;
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
