import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// BOJ #8437 Julka
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BigInteger a = new BigInteger(br.readLine());
		BigInteger b = new BigInteger(br.readLine());
		BigInteger subtract = a.subtract(b);
		BigInteger divide = subtract.divide(new BigInteger("2"));
		sb.append(divide.add(b).toString() + "\n" + divide.toString());
		System.out.println(sb);
	}
}
