# pragma JessieIntegerModel(math)
/*@ ensures \result == ((p % 2 == 1 || p % 2 == -1)?1:0);
*/
int oddNumber(int p) {
   int re = 0;
int a = p;
int b = p + 100;
if( a > 0 && b > 0)
 re = 1;
return re;

}
