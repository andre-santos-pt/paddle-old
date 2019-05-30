void sort(int[] array) {
	int i;
	i = 0;
	while(i < (array.length - 1)) {
		int min;
		min = i;
		int j;
		j = i + 1;
		while(j < array.length) {
			if(array[j] < array[min])
				min = j;
			j = j + 1;
		}
		swap(array, i, min);
		i = i + 1;
	}
}

