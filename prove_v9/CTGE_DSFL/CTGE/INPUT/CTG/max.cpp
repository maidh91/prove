int max(int a, int b, int c)
{
	if(a>b)
	{
		if (c>a) {
			return 3;
		}
		else {
			return 1;
		}
	}
	else
	{
		if (c>b) {
			return 3;
		}
		else {
			return 2;
		}
	}
}