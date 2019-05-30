void scale(int[][] matrix, int s) {
	int i;
	i = 0;
	while(i != matrix.length) {
		int j;
		j = 0;
		while(j != matrix[i].length) {
			matrix[i][j] = matrix[i][j] * s;
			j = j + 1;
		}
		i = i + 1;
	}
}

