import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17610 양팔저울 
public class Main {

	static int[] arr, pm;
	static boolean[] ch; // pm true = + , false = -
	static int N;
	
	static void BackTraking(int i) {
		if(i == N) {
			int sum = 0;
			for(int j = 0 ; j < N ; j ++) {
				sum += arr[j] * pm[j];
			}
			if(sum > 0) {
				ch[sum] = true;
			}
			return;
		}
		pm[i] = 1;
		BackTraking(i + 1);
		pm[i] = 0;
		BackTraking(i + 1);
		pm[i] = -1;
		BackTraking(i + 1);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		pm = new int[N];
		int sum = 0;
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		ch = new boolean[sum + 1];
		
		int answer = 0;
		T.BackTraking(0);
		for(int i = 1 ; i <= sum ; i ++) {
			if(!ch[i]) answer ++;
		}
		System.out.println(answer);
	}
}
