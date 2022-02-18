public interface HeapInterface<E extends Comparable>
{
	public boolean add(E item); //
	public E remove();
	public boolean isEmpty(); //
	public int size();
	public E get(int x);
	public void clear();
}