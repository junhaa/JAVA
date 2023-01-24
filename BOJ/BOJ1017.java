import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1017 소수 쌍
public class Main {

	static ArrayList<Integer>[] adj;
	static ArrayList<Integer> A = new ArrayList<>(), B = new ArrayList<>();
	static int[] Ato, Bto;
	static boolean[] visited, ch;
	static int first;
	static boolean firstOdd = false;
	
	public boolean dfs(int a) {
		visited[a] = true;
		for(int b : adj[a]) {
			if(Bto[b] == 0) continue;
			if(Bto[b] == -1 || !visited[Bto[b]] && dfs(Bto[b])) {
				Ato[a] = b;
				Bto[b] = a;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int first = Integer.parseInt(st.nextToken());
		if(first % 2 == 0) {
			A.add(first);
			firstOdd = false;
		}
		else { 
			B.add(first);
			firstOdd = true;
		}
		for(int i = 1 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp % 2 == 0) A.add(tmp);
			else B.add(tmp);
		}
		if(A.size() != B.size()) {
			System.out.println(-1);
			return;
		}
		adj = new ArrayList[N / 2];
		for(int i = 0 ; i < N / 2 ; i ++) {
			adj[i] = new ArrayList<>();
		}
		ch = new boolean[2001];
		ch[0] = true;
		ch[1] = true;
		for(int i = 2 ; i <= Math.sqrt(2000) ; i ++) {
			for(int j = i * i ; j <= 2000 ; j += i) {
				ch[j] = true;
			}
		}
		if(firstOdd) {
			ArrayList<Integer> tmp = A;
			A = B;
			B = tmp;
		}
		for(int i = 0 ; i < N / 2 ; i ++) {
			int tmp = A.get(i);
			for(int j = 0 ; j < N / 2 ; j ++) {
				if(!ch[tmp + B.get(j)]) adj[i].add(j);
			}
		}
		ArrayList<Integer> answer = new ArrayList<>();
		for(int x : adj[0]){
			int cnt = 1;
			
			Ato = new int[N / 2];
			Bto = new int[N / 2];
			Arrays.fill(Ato, -1);
			Arrays.fill(Bto, -1);
			
			Ato[0] = x;
			Bto[x] = 0;
			
			for(int i = 1 ; i < N / 2 ; i ++) {
				if(Ato[i] == -1) {
					visited = new boolean[N / 2];
					if(T.dfs(i)) cnt ++;
				}
			}
			if(cnt == N / 2) answer.add(B.get(x));
		}
		if(answer.size() == 0) {
			System.out.println(-1);
			return;
		}
		Collections.sort(answer);
		for(int x : answer) {
			sb.append(x + " ");
		}
		System.out.println(sb);
	}
}
