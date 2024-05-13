import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #31606 果物 (Fruit)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(Integer.parseInt(br.readLine()) + Integer.parseInt(br.readLine()) + 3);
	}
}
