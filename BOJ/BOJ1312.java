import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1312 ¼Ò¼ö
public class Main {
	public int solution(int A, int B, int N) {
		int rmd = A % B * 10, div = A / B, i = 0 ; // ³ª¸ÓÁö, ³ª´°¼À ¸ò
		while(++i <= N) {
			div = rmd / B;
			rmd = rmd % B * 10;
			
		}
		return div;
	}
	
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(A, B, N));
	}
}
