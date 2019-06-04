int max(int[] array, int bound) {
	int m;
	m = array[0];
	int i;
	i = 1;
	while(i < bound) {
		if(array[i] > bound) {
			m = array[i];
		}
		i = i + 1;
	}
	return m;
}

