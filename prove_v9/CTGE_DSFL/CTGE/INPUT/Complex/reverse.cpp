void reverse(int n)
{
	reverse = 0;
	while (n != 0)
   	{
      	reverse = reverse * 10;
      	reverse = reverse + n%10;
      	n = n/10;
   	}
}