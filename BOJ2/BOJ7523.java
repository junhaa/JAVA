import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
// BOJ #7523 Gauß
public class Main {
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int i = 1 ; i <= T ; i ++){
			st = new StringTokenizer(br.readLine());
			sb.append("Scenario #" + i + ":\n");
			sb.append(main.solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n\n");
		}
		System.out.println(sb);
	}
	private BigInteger solve(int start, int end){
		BigInteger startB = new BigInteger(String.valueOf(start));
		BigInteger endB = new BigInteger(String.valueOf(end));

		return ((endB.subtract(startB).add(new BigInteger("1"))).multiply(startB.add(endB))).divide(new BigInteger("2"));
	}
}
