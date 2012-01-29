Readme.txt

To run this program, simply run the main method in the Main class and this 
simulation will start. All constants reside in SantaConstants.java. There, it 
is possible to change the number of elves, how many elves it would take to get 
Santa's attention, how many milliseconds there are in a day, the lower and 
higher time range in which an elf encounters a problem that requires Santa's 
attention in milliseconds.  It also includes a list of reindeer names (with an 
implicit amount of reindeer derived from the amount of names), as well as an 
initial delay for reindeer before they will start returning.   Lastly, it 
includes the initial days until Christmas, an dteh number of milliseconds 
representing a “day.”

NUM_ELVES:			Number of elves

INIT_DAYS_TO_XMAS:	Initial Number of Days until Christmas

ELF_COUNT_WORTH_SANTAS_ATTENTION:		The number of elves who need to have a 
problem before Santa will fix them

MILLIS_PER_DAY: 		The number of milliseconds in a day

ELF_PROBLEM_MILLIS_LOW:	the minimum amount of time an elf can have a problem 
in, in milliseconds

ELF_PROBLEM_MILLIS_HIGH :	the maximum amount of time an elf can have a problem 
in, in milliseconds

INIT_REINDEER_DELAY:		The minimum amount of time before a reindeer comes 
back from the Bahamas.  Consists of INIT_DAYS_TO_XMAS, MILLIS_PER_DAY, and a 
factor between 0 and 1, exclusive

REINDEER_NAMES:		an array of Strings for reindeer names