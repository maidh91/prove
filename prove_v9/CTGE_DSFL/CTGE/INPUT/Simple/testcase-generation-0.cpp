int test01(int M,int N) {
	M = pow(M,2);
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