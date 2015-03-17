int function(int X, int Y, int Z) {
	if (X<=0||Y<0||Z<=0)
		return -1;
	else {
		int temp = 5;
		int mid;
		if ((X>=Y&&Y>=Z)||(Z>=Y&&Y>=X))
			mid=Y;	// Y la so o giua
		else if ((Y>=X&&X>=Z)||(Z>=X&&X>=Y))
			mid=X;	// X la so o giua
		else 
			mid=Z;	// Z la so o giua
		if (mid%2==0)
			mid=mid/2;
		if (mid%3==1) 
			mid=mid/3;
		return mid*mid;
	}
}
