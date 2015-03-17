int foo(int a, int out)
{
	if (a > 4) {
		out = out + 100;
	}
	else {
		out = out + 2;
	}
	return out;
}