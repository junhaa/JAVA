import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #17009 Winning Score
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a =
			Integer.parseInt(br.readLine()) * 3 + Integer.parseInt(br.readLine()) * 2 + Integer.parseInt(br.readLine());
		int b =
			Integer.parseInt(br.readLine()) * 3 + Integer.parseInt(br.readLine()) * 2 + Integer.parseInt(br.readLine());
		if (a > b)
			System.out.println("A");
		else if (b > a)
			System.out.println("B");
		else
			System.out.println("T");
	}
}
