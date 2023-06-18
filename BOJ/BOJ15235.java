import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #15235 Olympiad Pizza
class Pizza{
	int cnt, num;
	public Pizza(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Pizza> Q = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			Q.add(new Pizza(i, Integer.parseInt(st.nextToken())));
		}
		int c = 1;
		while(!Q.isEmpty()) {
			Pizza tmp = Q.poll();
			if(--tmp.cnt != 0) { 
				Q.add(tmp);
			}
			else {
				arr[tmp.num] = c;
			}
			c ++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb);
	}
}
