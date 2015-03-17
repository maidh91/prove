int ucln(int n, int m)
{
	while(n!=0 && m!=0)
	{
		if(n>m)
		{
			n -= m;
		}
		else
			m -= n;
	}
	if (n == 0)
	{
		return m;
	}
	else
	{
		return n;
	}
}