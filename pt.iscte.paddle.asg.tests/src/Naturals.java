int[] naturals(int n) {
	int[] v;
	v = new int[n];
	int i;
	i = 0;
	while(i < n) {
		v[i] = i + 1;
		i = i + 1;
	}
	return v;
}

