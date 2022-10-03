import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #2150 Strongly Connected Component
public class Main {
	static ArrayList<Integer>[] list;
	static boolean[] finished;
	static int[] n, sn; // 부여한 노드 번호 
	static int cnt = 1, scnt = 0;
	static ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
	static Stack<Integer> stack = new Stack<>();
	
	static int DFS(int i) {
		n[i] = cnt ++;
		stack.push(i);
		
		int result = n[i];
		for(int next : list[i]) {
			if(n[next] == 0) { // 방문하지 X
 				result = Math.min(result, DFS(next));
			}
			else if(!finished[next]) { // scc에 속해있지 X
				result = Math.min(result, n[next]);
			}
		}
		
		if(result == n[i]) { // 자신이 부모일 때
			ArrayList<Integer> sList = new ArrayList<>();
			while(true) {
				int tmp = stack.pop();
				sList.add(tmp);
				sn[tmp] = scnt;
				finished[tmp] = true;
				if(tmp == i) break;
			}
			Collections.sort(sList);
			scc.add(sList);
			scnt ++;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		list = new ArrayList[V + 1];
		n = new int[V + 1];
		sn = new int[V + 1];
		finished = new boolean[V + 1];
		for(int i = 1 ; i <= V ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < E ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
		}
		for(int i = 1 ; i <= V ; i ++) {
			if(n[i] == 0) {
				T.DFS(i);
			}
		}
		sb.append(scnt + "\n");
		Collections.sort(scc, (o1, o2) -> o1.get(0) - o2.get(0));
		for(ArrayList<Integer> t : scc) {
			for(int x : t) {
				sb.append(x + " ");
			}
			sb.append("-1" + "\n");
		}
		System.out.println(sb);
	}
}
