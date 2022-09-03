
import os
import filecmp

passed = True
testPassed = 0
testFailed = 0

def getSize(file):
    return os.path.getsize(file)

def sameLen(file1, file2):
    if(getSize(file1)==getSize(file2)):
        return True
    else:
        return False

def executeTest(key, input, output, reverseOutput, name):
    global passed
    global testPassed
    global testFailed

    print('\nDoing %s Test...\n' % str(name))
     
    os.system('./bvernan '+key+' '+input+' '+output)
    os.system('./bvernan '+key+' '+output+' '+reverseOutput)

    if(sameLen(input, output)):
        print('SUCCESS: Len %s is same Len %s %d - %d ' % ( input, output,getSize(input),getSize(output)))
        testPassed=testPassed+1
    else:
        passed=False
        testFailed=testFailed+1
        print('FAILED: Len %s NOT same Len %s %d - %d ' % ( input, output,getSize(input),getSize(output)))
        

    if(filecmp.cmp(input, reverseOutput)):
        print('SUCCESS: %s and %s are the same %d - %d '%  (input, reverseOutput,getSize(input),getSize(reverseOutput)))
        testPassed=testPassed+1
    else:
        passed=False
        testFailed=testFailed+1
        print('FAILED: %s and %s NOT the same %d - %d '% (input, reverseOutput,getSize(input),getSize(reverseOutput)))

os.system('clear')

print('\nCOMPILE AND TEST FOR BVERNAN...\n')

print('Deleting useless folders...')
os.system('rm -rf temptest')
os.system('rm -rf CMakeFiles')
os.system('rm Makefile')
os.system('rm cmake_install.cmake')
os.system('rm CMakeCache.txt')

print('\nApplyin: cmake ./...\n')
os.system('cmake ./')

print('\nApplyin: make all ./...\n')
os.system('make all')

print('\nCreating temptest dir ./...\n')
os.system('mkdir temptest')

print('\nStart doing TESTS for Bvernan app...\n')

executeTest('test/0key4b','test/0input11b','temptest/0output','temptest/0reverseOutput','COSO SEMPLICE')

executeTest('test/1key20b','test/1input40b','temptest/1output','temptest/1reverseOutput','DIVISIONE BLOCCHI INTERA')

executeTest('test/2key60b','test/2input10b','temptest/2output','temptest/2reverseOutput','DIVISIONE BLOCCHI INTERA, con chiave maggiore del file')

executeTest('test/3key10b','test/3input23b','temptest/3output','temptest/3reverseOutput','DIVISIONE BLOCCHI DOUBLE')

executeTest('test/example_key','test/example_file','temptest/example_output','temptest/example_reverseOutput','ESEMPIO PROF')

if(passed==True):
    print('\nSUCCESS all Test are Successful :) ...\n')
else:
    print('\nFAILED not all Test are Successful :( ...\n')  

print('PASSED:%d/%d'%  (testPassed, testPassed+testFailed) )





