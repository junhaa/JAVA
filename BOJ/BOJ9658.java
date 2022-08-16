import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #9658 µπ ∞‘¿” 4
public class Main {
	static int[] arr = {0,1,0,1,0,0,0,0};
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(arr[N % 7] == 1) System.out.println("CY");
		else System.out.println("SK");
	}
}
