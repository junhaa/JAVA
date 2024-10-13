import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #32498 Call for Problems
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		for(int i = 0 ; i < n ; i ++){
			if((Integer.parseInt(br.readLine()) & 1) == 1){
				answer ++;
			}
		}
		System.out.println(answer);
	}
}
