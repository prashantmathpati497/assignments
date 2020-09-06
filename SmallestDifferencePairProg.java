
class SmallestDifferencePairProg {

	public static int[] findSmallestDifferencePair(int[] a1, int[] a2) {
		double smallestDiff = Double.MAX_VALUE;
		int[] smallestDiffPair = new int[2];
		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a2.length; j++) {
				int currentDiff = Math.abs(a1[i] - a2[j]);
				if (currentDiff < smallestDiff) {
					smallestDiff = currentDiff;
					smallestDiffPair[0] = a1[i];
					smallestDiffPair[1] = a2[j];
				}
			}
		}
		return smallestDiffPair;
	}

	public static void main(String[] args) {
		int[] a1 = new int[] { 1, 3, 15, 11, 2 };
		int[] a2 = new int[] { 23, 127, 235, 19, 8 };

		int[] pair = findSmallestDifferencePair(a1, a2);
		int diff = pair[0] - pair[1];
		System.out.println("Smallest Difference is : " + diff + "  pair( " + pair[0] + "," + pair[1] + ")");
	}
}
