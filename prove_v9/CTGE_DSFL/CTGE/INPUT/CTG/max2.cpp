int max(int a, int b, int c)
{
	if(c>b)
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
		if (a>b) {
			return 1;
		}
		else {
			return 100;
		}
	}
}