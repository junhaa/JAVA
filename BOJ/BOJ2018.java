import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2018 수들의 합 5
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int lt = 0;
		int rt = 0;
		int answer = 1;
		int sum = 0;
		while(rt < N) {
			if(sum < N) {
				sum += ++rt;
			}
			else if(sum > N){
				sum -= lt;
				lt ++;
			}
			else {
				answer ++;
				sum += ++rt;
			}
		}
		System.out.println(answer);
	}
}
