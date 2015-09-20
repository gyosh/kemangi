#include <cstdio>
#include <string>

using namespace std;

char c;

bool valid(char c){
	if ('A' <= c && c <= 'Z') return 1;
	if ('a' <= c && c <= 'z') return 1;
	if ('0' <= c && c <= '9') return 1;
	if (c == '.') return 1;
	if (c == ',') return 1;
	if (c == '\"') return 1;
	if (c == ' ') return 1;
	if (c == '\n') return 1;
	return 0;
}

void clean(string input, string output){
	FILE *fin = fopen(input.c_str(), "r");
	FILE *fout = fopen(output.c_str(), "w");
	
	while (fscanf(fin, "%c", &c) == 1){
		if (valid(c)){
			fprintf(fout, "%c", c);
		}else{
			fprintf(fout, " ");
		}
	}
	
	fclose(fin);
	fclose(fout);
}

int main(){
	char buff[100];
	printf("masukkan nama berkas yang mau dibersihkan: ");
	scanf("%s", buff);
	string inFile = buff;
	
	printf("masukkan nama berkas hasil dibersihkan: ");
	scanf("%s", buff);
	string outFile = buff;
	
	clean(inFile, outFile);
}
