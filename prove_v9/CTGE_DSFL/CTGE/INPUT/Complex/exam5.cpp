int foo(int N) {
	if (N > 0) {
		int s = 0;
		int i;
		for (i=1; i<=N; i++) {
			s += i;
		}
		return s;
	}
	else {
		return -1;
	}
}