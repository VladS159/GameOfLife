# Proj_cebp

## Team members
 - Bele Anamaria-Alexandra
 - Toader Beatrice
 - Sarbu Vlad
 - Trif Robert

## Project specification
 We want to create a grid where the resources (food) are limited. There are 2 types of cells, sexual and asexual,
each with their specific type of reproduction, affecting the enclosed environment that we will be simulating in
very specific ways. Asexual cells will multiply through division, resulting in 2 hungry cells, and the
sexual cells will produce a third cell, which initially is hungry. Cells can also die, adding to the food supply.
Cells move around the greed with 2 primary goals, feeding and reproducing.

## Possible concurrency problems
 - Race Conditions: issues arising when different cells try to access resources or reproduce simultaneously:
    - cells try to access a foodUnit at the same time -> solution: added a semaphore to ensure only one cell
    can access a piece of food at any given time.

    - problems related to sexual reproduction:
       - the code that creates the new baby cells should be executed by only one of the parents (not both) so
       only one is created (not twins) -> solution: a combination of synchronized blocks and forcefully making
       partaking cells not HORNEE anymore.
       - reproduction should only happen between 2 cells -> solution: same as above.

## Architecture
 ### Classes
 - Cell class: represents a single cell in our simulated environment (any and all cells will fill one
 of the 2 roles represented by the sexual and asexual subclasses).
 - Grid class: entire world, responsible for defining the finite space in which the cells live. 
 - FoodUnit class: a class that represents the comestible object which holds the tFull attribute.
 - Main class: responsible for setting the Grid's boundaries, initializing the sexual and asexual cells and starting
 the threads.

 ### Enums
 - CurrentState: holds all possible cell states: HUNGRY or HORNEE.

 ### Threads
  - Each cell will be represented by a different thread.

 ### Entry points
  - The Main class.
 
