int test03(int X, int Y) {
	if (X <= 0 || Y <= 0) {
		return -1;
	}
	else if (X > Y) {
		return X;
	}
	else {
		return Y;
	}
}