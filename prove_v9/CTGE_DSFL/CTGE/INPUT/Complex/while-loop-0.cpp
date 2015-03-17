int foo(int N)
{
	// tinh n!
	int i;
	i=1;
	int gt = 1;
	while (i <= N)
	{
		gt = gt*i;
		i=i+1;
	}
	return gt;
}