int f(int a) {
	int *ptr;
	ptr=&a;
	if (a > 5) {
		a = 5;
	}
	else {
		*ptr += 5;
	}
	*ptr=*ptr+15;
	return a;
}
