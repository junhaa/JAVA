import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// BOJ #30664 Loteria Falha 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String input = br.readLine();
			if (input.equals("0")) {
				break;
			}
			BigInteger cur = new BigInteger(input);
			if (cur.mod(new BigInteger("42")).toString() == "0") {
				sb.append("PREMIADO\n");
			} else {
				sb.append("TENTE NOVAMENTE\n");
			}
		}
		System.out.println(sb);

	}
}
