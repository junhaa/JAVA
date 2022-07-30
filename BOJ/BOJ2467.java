import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ #2467 용액
public class Main { 
	static int[] arr;
	static int answerlt, answerrt;
	public static void solution(int N) { // 투포인터
		if(arr[0] > 0) {
			answerlt = arr[0];
			answerrt = arr[1];
			return;
		}
		else if(arr[N - 1] < 0) {
			answerrt = arr[N - 1];
			answerlt = arr[N - 2];
			return;
		}
		
		int lt = 0, rt = N - 1, min = Integer.MAX_VALUE;
		while(lt < rt) {
			int sum = arr[lt] + arr[rt];
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				answerlt = arr[lt];
				answerrt = arr[rt];
			}
			if(sum >= 0) rt --;
			else lt ++;
		}
		
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
		T.solution(N);
		System.out.println(answerlt + " " + answerrt);
	}
}
