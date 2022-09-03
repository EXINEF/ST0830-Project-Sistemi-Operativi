//
// Created by Alessio Pierdominici on 18/05/2021.
//

#include <stdio.h>
#include <string.h>
#include <malloc.h>

#include "bvernan.h"
static Files files;
static Counters counters;

int encodeFile(char* keyPath, char* inputPath, char* outputPath){
    if(loadAllFiles(keyPath, inputPath, outputPath) != 0){return -1;}

    char *key = (char* )malloc(files.KEY_SIZE * sizeof(char));
    fread(key, sizeof (char), files.KEY_SIZE, files.pKeyFile);
    char *blockD = (char* )malloc(files.KEY_SIZE * sizeof(char));
    long BLOCK_SIZE = (files.INPUT_SIZE <= files.KEY_SIZE ? files.INPUT_SIZE : files.KEY_SIZE);

    while(fread(blockD, sizeof (char), BLOCK_SIZE, files.pInputFile) != 0){
        if(encodeBlock(blockD, key, files.INPUT_SIZE, BLOCK_SIZE, files.pOutputFile) != 0){
            perror("\nThere was an error while encoding block");
            return 1;
        }
        counters.j++;
    }
    closeAllFiles();
    return 0;
}

int encodeBlock(const char* block, const char* key, long INPUT_SIZE, long BLOCK_SIZE, FILE *pOutputFile){
    for (long i = 0; i < BLOCK_SIZE; ++i) {
        if(counters.offset < INPUT_SIZE){
            if(putc(block[i] ^ key[(counters.j + i) % BLOCK_SIZE], pOutputFile) == EOF){
                perror("\nThere was an error while writing Output Files\n");
                return 1;
            }
            counters.offset++;
        } else{break;}
    }
    return 0;
}

int loadAllFiles(const char* keyPath, const char* inputPath, const char* outputPath){
    files.pKeyFile = fopen(keyPath, "r");
    files.pInputFile = fopen(inputPath, "r");
    files.pOutputFile = fopen(outputPath, "w");

    if(checkNullsFiles(files.pKeyFile, files.pInputFile) != 0){
        return 1;
    }
    calculateFilesSize();
    return 0;
}

void closeAllFiles(){
    fflush(files.pOutputFile);
    fclose(files.pKeyFile);
    fclose(files.pInputFile);
    fclose(files.pOutputFile);
}

int checkNullsFiles(){
    if (files.pKeyFile == NULL){
        perror("\nERROR: Key File was not found, check if its name is written correctly.");
        return 1;
    }
    if (files.pInputFile == NULL){
        perror("\nERROR: Input File was not found, check if its name is written correctly.");
        return 1;
    }
    return 0;
}

void calculateFilesSize(){
    files.INPUT_SIZE = getFileSize(files.pInputFile);
    files.KEY_SIZE = getFileSize(files.pKeyFile);
}

long getFileSize(FILE* file){
    fseek(file, 0, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}










	
	
	
