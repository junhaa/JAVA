import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10886 0 = not cute / 1 = cute
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = 0;
		int b = 0;
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			if(Integer.parseInt(br.readLine()) == 1) a ++;
			else b ++;
		}
		if(a > b) {
			System.out.println("Junhee is cute!");
		}
		else {
			System.out.println("Junhee is not cute!");
		}
	}
}
