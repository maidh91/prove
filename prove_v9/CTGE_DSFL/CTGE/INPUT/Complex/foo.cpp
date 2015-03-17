int foo(int n) {
	int x = 3;
	int i = 0;
	while (i < n) {
		if (i % 2 == 1) {
			x = 5;
		}
		else {
			x = 7;
		}
		i++;
	}
	return x;
}

