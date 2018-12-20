# Memory-Managment-Visualization
Project for OOD FALL 2018

Group: Nicholas La Sala, John Stranahan, Gene Zaleski, Youngyo Na, RJ Boucher, Craig Wert.

The project simulates process enter a memory stack, running to completion and exiting the memory.
Processes are allocated memory using first fit, and processes that are too large are placed in a queue to wait for more space to be made availble.

The program also supports a Buddy System process allocation style, which allocates process by splitting up nodes until they are as small as possible. Once the processes have exited the stack the nodes merge back together so that larger process can be stored again.

During runtime a GUI displays which process are currently running, and how much time they still need to execute. once their time to live reaches zero they are removed from the display.
