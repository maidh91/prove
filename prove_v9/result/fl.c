#include <stdio.h>
#include <stdlib.h>

int oddNumber(int p)
{
   	if ((p % 2) == 1)
    		return 1;
    	return 0;
}

int main(int argc, char *argv[])
{	
  printf("%d", oddNumber(2));
}
