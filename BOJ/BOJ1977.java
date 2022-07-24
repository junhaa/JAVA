import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1977 완전제곱수
public class Main {
	public static void solution(int M, int N) {
		boolean flag = false;
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for(int i = 1 ; i <= 100 ; i ++) {
			int tmp = i * i ;
			if(tmp >= M && tmp <= N) {
				if(flag) 	sum += tmp;
				else {
					flag = true;
					min = i * i;
					sum += tmp;
				}
			}
		}
		if(!flag) System.out.println(-1);
		else {
			System.out.println(sum);
			System.out.println(min);
		}
	}
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		T.solution(M, N);
	}
}
