import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

// BOJ #1550 16Áø¼ö
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String hexa = br.readLine();
		int answer = Integer.parseInt(hexa, 16);
		System.out.println(answer );
	}
}
