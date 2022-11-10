import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #1822 차집
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0 ; i < M ; i ++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		int answer = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			if(!set.contains(A[i])) {
				list.add(A[i]);
				answer ++;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(answer + "\n");
		Collections.sort(list);
		for(int x : list) {
			sb.append(x + " ");
		}
		System.out.println(sb);
	}
}
