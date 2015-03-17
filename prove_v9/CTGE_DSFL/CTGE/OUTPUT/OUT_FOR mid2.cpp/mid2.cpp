int mid(int a, int b, int c)
{
	if ((a > b))
	{
		if ((b > c))
		{
			return (b + 100);
		}
		else
		{
			return c;
		}
	}
	else
	{
		if ((a < c))
		{
			return a;
		}
		else
		{
			return c;
		}
	}
}
