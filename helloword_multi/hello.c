#include <stdio.h>
#include <string.h>
#include <polyglot.h>

void *helloWorld(char *name) {
    static char message[80];
    strcat(message,"C says Hello World ");
    strcat(message, name);
    strcat(message,"!");
    return polyglot_from_string(message, "UTF-8");
}

int main() {
    printf("Hello World");
    return 0;
}