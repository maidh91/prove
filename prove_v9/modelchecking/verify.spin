proctype m_oddNumber(int m1_p; chan c)
{
lb_1: skip;
	int m1_re = 0;
	int m1_a = m1_p;
	int m1_b = (m1_p + 100);
	if
	:: m1_a > 0 && m1_b > 0 -> 
		lb_3: m1_re = 1;
	:: else -> skip;
	fi;
	c ! m1_re; goto lb_2;
lb_2: skip;
}

#include "var_init.c"
init {
	int m1_p; 
	chan c = [1] of {int};

	var_init(m1_p);

	int obtainedResult;
	run m_oddNumber(m1_p, c);
	c?obtainedResult;

	int desiredResult;
	c_code {
		Pinit-> desiredResult = oddNumber(Pinit->m1_p);
	};
	assert (obtainedResult == desiredResult )
}

c_code {\#include "solution.c"};

c_code {\#include "createdomain.h"};

