import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// BOJ #2407 а╤гу
public class Main {
	static BigInteger[][] dy;
	public BigInteger combi(int n, int r) {
		if(dy[n][r] != null) return dy[n][r];
		if(n == r || r == 0) return dy[n][r] = new BigInteger("1");
		if(r == 1) return dy[n][r] = new BigInteger(String.valueOf(n));
		return dy[n][r] = combi(n - 1, r).add(combi(n - 1, r - 1));
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		dy = new BigInteger[n + 1][r + 1];
		System.out.println(T.combi(n,r));
	}
}
