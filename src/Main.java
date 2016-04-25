import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static List<Node> nodes = new ArrayList<Node>();
	
	public static void generateNodes(String line)
	{
		String delims = "[,]";
		String[]tokens = line.split(delims);
		if(nodes.isEmpty())
		{
			Node tempNode = new Node(tokens[0]);
			Node tempNode2 = new Node(tokens[2]);
			double weight = Double.parseDouble(tokens[1]);
			Edge tempEdge = new Edge(tempNode2, tempNode, weight);
			tempNode.adjacencies.add(tempEdge);
			tempNode2.adjacencies.add(tempEdge);
			nodes.add(tempNode);
			nodes.add(tempNode2);
			return;
		}
		else
		{
			Node tempNode = null;
			Node tempNode2 = null;
			Edge tempEdge = null;
			double weight = Double.parseDouble(tokens[1]);
			for(int i = 0; i < nodes.size(); i++)
			{
				if(nodes.get(i).name.equals(tokens[0]))
				{
					tempNode = nodes.get(i);
				}
				if(nodes.get(i).name.equals(tokens[2]))
				{
					tempNode2 = nodes.get(i);
				}
			}
			if(tempNode == null)
			{
				tempNode = new Node(tokens[0]);
				nodes.add(tempNode);
			}
			if(tempNode2 == null)
			{
				tempNode2 = new Node(tokens[2]);
				nodes.add(tempNode2);
			}
			tempEdge = new Edge(tempNode2, tempNode, weight);
			tempNode.adjacencies.add(tempEdge);
			tempNode2.adjacencies.add(tempEdge);
		}
	}
	
	
	public static void printGraph()
	{
		for(int i = 0; i < nodes.size(); i++)
		{
			Node temp = nodes.get(i);
			System.out.println("Node" + temp.name);
			for(int j = 0; j < temp.adjacencies.size(); j++)
			{
				System.out.println(temp.adjacencies.get(j).origion.name);
				System.out.println(temp.adjacencies.get(j).weight);
				System.out.println(temp.adjacencies.get(j).target.name);
			}
		}
	}
	
	public static List<Node> getShortestPathTo(Node target)
	{
		List<Node> path = new ArrayList<Node>();
		for(Node node = target; node != null; node = node.previous)
		{
			path.add(node);
		}
		if(path.isEmpty())
		{
			path.add(target);
		}
		Collections.reverse(path);
		return path;
	}
	
	public static Node findNode(String name)
	{
		for(Node n : nodes)
		{
			if(n.name.equals(name))
			{
				return n;
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException
	{
		String fileName = "graph.txt";
		String line = null;
		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine())!= null)
			{
				System.out.println(line);
				generateNodes(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not found");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		//printGraph();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println("What is the name of the starting node?");
			String name = br.readLine();
			Node start = findNode(name);
			if(start == null)
			{
				System.out.println("Please enter the correct name of an exsisting node.");
			}
			else
			{
				System.out.println("What is the name of the ending node?");
				name = br.readLine();
				Node end = findNode(name);
				if(end == null)
				{
					System.out.println("Please enter the correct name of an exsisting node.");
				}
				else
				{
					Dijkstra.computePaths(start);
					System.out.println("Distance to " + end.name + ": "+ end.minDistance);
			        List<Node> path = getShortestPathTo(end);
			        System.out.println("Path: " + path);
				}
			}
		}
	}
}
