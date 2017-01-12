This project has been created during my applicant process at isles.io. This a Spigot plugin developed and written using the Spigot 1.11 API version and therefore may use methods which may have been deprecated or non-existant in other copies. The project was created using requirements given by the isles.io team.

The plugin allows for a user to create a triangle such as this:

Vertex 1: (x=0, y=0, z=0)
Vertex 2: (x=0, y=1, z=0)
Vertex 3: (x=0, y=1, z=1)

All triangles are treated in such a manner and therefore will only use the Y and Z co-ordinates and assume all X co-ordinates are the same in accordance to the example given.

By selecting locations in the Minecraft environment and then choose whether triangle should be filled or not. 

Example:

Non-Filled:

https://i.gyazo.com/b538a50dedc9ee5c05d50ef12871e3eb.png#

Filled:

https://i.gyazo.com/30e9061e805393901725df920afe6e8d.png

It currently has no validation whether or not the triangle is a regular triangle such as the example above, leading to some interesting creations when given the parametres.

To be implemented into a real world plugin it requires:

 - Allow for Z or X rather than just Z.
 - Calculate whether the Z/X should be increased or deducted to travel from one vertex to another.
