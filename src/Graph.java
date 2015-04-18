import java.awt.List;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Graph {
	private int vertices;
	private Set<Node> nodes = new HashSet<Node>();
	private Set<Edge> edges = new HashSet<Edge>();

	public Graph (int vertices) 
	{
		this.vertices = vertices;
	}

	public void addVertex(Node node)
	{
		this.nodes.add(node);
	}

	public void addNodes (Set<Node> nodeList) 
	{
		this.nodes =  nodeList;
	}

	public void addEdge(Edge eg )
	{
		this.edges.add(eg);
	}

	public  Set  topologicalSort() 
	{
		Queue<Node> q = new LinkedList<Node>();
		Set<Node> topoSort = new LinkedHashSet<Node>();
		int vertexProcessesCtr = 0;
		for(Node m : this.nodes){
			addToQueueToposort(m,topoSort,vertexProcessesCtr,q);
		}
		while(!q.isEmpty()) {
			Node m = q.poll();
			for(Node child : m.adjacenctNode){
				int indeq = child.getInDegree()-1;
				child.setInDegree(indeq);
				addToQueueToposort(child,topoSort,vertexProcessesCtr,q);
			}
		}
		if(vertexProcessesCtr > this.vertices) {
			System.out.println();
		}
		return topoSort;
	}

	private void addToQueueToposort(Node node, Set topoSort, int vertexProcessesCtr, Queue<Node> q) 
	{
		if(node.getInDegree()==0){
			q.add(node);
			++vertexProcessesCtr;
			topoSort.add(node);
		}
	}
}

class Node 
{
	private int data;
	private int dist;
	private int inDegree;
	LinkedList<Node> adjacenctNode = new LinkedList<Node>( );

	public void addAdjNode(Node Child)
	{
		adjacenctNode.add(Child);
		Child.inDegree++;
	}

	public Node(int data) 
	{
		this.data = data;
	}

	public int getInDegree() 
	{
		return inDegree;
	}

	public void setInDegree(int inDegree) 
	{
		this.inDegree = inDegree;
	}

	@Override
	public String toString() 
	{
		return ""+  data ;
	}
}

class Edge
{
	private int distance;
	private Node src;
	private Node dest;
	public Edge(int dist, Node src, Node dest) 
	{
		this.distance = dist;
		this.src = src;
		this.dest = dest;
		src.addAdjNode(dest);
	}
}