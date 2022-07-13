import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// BOJ #1181 단어 정렬
public class Main {
	public static void solution(String[] arr){
		Arrays.sort(arr, new Comparator<String>(){
			public int compare(String str1, String str2) {
				if(str1.length() == str2.length()) {
					return str1.compareTo(str2);
				}
				else return str1.length() - str2.length();
			}
		});
		StringBuilder sb = new StringBuilder();
		String last = "";
		for(int i = 0 ; i < arr.length ; i ++) {
			if(!arr[i].equals(last)) sb.append(arr[i]).append('\n');
			last = arr[i];
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = br.readLine();
		}
		T.solution(arr);
	}
}
