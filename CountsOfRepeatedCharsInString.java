
public class CountsOfRepeatedCharsInString {
	public static void main(String[] args) {

		String str = "aabcccccaaa";
		// String str="abc";
		StringBuilder sb = new StringBuilder();

		char[] arr = str.toCharArray();
		char previous = ' ';
		int count = 0;
		int i = 0;
		for (char c : arr) {
			i += 1;
			if (c == previous) {
				count += 1;
			} else {
				if (count > 1) {
					sb.append(count);
					sb.append(c);
					count = 1;
				} else {
					count = 1;
					if (previous != ' ' && previous != c) {
						sb.append(1);
					}
					sb.append(c);
				}
			}
			if (arr.length == i) {
				sb.append(count);
			}
			previous = c;
		}
		System.out.println(sb.toString());
	}

}
