package org.naarad.muni.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/">Min Fuel Cost To capital</a>
 */
public class MinimumFuelCost {
    public static void main(final String[] args) {
        final MinimumFuelCost cost = new MinimumFuelCost();
        System.out.println(cost.minimumFuelCost(new int[][]{{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}}, 2));
    }

    public long minimumFuelCost(final int[][] roads, final int seats) {
        final boolean[] visited = new boolean[roads.length + 1];
        final Set<Integer>[] adjacencyList = convertGraphToAdjacencyList(roads);

        if (roads.length == 0) {
            return 0;
        }
        if (roads.length == 1) {
            return 1;
        }
        final Data cost = getMinimumFuelCost(adjacencyList, 0, visited, seats);
        return cost.fuelCost;
    }

    private Data getMinimumFuelCost(final Set<Integer>[] adjacencyList, final int currentNode, final boolean[] visited, final int seats) {
        visited[currentNode] = true;
        final List<Data> allDependentData = new ArrayList<>();
        final Data temp;
        for (final Integer city : adjacencyList[currentNode]) {
            if (!visited[city]) {
                allDependentData.add(getMinimumFuelCost(adjacencyList, city, visited, seats));
            }
        }

        if (allDependentData.isEmpty()) {
            temp = new Data(seats - 1);
        } else {
            int totalCars = 0;
            int totalVacantSeats = 0;
            long totalFuelCostSoFar = 0;

            for (final Data dependentData : allDependentData) {
                totalCars += dependentData.numberOfCars;
                totalVacantSeats += dependentData.vacantSeats;
                totalFuelCostSoFar += dependentData.fuelCost;
            }

            final int totalSeats = seats * totalCars;
            final int totalPassenger = totalSeats - totalVacantSeats + 1;
            int totalRequiredCars = totalPassenger / seats;
            totalRequiredCars = totalPassenger % seats == 0 ? totalRequiredCars : totalRequiredCars + 1;
            final long fuelCostIncludingThisCity = currentNode == 0 ? totalFuelCostSoFar : (long) totalRequiredCars + totalFuelCostSoFar;
            temp = new Data(totalRequiredCars, totalRequiredCars * seats - totalPassenger, fuelCostIncludingThisCity);
        }

        return temp;
    }

    private Set<Integer>[] convertGraphToAdjacencyList(final int[][] roads) {
        final Set<Integer>[] adjacencyList = new HashSet[roads.length + 1];

        for (final int[] road : roads) {
            final int citi1 = road[0];
            final int citi2 = road[1];

            if (adjacencyList[citi1] == null) {
                adjacencyList[citi1] = new HashSet<>();
            }
            if (adjacencyList[citi2] == null) {
                adjacencyList[citi2] = new HashSet<>();
            }

            adjacencyList[citi1].add(citi2);
            adjacencyList[citi2].add(citi1);
        }
        return adjacencyList;
    }

    class Data {
        int numberOfCars;
        int vacantSeats;
        long fuelCost;

        Data(final int cars, final int seat, final long cost) {
            numberOfCars = cars;
            vacantSeats = seat;
            fuelCost = cost;
        }

        Data(final int availableSeats) {
            numberOfCars = 1;
            vacantSeats = availableSeats;
            fuelCost = 1;
        }
    }
}
