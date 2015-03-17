int foo(int a, int b, int c) {
	int x=0;
	if((a==0)&&(b==0)&&(c==0)){
		return -1;
	}
	else if((b*b-4*a*c)<0){
		return -1;
	}
	else if((b*b-4*a*c)==0){
		return 1;
	}
	else {
		return 2;
	}
}

