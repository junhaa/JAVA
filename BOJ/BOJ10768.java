import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #10768 특별한 날
public class Main {

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int d = Integer.parseInt(br.readLine());
		if(m < 2) {
			System.out.println("Before");
		}
		else if(m > 2) {
			System.out.println("After");
		}
		else {
			if(d > 18) System.out.println("After");
			else if(d < 18) System.out.println("Before");
			else System.out.println("Special");
		}
	}
}
