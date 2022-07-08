import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2442 º° Âï±â - 5
public class Main {
	public void solution(int N) {
		for(int i = 1 ; i <= N ; i ++) {
			for(int k = 0 ; k < N - i ; k ++) { 
				System.out.print(" ");	
			}
			for(int k = 1 ; k <= 2 * i - 1; k ++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
	
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		T.solution(N);
	}

}
