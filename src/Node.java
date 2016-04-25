import java.util.List;
import java.util.ArrayList;

public class Node {
	public final String name;
	public List<Edge> adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Node previous;
	
	public Node(String _name)
	{
		name = _name;
		adjacencies = new ArrayList<Edge>();
	}
	
	public String toString()
	{
		return name;
	}
	
	public int compareTo(Node other)
	{
		return Double.compare(minDistance, other.minDistance);
	}
}
