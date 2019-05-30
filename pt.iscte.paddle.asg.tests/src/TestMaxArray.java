int max(int[] array) {
	int m;
	m = array[0];
	int i;
	i = 1;
	while(i < array.length) {
		if(array[i] > m)
			m = array[i];
		i = i + 1;
	}
	return m;
}

