import java.util.ArrayList;

public class MaxHeap<E extends Comparable> implements HeapInterface<E>{

    private ArrayList<E> listData;

    public MaxHeap() {
        listData = new ArrayList<>();
    }

    @Override
    public boolean add(E item) {

        if(isEmpty()) {
            listData.add(item);
        }


        else if(size() >= 1) {
            listData.add(item);
            int indData = listData.size()-1;
            int parentInd = (indData-1)/2;
            while(listData.get(indData).compareTo(listData.get(parentInd)) > 0){
                E initDataSwap = listData.get(parentInd);


                listData.set(parentInd, listData.get(indData));
                listData.set(indData , initDataSwap);

                indData = parentInd;
                parentInd = (indData-1)/2;

            }

        }

        return true;
    }

    public boolean insert(E item) {
        return add(item);
    }


    @Override
    public E remove() {
        E rmNode = listData.get(0);
        listData.set(0, (listData.get(size()-1)));
        listData.remove(size()-1);

        heapify();
        return rmNode;
    }

    public void heapify() {
        int node = 0;
        int leftChild =  (node * 2) + 1;
        int rightChild = (node * 2) + 2;

        while(((size() > leftChild) && listData.get(leftChild).compareTo(listData.get(node)) > 0)
                || ((size() > rightChild) && listData.get(rightChild).compareTo(listData.get(node)) > 0)){
            if( (size() <= rightChild) || (listData.get(leftChild).compareTo(listData.get(rightChild)) > 0)){
                E initialData = listData.get(node);
                listData.set(node, listData.get(leftChild));
                listData.set(leftChild, initialData);

                node = leftChild;
                leftChild = (node * 2) + 1;
                rightChild = (node * 2) + 2;
            } else {
                E initialData = listData.get(node);
                listData.set(node, listData.get(rightChild));
                listData.set(rightChild, initialData);

                node = rightChild;
                leftChild = (node * 2) + 1;
                rightChild = (node * 2) + 2;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return listData.isEmpty();
    }

    @Override
    public int size() {
        return listData.size();
    }

    @Override
    public E get(int x) {
        return listData.get(x);
    }

    @Override
    public void clear() {
        listData = new ArrayList<>();
    }

    @Override
    public String toString() {
        return listData.toString();
    }
}
