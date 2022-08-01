import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

// BOJ #1940 аж╦Ы
public class Main {
	static int[] arr;
	static int N, M;
	static int solution(int N, int M) {
		int answer = 0;
		for(int i = 0 ; i < N - 1 ; i ++) {
			for(int k = i + 1 ; k < N ; k ++) {
				if(arr[i] + arr[k] == M) answer ++;
			}
		}
		return answer;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(T.solution(N,M));
	}
}
