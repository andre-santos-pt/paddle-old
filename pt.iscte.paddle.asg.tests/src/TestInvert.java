void invert(int[] array) {
	int i;
	i = 0;
	while(i < (array.length / 2)) {
		swap(array, i, (array.length - 1) - i);
		i = i + 1;
	}
}

