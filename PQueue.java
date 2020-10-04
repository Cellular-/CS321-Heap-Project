/**
 * Priority queue implementation using MaxHeap.
 * 
 * @author Steven Lineses
 * @version 1.0
 */

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

    /**
     * To prevent starvation problem, the program will increase
     * the priority of processes that are not getting CPU time.
     * 
     * Once a process hits can no longer be delayed, its priority
     * is increased and it is pushed up the heap.
     * 
     * @param timeToIncrementPriority
     * @param maxPriority
     */
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