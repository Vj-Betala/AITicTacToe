public interface PriorityQueueInterface<E extends Comparable>
{
	public boolean add(E o);
	public boolean offer(E o);
	public E poll();
	public E remove();
	public int size();
	public boolean empty();
	public void clear();
	public E get(int x);
	public E set(int x, E item);
	public E peek();
	public boolean isEmpty();
	public E element();
}