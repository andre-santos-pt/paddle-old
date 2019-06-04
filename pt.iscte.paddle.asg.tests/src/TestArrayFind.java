boolean exists(int[] array, int e) {
	boolean found;
	found = false;
	int i;
	i = 0;
	while((!found) && (i < array.length)) {
		if(array[i] == e) {
			found = true;
		}
		i = i + 1;
	}
	return found;
}

