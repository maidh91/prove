#include <fstream>
#include "verify.c"

int main(int args, char** argv)
{
	int a[4] = {3, 2, 1, 0};
	output = fopen("output.txt", "w");
	sort(a, 4);
	for(int i = 0; i < 4; i++)
		cout << a[i] << endl;
	return 1;
}
