int[][] transpose(int[][] matrix) {
	int[][] t;
	t = new int[matrix[0].length][matrix.length];
	int i;
	i = 0;
	while(i != t.length) {
		int j;
		j = 0;
		while(j != t[i].length) {
			t[i][j] = matrix[j][i];
			j = j + 1;
		}
		i = i + 1;
	}
	return t;
}

