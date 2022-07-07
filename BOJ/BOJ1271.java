import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public void solution(BigInteger n, BigInteger m) {
		System.out.println(n.divide(m));
		System.out.println(n.mod(m));
	}
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		BigInteger n = sc.nextBigInteger();
		BigInteger m = sc.nextBigInteger();
		T.solution(n, m);
		
	}
}
