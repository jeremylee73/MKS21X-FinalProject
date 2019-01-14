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

January 11, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I changed the input in the terminal to take the equation in 'ax^2 + bx + c' form, and it looks for particular strings to parse into our individual variables. The only thing that could be optimized is that currently, it does not recognize x^2 or x as being equivalent to 1x^2 or 1x, so that is something we should fix later on. I also editted the rotate functions to accomodate for quadratic functions. I also editted the inputs so that the last input is a basic transformation done on the graph immediately after graphing it.

January 12, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I fixed a bug with translateLeftRight and updated the input so that after you type the equation you can use "trN", "tuN", "duN", and "drN" to transform when N is the number you translate/dilate by. I also adjusted the input such that you don't need to add a coefficient if the coefficient is 1, so x is the same as 1x and x^2 is the same as 1x^2. In addition, I optimized the display function so that it fills in gaps between dots when the slope of the line is above or below 1 in magnitude. Before, when we displayed 20x it was very blurry because there were only a few dots to satisfy the equation, but now it fills in those gaps. We still need to get it working for quadratics. I got it working for quadratics with leading coefficient greater than or equal to 1, but when it goes to 0.2 for example, the graph starts to look like a compilation of Ls.

January 13, 2019
------------------------------------------------------------------------------------------------------------------------------
Jeremy - I fixed a bug with solve, where it would perform the quadratic equation on linear functions and lead to division by zero. I just made it check for whether the coefficient of x^2 was zero. I also cleaned up some of the code by removing some of the commented out code. I also added some comments of my own to some of the code to clarify its function and commented out the optimized display for quadratics until we can get it functional. I also added an input option of "rc" or "rcc" to call the rotate function on the inputted graph.
Jacob - I made it so that you can graph two graphs on the same set of axes and translate them. To do this you just separate each graph with the word "new". This only works on two functions presently. The only issue is that this makes the graphs sideways.
