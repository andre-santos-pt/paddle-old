int sumEven(int from, int to) {
	int sum;
	sum = 0;
	int i;
	i = from;
	while(i <= to) {
		sum = sum + i;
		i = i + 2;
	}
	return sum;
}

