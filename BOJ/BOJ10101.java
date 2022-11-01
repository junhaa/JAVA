import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// BOJ #10101 삼각형 외우기
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		if(a == 60 && b == 60 && c == 60) {
			System.out.println("Equilateral");
			return;
		}
		if(a + b + c == 180) {
			set.add(a);
			set.add(b);
			set.add(c);
			if(set.size() == 3) {
				System.out.println("Scalene");
				return;
			}
			else { 
				System.out.println("Isosceles");
				return;
			}
		}
		else System.out.println("Error");
	}
}
