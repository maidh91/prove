inline var_init(x1)
{
	/* NEW RANDOM ON DOMAIN */
	/*Size of random input array*/
	int SIZE_OF_ARRAY_INPUT = 100;
	int array_input[100];
	/*Value random from MIN to MAX*/
	int MIN_VALUE_RANDOM_INPUT = -1000;
	int MAX_VALUE_RANDOM_INPUT = 1000;	
	/*Create random input array*/
	c_code {
		int min_default = Pinit->MIN_VALUE_RANDOM_INPUT;
		int max_default = Pinit->MAX_VALUE_RANDOM_INPUT;
		int size = Pinit->SIZE_OF_ARRAY_INPUT;
		create_array_input("sample.txt", min_default, max_default, Pinit->array_input, size);
	};
	/*Assign random value in array to input variable*/
	int i1 = 0;	
	do		
	:: break;
	:: i1 < SIZE_OF_ARRAY_INPUT-1 -> i1++;
	od;
	x1 = array_input[i1];
}
