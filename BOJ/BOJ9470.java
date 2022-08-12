import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #9470 Strahler 순서

class Edge{
	int node;
	int num;
	public Edge(int node, int num) {
		this.node = node;
		this.num = num;
	}
}
public class Main {
	static int[] id;
	static ArrayList<Integer>[] list, num;
	
	static int solution(int M) {
		Queue<Edge> Q = new LinkedList<>();
		for(int i = 1 ; i <= M ; i ++) {
			if(id[i] == 0) {  
				num[i].add(1);
				Q.add(new Edge(i, 1));
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			if(Q.isEmpty()) return -1;
				Edge tmp = Q.poll();
				if(i == M - 1) return num[tmp.node].get(0);
				for(int x : list[tmp.node]) {
					if(num[x].isEmpty()) num[x].add(tmp.num);
					else if (num[x].get(0) < tmp.num) { 
						num[x].clear();
						num[x].add(tmp.num);
					}
					else if(num[x].get(0) == tmp.num) num[x].add(tmp.num);
					if(--id[x] == 0) {
						if(num[x].size() > 1) {
							int tmpnum = num[x].get(0);
							num[x].clear();
							num[x].add(tmpnum + 1);
						}
						Q.add(new Edge(x, num[x].get(0)));
					}
			}	
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 데스트 번호 
			int M = Integer.parseInt(st.nextToken()); // 노드의 수
			int P = Integer.parseInt(st.nextToken()); // 간선의 수
			id = new int[M + 1];
			num = new ArrayList[M + 1];
			list = new ArrayList[M + 1];	
			for(int i = 1 ; i <= M ; i ++) { 
				list[i] = new ArrayList<>();
				num[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < P ; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				list[start].add(end);
				id[end] ++;
			}
			sb.append(K + " ").append(T.solution(M)).append('\n');
		}
		System.out.println(sb);
	}
}
