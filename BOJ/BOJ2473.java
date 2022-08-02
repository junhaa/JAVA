import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2473 ¼¼ ¿ë¾×
public class Main {
	static int[] arr;
	static int[] solution(int N) {
		int[] answer = new int[3];
		Arrays.sort(arr);
		long min = Long.MAX_VALUE;
		for(int i = 0 ; i < N - 2 ; i ++) {
			int lt = i + 1, rt = N - 1;
			while(lt < rt) {
				long value = (long)arr[i] + arr[lt] + arr[rt];
				if(Math.abs(value) < min) {
					answer[0] = arr[i];
					answer[1] = arr[lt];
					answer[2] = arr[rt];
					min = Math.abs(value);
				}
				if(value > 0) rt --;
				else lt ++;
			}
		}
		Arrays.sort(answer);
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int x : T.solution(N)) {
			System.out.print(x + " ");
		}
	}
}
