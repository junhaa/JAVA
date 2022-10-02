import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #11948 과목선택
public class Main {
	static int[] arr1 = new int[4];
	static int[] arr2 = new int[2];
	
	public static void main(String[] args) throws IOException{
		Main T = new Main(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		for(int i = 0 ; i < 4 ; i ++) {
			arr1[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 0 ; i < 2 ; i ++) {
			arr2[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		for(int i = 3 ; i > 0 ; i --) {
			answer += arr1[i];
		}
		answer += arr2[1];
		System.out.println(answer);
	}
}
