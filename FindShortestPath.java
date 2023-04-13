import java.io.*;

public class FindShortestPath {

    public static void main(String[] args) throws IOException, InvalidDungeonCharacterException {
        
        Dungeon dungeon = new Dungeon("input.txt");
        Hexagon start = dungeon.getStart();

        DLPriorityQueue<Hexagon> queue = new DLPriorityQueue<>();
        start.setDistanceToStart(0);
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            Hexagon current = queue.dequeue();
            if (current.isExit()) {
                System.out.println(current.getDistanceToStart());
                return;
            }
            for (int i = 0; i < 6; i++) {
                try {
                    Hexagon neighbour = current.getNeighbour(i);
                    if (neighbour != null && !neighbour.isWall() && !neighbour.isMarkedEnqueued()) {
                        int newDistance = current.getDistanceToStart() + 1;
                        if (newDistance < neighbour.getDistanceToStart()) {
                            neighbour.setDistanceToStart(newDistance);
                            neighbour.setPredecessor(current);
                        }
                        neighbour.markEnqueued();
                        queue.enqueue(neighbour);
                    }
                } catch (InvalidNeighbourIndexException e) {
                    // do nothing, continue with next index
                }
            }
            current.markDequeued();
        }
        throw new RuntimeException("No path to exit was found");
    }
}
