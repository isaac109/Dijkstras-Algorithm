
public class Edge {
	public Node target;
	public Node origion;
	public final double weight;
	
	public Edge(Node _target, Node _origion, double _weight)
	{
		target = _target;
		origion = _origion;
		weight = _weight;
	}

}
