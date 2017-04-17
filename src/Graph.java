import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	
	private final int MAX_VERT = 20;
	
	private Vertex vertexList[];
	
	private int adjMat[][];
	
	private int nVerts;
	
	public Graph() {
		vertexList = new Vertex[MAX_VERT];
		adjMat = new int[MAX_VERT][MAX_VERT];
		nVerts = 0;
		for (int i = 0; i < MAX_VERT; i++) {
			for (int j = 0; j < MAX_VERT; j++) {
				adjMat[i][j] = 0;
			}
			
		}
	}
	
	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}
	
	public void  addEdge(int from, int to) {
		adjMat[from][to] = 1;
		adjMat[to][from] = 1;
	}
	
	public void displayVertex(int v) {
		System.out.print(" "+vertexList[v].label+" ");
	}
	
	public int getAdjUnvisitedVertex(int v) {
		for (int i = 0; i < nVerts; i++) {
			if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false) 
				return i;
		}
		return -1;
	}
	
	public void dfs() {
		
		Stack<Integer> stack = new Stack<>();
		vertexList[0].wasVisited = true;
		displayVertex(0);
		stack.push(0);
		
		while(!stack.isEmpty()){
			int v = getAdjUnvisitedVertex(stack.peek());
			if(v == -1){
				stack.pop();
			}else{
				vertexList[v].wasVisited = true;
				displayVertex(v);
				stack.push(v);
			}
		}
		
		for (int i = 0; i < nVerts; i++) {
			vertexList[i].wasVisited = false;
		}
	}
	
	public void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		vertexList[0].wasVisited = true;
		displayVertex(0);
		queue.offer(0);
		int v2;
		while (!queue.isEmpty()) {
			int v1 = queue.poll();
			while ((v2=getAdjUnvisitedVertex(v1)) !=-1) {
				vertexList[v2].wasVisited = true;
				displayVertex(v2);
				queue.offer(v2);
			}
		}
		for (int i = 0; i < nVerts; i++) {
			vertexList[i].wasVisited = false;
		}
		
	}

	
/*	
			B-------C
   	       /
          /
         / 
        A
         \
          \
           \
            D-------E
*/	
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addVertex('A'); //0
		graph.addVertex('B'); //1
		graph.addVertex('C'); //2
		graph.addVertex('D'); //3
		graph.addVertex('E'); //4
		
		
		graph.addEdge(0, 1); //AB
		graph.addEdge(1, 2); //BC
		graph.addEdge(0, 3); //AD
		graph.addEdge(3, 4); //DE
		
		System.out.print("DFS: ");
		graph.dfs();
		System.out.println();
		System.out.print("BFS: ");
		graph.bfs();
	}
}


class Vertex{
	
	public char label;
	public boolean wasVisited;
	
	public Vertex(char label) {
		this.label = label;
		wasVisited = false;
	}
}