# System description

This is a load balancing system. The system comprises three components.
1. A Balancer, which distributes the items to the Stores, so that the storage used on each store is always approximately equal.
2. A Generator (represents a client), which generates and sends to the Balancer a sequence of items (simulations thereof) to be stored. These items identify the type of object to be stored (e.g. a file or an image) and the size of the item in MBs.
3. A set of stores (3).

# How to use

* The Balancer has to run before anything else. The order of the rest doesn't matter.

# Notes

* This project may or may not receive updates, to better reflect my own skills in programming.
* There are also some ideas I may have to improve how the project works overall (possibly by making the system a little more realistic as a load balancing system).
* This project was made by me at the university as part of a module's project. Modules were basically subjects we had to learn, in case you're not familiar with the term.
* The system description used here is (mostly) taken from the description provided to us by the university.
