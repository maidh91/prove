int test02(int x) 
{
	float rad = x*3.14/180;
	if((sin(rad)>= 0) && (cos(rad)>=0))
	{
		return 1;
	}
	else if((sin(rad)>=0) && (cos(rad)<0))
	{
		return 2;
	}
	else if(sin(rad)<0 && cos(rad)>=0)
	{
		return 3;
	}
	else
	{
		return 4;
	}
}