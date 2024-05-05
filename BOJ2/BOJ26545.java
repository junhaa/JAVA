import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #26545 Mathematics
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long sum = 0;
		int n = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < n ; i++){
			sum += Integer.parseInt(br.readLine());
		}
		System.out.println(sum);
	}
}
