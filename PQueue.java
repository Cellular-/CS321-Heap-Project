import java.util.Iterator;

public class PQueue {
    private MaxHeap<Process> heap;

    public PQueue() {
        this.heap = new MaxHeap<Process>();
    }

    public void enPQueue(Process elem) {
        heap.insert(elem);
    }

    public Process dePQueue() {
        return heap.extractMax();
    }
    
    public boolean isEmpty() {
        return heap.getSize() == 0;
    }

    public void update(int timeToIncrementPriority, int maxPriority) {
        for(int i = 0; i < heap.getSize(); i++) {
            heap.get(i).increaseTimeNotProcessed();

            if(heap.get(i).getTimeNotProcessed() >= timeToIncrementPriority) {
                heap.get(i).resetTimeNotProcessed();
                
                if(heap.get(i).getPriority() < maxPriority) {
                    heap.get(i).increasePriority();
                    heap.maxHeapifyUp(i);
                }
            }
        }
    }
}