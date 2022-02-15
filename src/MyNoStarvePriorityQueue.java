public class MyNoStarvePriorityQueue<E> extends MyPriorityQueue<PriorityNode<E>>{

    public MyNoStarvePriorityQueue() {
        super();
    }

    @Override
    public boolean add(PriorityNode<E> item) {
        for (int i = 0; i < size(); i++) {
            if(get(i).getPriority() > item.getPriority()){
                listData.add(i,item);
                return true;
            }
        }

        listData.add(item);
        return true;
    }

    @Override
    public PriorityNode<E> remove() {
        PriorityNode<E> rtrn = super.remove();
        for (int i = 0; i < size(); i++) {
            PriorityNode<E> node = super.get(i);
            super.get(i).setPriority(node.getPriority() - 1);
        }

        return rtrn;
    }
}
