import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2908 »ó¼ö
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int inA = Integer.parseInt(st.nextToken());
		int inB = Integer.parseInt(st.nextToken());
		int numA = 0, numB = 0;
		for(int i = 0 ; i < 3 ; i ++) {
			numA += (inA / (int)(Math.pow(10, 2 - i))) * (int)Math.pow(10, i);
			inA %= (int)Math.pow(10, 2 - i);
			numB += (inB / (int)(Math.pow(10, 2 - i))) * (int)Math.pow(10, i);
			inB %= (int)Math.pow(10, 2 - i);
		}
		System.out.println(Math.min(numA, numB));
	}
}
