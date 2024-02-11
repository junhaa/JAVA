import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #16566 카드 게임
public class Main {

	private static class FastIO {
		final private int BUFFER_SIZE = 1 << 16;
		final private int INT_SIZE = 10;
		private DataInputStream din;
		private DataOutputStream dout;

		private byte[] inbuffer;
		private int inputbufferpointer, bytesread;
		private byte[] outbuffer;
		private int outputbufferpointer;
		private byte[] bytebuffer;

		FastIO() {
			din = new DataInputStream(System.in);
			dout = new DataOutputStream(System.out);
			inputbufferpointer = bytesread = outputbufferpointer = 0;
			bytebuffer = new byte[INT_SIZE];
			inbuffer = new byte[BUFFER_SIZE];
			outbuffer = new byte[BUFFER_SIZE];

		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte b = read();
			while (b <= ' ')
				b = read();
			boolean isneg = (b == '-');
			if (isneg)
				b = read();
			do {
				ret = ret * 10 + b - '0';
			} while ((b = read()) >= '0' && b <= '9');

			if (isneg)
				return -ret;
			return ret;
		}

		private byte read() throws IOException {
			if (inputbufferpointer == bytesread)
				fillbuffer();
			return inbuffer[inputbufferpointer++];
		}

		private void fillbuffer() throws IOException {
			bytesread = din.read(inbuffer, inputbufferpointer = 0, BUFFER_SIZE);
			if (bytesread == -1)
				inbuffer[0] = -1;
		}

		public void write(int i) {
			if (i == 0) {
				writebuffer((byte)'0');
				return;
			}

			if (i < 0) {
				writebuffer((byte)'-');
				i = -i;
			}

			int index = 0;
			while (i > 0) {
				bytebuffer[index++] = (byte)((i % 10) + '0');
				i /= 10;
			}

			while (index-- > 0)
				writebuffer(bytebuffer[index]);
		}

		public void writels(int i) {
			write(i);
			writebuffer((byte)' ');
		}

		private void writebuffer(byte b) {
			if (outputbufferpointer == outbuffer.length) {
				flushbuffer();
			}
			outbuffer[outputbufferpointer++] = b;
		}

		private void flushbuffer() {
			if (outputbufferpointer != 0) {
				try {
					dout.write(outbuffer, 0, outputbufferpointer);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			outputbufferpointer = 0;
		}
	}

	static int N, M;
	static int[] parent;

	static boolean[] visited;

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}

	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);

		if (fa < fb) {
			parent[fa] = fb;
		} else {
			parent[fb] = fa;
		}

	}

	static int upperBound(ArrayList<Integer> list, int val) {
		int index = Collections.binarySearch(list, val);

		if (index < 0) {
			index = -(index + 1);
		}
		return index;
	}

	public static void main(String[] args) throws IOException {

		FastIO myIO = new FastIO();
		N = myIO.nextInt();
		M = myIO.nextInt();

		int K = myIO.nextInt();

		int[] sortArr = new int[N + 1];
		parent = new int[M];
		visited = new boolean[M];

		for (int i = 0; i < M; i++) {
			int cur = myIO.nextInt();
			sortArr[cur]++;
		}

		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			parent[i] = i;
		}

		for (int i = 0; i <= N; i++) {
			if (sortArr[i] != 0) {
				list.add(i);
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < K; i++) {
			int val = myIO.nextInt() + 1;
			int des = upperBound(list, val);
			int fd = find(des);
			visited[fd] = true;
			sb.append(list.get(fd) + "\n");
			if (fd != M - 1)
				union(fd, fd + 1);
		}
		System.out.println(sb);
	}
}
