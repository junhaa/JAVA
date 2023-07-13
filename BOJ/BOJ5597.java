import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// BOJ #5597 과제 안 내신 분 ..?
public class Main {

	static boolean[] ch = new boolean[31];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0 ; i < 28 ; i ++) {
			ch[Integer.parseInt(br.readLine())] = true;
		}
		for(int i = 1 ; i <= 30 ; i ++) {
			if(!ch[i]) System.out.println(i);
		}
	}
}
