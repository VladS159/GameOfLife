# Proj_cebp

## Team members
 - Bele Anamaria-Alexandra
 - Toader Beatrice
 - Sarbu Vlad
 - Trif Robert

## Project specification
 We want to create a grid where the resources (food) are limited. There are 2 types of cells, sexual and asexual,
each with their specific type of reproduction, affecting the enclosed environment that we will be simulating in
very specific ways. Asexual cells will be multiplicating through division, resulting in 2 hungry cells, and the
sexual cells will produce a third cell, which initially is hungry. Cells can also die, adding to the food supply.
Cells move around the greed with 2 primary goals, feeding and reproducing.

## Possible concurrence problems
 - Race Conditions: issues arising when different cells try to access resources or reproduce simultaneously.
 - Deadlocks: when multiple cells are waiting for each other to reproduce.
 - Starvation: might happen when a cell is being denied resouces repeatedly.

## Architecutre
 ### Classes
 - Cell class: represents a single cell in our simulated environment (any and all cells will fill one
 of the 2 roles represented by the sexual and asexual subclasses).
 - Environment class: entire world, responsible of managing resorces/ multiplying habbits.
 - Rules class: quota for how the cells/world will function.
 - Simulation: mechanism that controls the simulation loop and actions like start and stop.
 - Renderer: the way in which the simulation is displayed, how it becomes accessible to the user.

 ### Threads
  - Each cell will be represented by a different thread.

 ### Entry points
  - Renderer entry points: renders the graphics of the simulation (the environment, resources and the cells).
 
