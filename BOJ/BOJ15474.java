import java.util.Scanner;

// BOJ #15474 鉛筆 (Pencils)
public class Main {
	public static void main(String[] args) {
		Scanner S = new Scanner(System.in);
		int N = S.nextInt();
		System.out.println(
			(int)Math.min(Math.ceil(N / S.nextDouble()) * S.nextInt(), Math.ceil(N / S.nextDouble()) * S.nextInt()));
	}
}