int test02(int X, int Y) {
	if (X > Y && X > 0 && Y > 0) {
		return X;
	}
	else if (X < Y && X > 0 && Y > 0) {
		return Y;
	}
	else {
		return -1;
	}
}