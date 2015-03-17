int foo(int n, int a) {
	int x=1;
	int b=a+x;
	a=a+1;
	int i=1;
	int s=0;
	while (i<=n) {
		if (b>0)
			if (a>1)
				x=2;
		s=s+x;
		i=i+1;
	}
	return s;
}