double summation(double[] array) {
	double sum;
	sum = 0.0;
	int i;
	i = 0;
	while(i != array.length) {
		sum = sum + array[i];
		i = i + 1;
	}
	return sum;
}

