int count(int[] array, int e) {
	int c;
	c = 0;
	int i;
	i = 0;
	while(i < array.length) {
		if(array[i] == e) {
			c = c + 1;
		}
		i = i + 1;
	}
	return c;
}

