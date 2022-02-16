public class HeapNode<T> implements Comparable {
    private T data;
    private Integer priority;
    public HeapNode(T data, Integer priority)
    {
        this.data  = data;
        this.priority  = priority;
    }
    public T getData()
    { return data; }
    public void setData(T data)
    { this.data = data; }
    public Integer getPriority()
    { return priority; }
    public void setPriority(Integer priority)
    {  this.priority  = priority; }
    public int compareTo(Object o)
    {
        HeapNode<T> other = (HeapNode<T>)o;
        return priority.compareTo(other.getPriority());
    }
    public String toString()
    {
        return "("+data+", "+priority+")";
    }
}
