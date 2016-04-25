import java.util.List;
import java.util.ArrayList;

public class Dijkstra {
	public static void computePaths(Node source)
	{
		source.minDistance = 0;
		List<Node> nodeQueue = new ArrayList<Node>();
		nodeQueue.add(source);
		while(!nodeQueue.isEmpty()){
			Node node = nodeQueue.get(0);
			nodeQueue.remove(0);
			for(int i = 0; i < node.adjacencies.size(); i++)
			{
				Node target = null;
				Edge edge = node.adjacencies.get(i);
				double weight = edge.weight;
				double distanceThroughNode = node.minDistance + weight;
				if(node.name.equals(edge.target.name))
				{
					target = edge.origion;
				}
				else
				{
					target = edge.target;
				}
				if(distanceThroughNode<target.minDistance)
				{
					nodeQueue.remove(target);
					target.minDistance = distanceThroughNode;
					target.previous = node;
					nodeQueue.add(target);
				}
			}
		}
	}
	
}
