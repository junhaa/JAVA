import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #21758 꿀 따기
public class Main {
	
	static int[] prefixr, prefixl, arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		int minr = Integer.MAX_VALUE;
		int minl = Integer.MAX_VALUE;
		arr = new int[N];
		prefixr = new int[N];
		prefixl = new int[N];
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		for(int i = 1 ; i < N ; i ++) {
			prefixr[i] = prefixr[i - 1] + arr[i];
			minr = Math.min(prefixr[i] + arr[i], minr);
			prefixl[N - 1 - i] = prefixl[N - i] + arr[N - 1 - i];
			minl = Math.min(prefixl[N - 1 - i] + arr[N - 1 - i], minl);
		}
		answer = Math.max(answer, prefixr[N - 1] * 2 - minr);
		answer = Math.max(answer, prefixl[0] * 2 - minl);
		answer = Math.max(answer, prefixr[N - 1] - arr[N - 1] + max);
		System.out.println(answer);
	}

}
