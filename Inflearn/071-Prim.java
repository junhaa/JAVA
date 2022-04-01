package codingTest_071_Prim;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
	public int vex, cost;
	Edge(int vex,int cos){
		this.vex = vex;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge ob) {
		return this.cost - ob.cost;
	}
}

public class Main {
	public static void main(String[] args) {
		ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		for(int i = 0 ; i <= V ; i ++) {
			graph.add(new ArrayList<Edge>());
		}
		for(int i = 0 ; i < E ; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			graph.get(a).add(new Edge(b,c));
			graph.get(b).add(new Edge(a,c)); // ¹æÇâÀÌ ¾ø±â ¶§¹®
			
		}
		int[] ch = new int[V+1];
		int answer = 0;	
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(1,0));
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			int ev = tmp.vex;
			if(ch[ev] == 0) {
				ch[ev] =1 ;
				answer += tmp.cost;
				for(Edge ob : graph.get(ev)) {
					if(ch[ob.vex] == 0) pQ.offer(new Edge(ob.vex, ob.cost));
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
}
