public class DLinkedNode<T> {
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;
    
    public DLinkedNode(T data, double prio) {
        this.dataItem = data;
        this.priority = prio;
        this.next = null;
        this.prev = null;
    }
    
    public DLinkedNode() {
        this.dataItem = null;
        this.priority = 0;
        this.next = null;
        this.prev = null;
    }
    
    public T getDataItem() {
        return this.dataItem;
    }
    
    public void setDataItem(T data) {
        this.dataItem = data;
    }
    
    public double getPriority() {
        return this.priority;
    }
    
    public void setPriority(double prio) {
        this.priority = prio;
    }
    
    public DLinkedNode<T> getNext() {
        return this.next;
    }
    
    public void setNext(DLinkedNode<T> nextNode) {
        this.next = nextNode;
    }
    
    public DLinkedNode<T> getPrev() {
        return this.prev;
    }
    
    public void setPrev(DLinkedNode<T> prevNode) {
        this.prev = prevNode;
    }
}

