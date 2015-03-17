int test01(int M,int N) {
	if (M > 0 && N > 0) {
		if (M > N) {
			return M;
		}
		else {
			return N;
		}
	}
	else {
		return -1;
	}
}