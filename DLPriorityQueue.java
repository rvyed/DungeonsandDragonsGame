public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
    
    private DLinkedNode<T> front;
    private DLinkedNode<T> rear;
    private int count;
    
    public DLPriorityQueue() {
        this.front = null;
        this.rear = null;
        this.count = 0;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DLinkedNode<T> current = front;
        while (current != null) {
            sb.append(current.getDataItem());
            current = current.getNext();
        }
        return sb.toString();
    }
    

    public void add(T dataItem, double priority) {
        DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            DLinkedNode<T> current = front;
            while (current != null && current.getPriority() <= priority) {
                current = current.getNext();
            }
            if (current == null) {
                newNode.setPrev(rear);
                rear.setNext(newNode);
                rear = newNode;
            } else if (current == front) {
                newNode.setNext(front);
                front.setPrev(newNode);
                front = newNode;
            } else {
                newNode.setPrev(current.getPrev());
                newNode.setNext(current);
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
            }
        }
        count++;
    }
    
    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
        DLinkedNode<T> current = front;
        while (current != null && !current.getDataItem().equals(dataItem)) {
            current = current.getNext();
        }
        if (current == null) {
            throw new InvalidElementException("Data item not found in priority queue");
        } else {
            current.setPriority(newPriority);
            if (current == front && current == rear) {
                // only one node in queue, do nothing
            } else if (current == front) {
                // first node in queue, move to correct position
                front = front.getNext();
                front.setPrev(null);
                add(dataItem, newPriority);
            } else if (current == rear) {
                // last node in queue, move to correct position
                rear = rear.getPrev();
                rear.setNext(null);
                add(dataItem, newPriority);
            } else {
                // middle node in queue, move to correct position
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                add(dataItem, newPriority);
            }
        }
    }
            
    public T remove() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Priority queue is empty.");
        }
        T removedItem = front.getDataItem();
        if (count == 1) {
            front = null;
            rear = null;
        } else {
            front = front.getNext();
            front.setPrev(null);
        }
        count--;
        return removedItem;
    }
    
    public T peek() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Priority queue is empty.");
        }
        return front.getDataItem();
    }

    public T removeMin() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Priority queue is empty.");
        }
        T dataItem = front.getDataItem();
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front.getNext().setPrev(null);
            front = front.getNext();
        }
        count--;
        return dataItem;
    }
    
    public DLinkedNode<T> getRear() {
        return rear;
    }
    
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public int size() {
        return count;
    }

    public void enqueue(Hexagon start) {
    }

    public Hexagon dequeue() {
        return null;
    }
}