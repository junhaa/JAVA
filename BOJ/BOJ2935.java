import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// BOJ #2935 공
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger A = new BigInteger(br.readLine());
		boolean isMul = false;
		if (br.readLine().charAt(0) == '*') {
			isMul = true;
		} else {
			isMul = false;
		}
		BigInteger B = new BigInteger(br.readLine());
		System.out.println(isMul ? A.multiply(B) : A.add(B));
	}
}
