import java.io.IOException;

public class FindShortestPath {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java FindShortestPath inputFile");
            System.exit(1);
        }

        String inputFile = args[0];

        try {
            Dungeon dungeon = new Dungeon(inputFile);
            Hexagon start = dungeon.getStart();
            Hexagon exit = findShortestPath(dungeon, start);
            if (exit == null) {
                System.out.println("No path to the exit was found.");
            } else {
                printPath(exit);
            }
        } catch (InvalidDungeonCharacterException | IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static Hexagon findShortestPath(Dungeon dungeon, Hexagon start) {
        DLPriorityQueue<Hexagon> priorityQueue = new DLPriorityQueue<>();
        start.setDistanceToStart(0);
        priorityQueue.add(start, 0);
        start.markEnqueued();

        while (!priorityQueue.isEmpty()) {
            Hexagon current = priorityQueue.removeMin();
            current.markDequeued();

            if (current.isExit()) {
                return current;
            }

            if (current.isDragon() || hasDragonNeighbour(current)) {
                continue;
            }

            for (int i = 0; i < 6; i++) {
                try {
                    Hexagon neighbour = current.getNeighbour(i);
                    if (neighbour != null && !neighbour.isWall() && !neighbour.isMarkedDequeued()) {
                        int distance = current.getDistanceToStart() + 1;
                        if (distance < neighbour.getDistanceToStart()) {
                            neighbour.setDistanceToStart(distance);
                            neighbour.setPredecessor(current);

                            if (neighbour.isMarkedEnqueued()) {
                                priorityQueue.updatePriority(neighbour, distance + neighbour.getDistanceToExit(dungeon));
                            } else {
                                priorityQueue.add(neighbour, distance + neighbour.getDistanceToExit(dungeon));
                                neighbour.markEnqueued();
                            }
                        }
                    }
                } catch (InvalidNeighbourIndexException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
            }
        }

        return null;
    }

    public static boolean hasDragonNeighbour(Hexagon chamber) {
        for (int i = 0; i < 6; i++) {
            try {
                Hexagon neighbour = chamber.getNeighbour(i);
                if (neighbour != null && neighbour.isDragon()) {
                    return true;
                }
            } catch (InvalidNeighbourIndexException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
        return false;
    }

    private static void printPath(Hexagon exit) {
        int pathLength = 0;
        Hexagon current = exit;
    
        while (current != null) {
            pathLength++;
            current = current.getPredecessor();
        }
    
        System.out.println("Path Length: " + (pathLength));
    }
    
}
