boolean binarySearch(int[] array, int e) {
	int l;
	l = 0;
	int r;
	r = array.length - 1;
	while(l <= r) {
		int m;
		m = l + ((r - l) / 2);
		if(array[m] == e)
			return true;
		if(array[m] < e)
			l = m + 1;
		else
			r = m - 1;
	}
	return true;
}

