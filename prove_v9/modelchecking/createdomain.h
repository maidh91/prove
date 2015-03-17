#include <stdio.h>

int count_domain(char* sample_filename) {
	FILE *file = fopen (sample_filename,"r");	
	if (file == NULL)
		return -1;
	int line = 0;
	int min, max;
	while (!feof(file)) {
		line++;
		fscanf(file, "%d %d\n", &min, &max);
	}
	if (file != NULL)
		fclose (file);
	return line;
}

int extract_domain(char* sample_filename, int *min, int *max, int line_read) {
	// type = 1 -> get min value of domain
	// type = 2 -> get max value of domain
	
	FILE *file = fopen (sample_filename,"r");
	if (file == NULL)
		return -1;
	
	int line = 0;
	while (!feof(file)) {
		line++;
		fscanf(file, "%d %d\n", min, max);		
		if (line == line_read)
			break;
	}	
	
//	*min = 1;
//	*max = 100000;

	if (file != NULL)
		fclose (file);
	
	return line_read;
}

void create_array_input(char* sample_domain, int min_default, int max_default, int array_input[], int size) {
	srandom(time(NULL));
	int i=0;

	int min_domain, max_domain, line_read, total_domain;
	total_domain = count_domain(sample_domain);
	//printf("Total = %d\n", total_domain);
	/* 1 domain will generate 5 testcase, 2 testcase with value min or max, 3 testcase random in domain */

	line_read = 1;
	while (line_read <= total_domain) {
		line_read = extract_domain(sample_domain, &min_domain, &max_domain, line_read);	
		//printf("%d: %d => %d\n", line_read, min, max);
		if (min_domain < min_default)
			min_domain = min_default;
		if (max_domain > max_default)
			max_domain = max_default;
		array_input[i] = min_domain;
		array_input[i+1] = max_domain;
		array_input[i+2] = random() % (max_domain-min_domain+1) + min_domain;
		array_input[i+3] = random() % (max_domain-min_domain+1) + min_domain;
		array_input[i+4] = random() % (max_domain-min_domain+1) + min_domain;
		i = i + 5;
		line_read = line_read + 1;
	}
	
	while (i < size) {
		while (1) {
			array_input[i] = random() % (max_default-min_default+1) + min_default;
			int j=0;
			int equals = 0;
			for (j=0; j<i; j++)
				if (array_input[i] == array_input[j]) {
					equals = 1;
					break;
				}
			if (equals == 0)
				break;				
		}
		i++;
	}
}
