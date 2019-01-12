# MKS21X-FinalProject


------------------------------------------------------------------------------------------------------------------------------
A quick note: For whatever reason we thought the devlog was supposed to be on google docs rather than here at first. So if it looks like the first week or so of commits were all added at the same time, that's why.

January 03, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I wrote the solve function, which inputs four variables: a, b, c, d. The function returns the value of x after setting up the equation ax + b = cx + d.

January 04, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I wrote the storeVals function, which uses the inputted equation to check x and y values and set the values within the boolean 2D array to either true or false. If the value satisfies the equation it is set to true and if it doesn't it is false. I also wrote the toString function, which prints the true values within the boolean 2D array.

January 05, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - We got the display function to change a graph.jpg file within the directory to whatever graph was inputted. However, when the slope gets large in magnitude, the graph gets blurry because there are fewer integer points that can satisfy the equation within the 2000x2000 space.

January 06, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I tried to expand into the SingleGraph class, but there are some problems dealing with errors and transferring some of the functions to become compatible.
Jacob - I added the basics for translation/dilation

January 07, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - Fixed some of the issues with dilation and translation by simplifying the functions to directly change the equation rather than changing the boolean values of the 2D array.
Jacob - I moved a lot of the functions into the SingleGraph class. The original class is now only for our main and non-graphical things.

January 08, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I wrote both rotate functions that allow the graph to be rotated either 90 degrees clockwise or counterclockwise.
Jacob - I started to make some of the basic user inputs by allowing the user to give the slope and y-intercept of the line, along with the dimensions of the final image.

January 09, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I wrote the findRoot function, which uses the solve function to figure out the x coordinate at which the graph intersects the x-axis.
Jacob - I also fixed a few issues with inputting values.

January 10, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I added the feature of inputting quadratics, so now all the transformations except rotations are compatible with quadratics. I also changed the solve and findRoots functions to operate with quadratics. The one problem so far is that when graphing the quadratics, it is very, very blurry because the pixels are so spaced apart, which makes dilations pretty much impossible to see.
Jacob - I made a way for users to input transformations they want to see into the terminal directly, rather than having to go into the code.
