int triangle(int a, int b, int c)
{
    if (a <= 0 || b <= 0 || c <= 0) 
	{
        return 4;
    }
    else if (!(a + b > c && a + c > b && b + c > a)) 
	{
        return 4;
    }
    else 
    {
	  if (a == b && b == c) {
            return 1;
        }
        else if (a == b || a == c || b == c) {
            return 2;
        }
        else return 3;
    }
}