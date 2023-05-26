package org.naarad.muni.graph;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/bus-routes/">Bus Route</a>
 */
public class BusRoute {
    public static void main(String[] args) {
        final BusRoute route = new BusRoute();
        System.out.println(
                route.numBusesToDestination(new int[][]{{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}}, 15, 12));
    }

    private static final int BFS_LEVEL_END = -1;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        final Map<Integer, List<Integer>> busStopToRouteGraph = createRouteGraph(routes);
        int minRoutes = 1;

        if (!busStopToRouteGraph.containsKey(source) || !busStopToRouteGraph.containsKey(target)) {
            return -1;
        }

        if (source == target) {
            return 0;
        }

        final Set<Integer> processing = new HashSet<>();
        final Queue<Integer> bfs = new LinkedList<>();
        final Set<Integer> alreadyVisitedRows = new HashSet<>();
        bfs.add(source);
        processing.add(source);

        for (int connectedRoutes : busStopToRouteGraph.get(source)) {
            if (!alreadyVisitedRows.contains(connectedRoutes)) {
                for (int j = 0; j < routes[connectedRoutes].length; j++) {
                    int currentBusStop = routes[connectedRoutes][j];
                    if (currentBusStop == target) {
                        return minRoutes;
                    } else if (!processing.contains(currentBusStop)) {
                        processing.add(currentBusStop);
                        bfs.add(currentBusStop);
                    }
                }
            } else {
                alreadyVisitedRows.add(connectedRoutes);
            }
        }

        bfs.remove();
        if (!bfs.isEmpty()) {
            bfs.add(BFS_LEVEL_END);
            minRoutes++; // because we are now in the next level
        } else {
            return -1;
        }

        while (!bfs.isEmpty()) {
            while (!bfs.isEmpty() && bfs.peek() != BFS_LEVEL_END) {
                int busStop = bfs.remove();

                for (int connectedRoutes : busStopToRouteGraph.get(busStop)) {
                    if (!alreadyVisitedRows.contains(connectedRoutes)) {
                        for (int j = 0; j < routes[connectedRoutes].length; j++) {
                            int currentBusStop = routes[connectedRoutes][j];
                            if (currentBusStop == target) {
                                return minRoutes;
                            } else if (!processing.contains(currentBusStop)) {
                                processing.add(currentBusStop);
                                bfs.add(currentBusStop);
                            }
                        }
                    } else {
                        alreadyVisitedRows.add(connectedRoutes);
                    }
                }
            }

            if (bfs.isEmpty()) {
                return -1;
            }

            if (bfs.peek() == BFS_LEVEL_END) {
                bfs.remove();
            }

            if (!bfs.isEmpty()) {
                bfs.add(BFS_LEVEL_END);
                minRoutes++;
            }
        }

        return -1;
    }

    private Map<Integer, List<Integer>> createRouteGraph(int[][] routes) {
        final Map<Integer, List<Integer>> routeGraph = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int currentNode = routes[i][j];

                if (!routeGraph.containsKey(currentNode)) {
                    routeGraph.put(currentNode, new ArrayList<>());
                }

                routeGraph.get(currentNode).add(i);
            }
        }
        return routeGraph;
    }

}
