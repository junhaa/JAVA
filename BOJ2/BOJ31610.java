import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #31610 飴の袋詰め (Drops Packing)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		sum += Integer.parseInt(br.readLine());
		sum *= Integer.parseInt(br.readLine());
		sum += Integer.parseInt(br.readLine());
		System.out.println(sum);

	}
}
