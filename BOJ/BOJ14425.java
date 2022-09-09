import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

//BOJ #14425 문자열 집합
public class Main {

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<String> hs = new HashSet<>();
		int answer = 0;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N ; i ++) {
			String input1 = br.readLine();
			hs.add(input1);
		}
		for(int i = 0 ; i < M ; i ++) {
			String input2 = br.readLine();
			if(hs.contains(input2)) answer ++;
		}
		System.out.println(answer);
	}
}
