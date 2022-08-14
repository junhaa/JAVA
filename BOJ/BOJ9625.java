import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #9625 BABBA
class AB{
	int A;
	int B;
	public AB(int a, int b){
		A = a;
		B = b;
	}
}
public class Main {
	static AB solution(int K) {
		AB tmp = new AB(1,0);
		for(int i = 0 ; i < K ; i ++) {
			int cur = tmp.A;
			tmp.A = tmp.B;
			tmp.B += cur;
		}
		return tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		AB answer = T.solution(K);
		System.out.println(answer.A + " " + answer.B);
	}
}
