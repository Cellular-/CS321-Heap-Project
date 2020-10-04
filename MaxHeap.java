import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public MaxHeap() {
        this.heap = new ArrayList<T>();
    }

    public MaxHeap(ArrayList<T> arr) {
        heap = arr;
        this.buildHeap(arr);
    }

    public int getSize() {
        return heap.size();
    }

    public void insert(T elem) {
        heap.add(elem);

        if(heap.size() > 0 ) {
            maxHeapifyUp(heap.size() - 1);
        }
    }

    public T extractMax() {
        assert heap.size() > 0 : "Heap is empty";
        T root = heap.get(0);

        swap(heap, 0, heap.size() - 1);
        heap.remove(heap.size() - 1);

        if(heap.size() > 0) {
            maxHeapifyDown(0);
        }

        return root;
    }

    /**
     * When a new element is inserted into the heap, swap elements
     * until the max heap properties are restored. Sometimes, the newly
     * added element preserves the max heap properties so do no work.
     * 
     * Otherwise, swap elements until max heap properties are restored.
     */
    public void maxHeapifyUp(int index) {
        while( (index != 0) && (heap.get(index).compareTo(heap.get(parentIndex(index)))) > 0 ) {
            swap(heap, index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private void maxHeapifyDown(int index) {
        assert index >= 0 && index < heap.size();

        while (!isLeaf(index)) {
            int j = leftChildIndex(index);

            if ( (j < heap.size() - 1) && (heap.get(j).compareTo(heap.get(j + 1)) < 0) ) {
                j++;
            }

            if (heap.get(index).compareTo(heap.get(j)) >= 0) {
                return;
            }
            
            swap(heap, index, j);
            index = j;
        }
    }

    public void buildHeap(ArrayList<T> arr) {
        for(int i = (arr.size() / 2) - 1; i >= 0; i--) {
            maxHeapifyDown(i);
        }
    }

    private int parentIndex(int index) {
        assert index > 0: "Position has no parent";

        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        assert index < heap.size() / 2 : "Position has no left child";

        return (2 * index + 1);
    }

    private int rightChildIndex(int index) {
        assert index < (heap.size() - 1) / 2 : "Position has no right child";

        return (2 * index + 2);
    }

    private boolean isLeaf(int index) {
        return (index >= heap.size() / 2 && index < heap.size());
    }

    private void swap(ArrayList<T> heap, int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public T get(int index) {
        return heap.get(index);
    }
}
