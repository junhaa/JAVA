import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ #2606 바이러스
public class Main {
	static int[] union;
	
	public static int Find(int x) {
		if(x == union[x]) return x;
		else return union[x] = Find(union[x]);
	}
	
	public static void Union(int a, int b) {
		int fa = Find(a);
		int fb = Find(b);
		if(fa != fb) union[fa] = fb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		union = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			union[i] = i;
		}
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			T.Union(Math.min(tmp1, tmp2), Math.max(tmp1, tmp2));
		}
		for(int i = 2 ; i < N + 1 ; i ++) {
			if(Find(1) == Find(i)) answer++;
		}
		System.out.println(answer);
	}
}
