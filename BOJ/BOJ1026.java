import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1026 º¸¹°
public class Main {
	static int[] A,B;
	static int solution(int N) {
		int answer = 0;
		Arrays.sort(A);
		Arrays.sort(B);
		for(int i = 0 ; i < N ; i ++) {
			answer += A[i] * B[N - 1 - i];
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(T.solution(N));
	}
}
