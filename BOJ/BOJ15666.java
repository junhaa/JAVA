import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #15666 N°ú M (12)
public class Main {
	static ArrayList<Integer> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static int M;
	
	static void backTracking(int L, int max) {
		if(L == M) {
			for(int i = 0 ; i < M ; i ++) {
				sb.append(arr[i] + " ");
			}
			sb.append('\n');
			return;
		}
		for(int x : list) {
			if(x < max) continue;
			arr[L] = x;
			backTracking(L + 1, Math.max(max, x));
			arr[L] = 0;
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		HashSet<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		for(int x : set) {
			list.add(x);
		}
		Collections.sort(list);
		T.backTracking(0, Integer.MIN_VALUE);
		System.out.println(sb);
	}
}
