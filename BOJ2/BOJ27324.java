// BOJ #27324 ゾロ目 (Same Numbers)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N % 10 == N / 10)
			System.out.println(1);
		else
			System.out.println(0);
	}
}
