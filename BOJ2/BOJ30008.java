import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #30008 준영이의 등급 
public class Main {
	static int[] arr;

	public static void main(String[] a) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		arr = new int[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < K; i++) {
			sb.append(getGrade(N, i) + " ");
		}
		System.out.println(sb);
	}

	static int getGrade(int N, int idx) {
		int p = arr[idx] * 100 / N;
		if (p <= 4) {
			return 1;
		} else if (4 < p && p <= 11) {
			return 2;
		} else if (11 < p && p <= 23) {
			return 3;
		} else if (23 < p && p <= 40) {
			return 4;
		} else if (40 < p && p <= 60) {
			return 5;
		} else if (60 < p && p <= 77) {
			return 6;
		} else if (77 < p && p <= 89) {
			return 7;
		} else if (89 < p && p <= 96) {
			return 8;
		} else {
			return 9;
		}
	}
}
