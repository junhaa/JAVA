import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1671 상어의 저녁식사
class Shark implements Comparable<Shark> {
	int size, speed, intel;
	long sum;
	public Shark(int size, int speed, int intel) {
		this.size = size;
		this.speed = speed;
		this.intel = intel;
		this.sum = (long)size + speed + intel;
	}
	@Override
	public int compareTo(Shark o) {
		if(this.sum > o.sum) return 1;
		else if(this.sum < o.sum) return -1;
		else return 0;
	}
}
public class Main {

	static ArrayList<Shark> slist = new ArrayList<>();
	static ArrayList<Shark> A = new ArrayList<>(), B = new ArrayList<>();
	static ArrayList<Integer>[] adj;
	static int[] Ato, Bto;
	static boolean[] visited;
	
	public static boolean dfs(int a) {
		visited[a] = true;
		for(int b : adj[a]) {
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
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			Shark tmp = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			A.add(tmp);
			B.add(tmp);
		}
		adj = new ArrayList[N * 2]; // 0 - based
		Collections.sort(A);
		Collections.sort(B);
		for(int i = 0 ; i < N * 2 ; i ++) adj[i] = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < i ; j ++) {
				if(A.get(i).size >= A.get(j).size && A.get(i).speed >= A.get(j).speed && A.get(i).intel >= A.get(j).intel) {
					adj[2 * i].add(j);
					adj[2 * i + 1].add(j);
				}
			}
		}
		int cnt = 0;
		Ato = new int[N * 2];
		Bto = new int[N];
		Arrays.fill(Ato, -1);
		Arrays.fill(Bto, -1);
		
		for(int i = 0 ; i < N * 2 ; i ++) {
			if(Ato[i] == -1) {
				visited = new boolean[N * 2];
				if(T.dfs(i)) cnt ++;
			}
		}
		System.out.println(N - cnt);
	}
}
