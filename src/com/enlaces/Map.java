package com.enlaces;

import util.Consola;

import java.util.*;

public class Map {
    private ArrayList<City> cities;

    public Map() {
        cities = new ArrayList<City>();
    }

    public void createCity(String name) {
        City city = new City(name);
        cities.add(city);
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

    public void createCityIfDoesNotExists(String strCity) {
        boolean check = checkIfTowerExistsByName(strCity);
        if (!check) createCity(strCity);
    }

    //Run Depth First Search
    public String searchWayToCityDFS(String strCity1, String strCity2) {
        System.out.println(Consola.Color.BLUE + "Depth First Search started for " + Consola.Color.RESET + strCity1 + Consola.Color.BLUE + " to " + Consola.Color.RESET + strCity2);
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);

        Stack<City> searchStack = new Stack<City>();
        ArrayList<City> visitedCities = new ArrayList<City>();

        searchStack.add(city1); //add first node to queue

        while (searchStack.size() > 0) {
            visitedCities.add(searchStack.peek()); //add actual node to visited nodes
            Optional<City> result = searchStack.peek().getLinkedCities().stream()  //Check for new nodes in the edges of the actual node
                    .filter(city -> !(visitedCities.contains(city)))
                    .findFirst();
            if (result.isPresent()) {
                searchStack.push(result.get());
                if (searchStack.contains(city2)) return Consola.Color.GREEN + "TRUE" + Consola.Color.RESET;
            } else searchStack.pop(); //remove last node from stack if doesnt have new unprocessed edges
        }
        return Consola.Color.RED + "False" + Consola.Color.RESET;
    }

    //Run Breadth First Search
    public String searchWayToCityBFS(String strCity1, String strCity2) {
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);

        Queue<City> searchQueue = new LinkedList<City>();
        ArrayList<City> visitedCities = new ArrayList<City>();

        searchQueue.add(city1); //add first node to queue
        while (!searchQueue.isEmpty()) { //While the queue has nodes
            if (!searchQueue.peek().getLinkedCities().isEmpty()) { //Check if actual node has vertexes
                searchQueue.peek().getLinkedCities().forEach(city -> { //Check for all node if has not been processed or added to the queue before
                    if (!(visitedCities.contains(city) || searchQueue.contains(city))) searchQueue.add(city); //Check node for adding to queue
                });
                if (searchQueue.contains(city2)) return Consola.Color.GREEN + "+"; //Check result
            }
            visitedCities.add(searchQueue.peek()); //add actual node to visited nodes
            searchQueue.poll(); //remove actual (first) node from queue
        }
        return Consola.Color.RED + "-";
    }

    public void closeConnectionBetween(String strCity1, String strCity2) {
        City city1 = getExistingCityByName(strCity1);
        City city2 = getExistingCityByName(strCity2);

        if ((city1 == null) || (city2 == null)) return;
        city1.linkedCities.remove(city2);
    }

    public class City {
        private ArrayList<City> linkedCities;
        private String name;

        public City(String name) {
            this.name = name;
            linkedCities = new ArrayList<City>();
        }

        public void addLink(City city) {
            if (city.getName() == this.name) System.out.println("City cannot be linked to itself");
            if (!linkedCities.contains(city)){
                linkedCities.add(city);
            }
        }

        public ArrayList<City> getLinkedCities() {
            return linkedCities;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
