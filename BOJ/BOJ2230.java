import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2230 수 고르기 
public class Main {

	static int[] arr;
	
	
	static int solution(int N, int M) {
		int min = Integer.MAX_VALUE;
		int lt = 0;
		int rt = 0;
		int dif = arr[rt] - arr[lt];
		while(lt < N - 1) {
			if(dif < M) {
				rt ++;
				if(rt == N) break;
				dif = arr[rt] - arr[lt];
			}
			else {
				lt ++;
				dif = arr[rt] - arr[lt];
			}
			if(dif >= M) {
				min = Math.min(min, dif);
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		System.out.println(T.solution(N, M));
	}
}
