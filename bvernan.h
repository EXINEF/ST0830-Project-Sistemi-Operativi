//
// Created by Alessio Pierdominici on 18/05/2021.
//

#ifndef ALESSIOPIERDOMINICI109849_BVERNAN_H
#define ALESSIOPIERDOMINICI109849_BVERNAN_H

/**
 * Struct who contains all Files Information, pointers to Files and File Lengths
 */
typedef struct FilesDataStruct {
    FILE *pKeyFile, *pInputFile, *pOutputFile;
    long INPUT_SIZE, KEY_SIZE;
}Files;

/**
 * Struct who contains all Counters for control Bvenrnan Algorithm
 */
typedef struct CountersContainerStruct {
    long j, offset;
}Counters;


 /**
  * Encode Input Files with Key Files by using Bvernan Algorithm
  * then save result in Output Files
  *
  * @param keyPath key path
  * @param inputPath input path
  * @param outputPath output path
  * @return result
  */
int encodeFile(char* keyPath, char* inputPath, char* outputPath);

/**
 * Encode Block using bvernan algorithm and put evry new char to output File
 *
 * @param block block to encode
 * @param key key
 * @param INPUT_SIZE input file size in bytes
 * @param BLOCK_SIZE block size in bytes
 * @param pOutputFile pointer to output file
 * @return result
 */
int encodeBlock(const char* block, const char* key, long INPUT_SIZE, long BLOCK_SIZE, FILE *pOutputFile);

/**
 * Load all files in to their pointers using fopen, inizialise their length, and other variables
 *
 * @param keyPath key path
 * @param inputPath input path
 * @param outputPath output path
 * @return
 */
int loadAllFiles(const char* keyPath, const char* inputPath, const char* outputPath);

/**
 * Close All Files and Flush output File
 */
void closeAllFiles();

/**
 * Check if key or input files are null, then perror
 *
 * @return result
 */
int checkNullsFiles();

/**
 * Inizialise File Size in Bytes using getFileSize methos
 */
void calculateFilesSize();

/**
 * Calculate File Size in Bytes
 * @param file file to calculate size
 * @return
 */
long getFileSize(FILE *file);

#endif //ALESSIOPIERDOMINICI109849_BVERNAN_H
