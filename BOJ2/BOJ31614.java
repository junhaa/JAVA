import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #31614 分 (Minutes)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		sum += Integer.parseInt(br.readLine()) * 60;
		sum += Integer.parseInt(br.readLine());

		System.out.println(sum);
	}

}
