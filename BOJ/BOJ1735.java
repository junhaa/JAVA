import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ #1735 ºÐ¼ö ÇÕ
public class Main {
	public static int gcd(int a, int b) {
		int n;
		while(b != 0) {
			n = a % b;
			a = b;
			b = n;
		}
		return a;
	}
	
	public static void solution(int numA, int denA, int numB, int denB) {
		int ansDen = denA * denB;
		int ansNum = (numA * (ansDen / denA) + numB * (ansDen / denB));
		int gcd = gcd(ansDen, ansNum);
		System.out.print(ansNum / gcd + " " + ansDen / gcd);
		
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numA = Integer.parseInt(st.nextToken());
		int denA = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int numB = Integer.parseInt(st.nextToken());
		int denB = Integer.parseInt(st.nextToken());
		T.solution(numA, denA, numB, denB);
	}
}
