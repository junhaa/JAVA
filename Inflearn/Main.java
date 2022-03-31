package codingTest_071;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class wonderLand implements Comparable<wonderLand>{
	public int v1, v2, cost;
	wonderLand(int v1, int v2, int cost){
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;	
	}
	@Override
	public int compareTo(wonderLand o) {
		return this.cost - o.cost;
	}

}

public class Main {
	static int unf[];
	
	public static int find(int x) {
		if(x == unf[x]) return x;
		else return unf[x] = find(unf[x]);
	}
	
	public static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa != fb) unf[fa] = unf[fb];
	}
	
	public int solution(ArrayList<wonderLand> arr) {
		int answer = 0;
		Collections.sort(arr);

		for(wonderLand o : arr) {
			if(find(o.v1) != find(o.v2)) {
			union(o.v1,o.v2);
			answer += o.cost;
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc =  new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		ArrayList<wonderLand> arr = new ArrayList<>();
		unf = new int[V+1];
		for(int k = 1 ; k < V+1; k++) {
			unf[k] = k;
		}
		for(int i = 0 ; i < E; i ++) {
			int v1, v2, cost;
			v1 = sc.nextInt();
			v2 = sc.nextInt();
			cost = sc.nextInt();
			arr.add(new wonderLand(v1,v2,cost));
		}
		System.out.println(T.solution(arr));
	}
}
