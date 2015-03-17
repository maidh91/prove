int f(int a) {
	int x=a+3;
	int y=x/3;
	if (y!=a) {
		y=y+1;
		x=3*(y+6)-8*x;
	}
	else {
		y=y+2;
		x=3*(y-6)-5*x;
	}
	if(x>15)
		x=x+2;
	return x;
}
