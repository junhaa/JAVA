import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1300 K번째 수
public class Main {

	private long solve(int N, int k){
		long lt = 1;
		long rt = (long)N * N;
		long mid;
		long answer = -1L;
		while(lt <= rt){
			mid = (lt + rt) / 2;
			long sum = 0;
			for(int i = 1 ; i <= N ; i ++){
				sum += Math.min(mid / i, N);
			}
			if(sum >= k){
				answer = mid;
				rt = mid - 1;
			}
			else{
				lt = mid + 1;
			}
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		System.out.println(main.solve(N, k));
	}
}
