import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #24568 Cupcake Party
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(Integer.parseInt(br.readLine()) * 8 + Integer.parseInt(br.readLine()) * 3 - 28);
	}
}
