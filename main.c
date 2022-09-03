//
// Created by Alessio Pierdominici on 18/05/2021.
//

#include <stdio.h>

#include "bvernan.h"

void printInformation() {
    printf("YOU HAVE TO WRITE INPUT LIKE THIS: bvernan keyfile inputfile outputfile\n\n");
}

/**
 * Encode InputFile with keyFile using Bvernan Algorithm and save those encode to an outputFile
 *
 * @param argc number of parameters
 * @param argv parameters
 *
 * @return 0 if Encode was successful, -1 if Encode was unsuccessful
 */
int main(int argc, char **argv) {
    if (argc != 4) {
        printf("\nWrong number of input parameters (%d instead of 4)!\n\n", argc);
        printInformation();
        return 1;
    }

    int result = encodeFile(argv[1], argv[2], argv[3]);

    if(result!=0){
        printf("\nERROR: Bvernan Encode was unsuccessful\n\n");
        return 1;
    }

    printf("\nSUCCESS: Bvernan Encode was successful\n\n");
    return result;
}
