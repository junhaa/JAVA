import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #30030 스위트콘 가격 구하기
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()) / 11;
		System.out.println(10 * n);
	}
}
