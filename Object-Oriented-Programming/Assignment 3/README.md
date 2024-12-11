# Assignment 3
## Minesweeper.java, MinesweeperPanel.java, MinesweeperBoard.java, MinesweeperFrame.java, Sierpinski.java, SierpinskiFrame.java, SierpinskiPanel.java, Message.java, MessageFrame.java, MessagePanel.java
- imports javax.swing.\*, java.awt.\*, java.awt.event.*, etc.

### Minesweeper ~ Model-View-Controller Architecture
This program implements a simple GridLayout of JButtons (View) with MouseHandlers with inner ***private class MinesweeperMouseListener*** extending ***MouseAdapter*** (Controller). The inner class overrides ***mouseClicked()*** and recognizes the ***MouseEvent*** of the interacted JButton so that it is differentiable between left clicks and right clicks (defined by final BUTTON1 and final BUTTON3). The Model stores all of the gameplay aspects and methods to be called in the controller ***MinesweeperMouseListener***. The MinesweeperBoard initializes the new game by assigning a particular JButton to "Mine" based on an interchangable probability of 12.5%. The MinesweeperBoard defines mines and safe squares by assigning integer -1 (mine) and 0 (non-mine). These values are communicated to the controller via a java HashMap<JButton, Integer> which organizes well in terms of the communication occurring inside ***MinesweeperMouseListener***.

To launch Minesweeper.java, open your terminal and run:
```
$ java Minesweeper
```
It will launch 10x10 Minesweeper game, which, for example, looks like this if played out:

![Minesweeper](https://i.imgur.com/qNGgctG.png)

### Sierpinski's Triangle ~ Representing Recursive Fractals with a GUI
This program contains an overridden ***paintComponent*** method which determines the length of the smallest dimension of your resizable window by utilizing functions ***getWidth()*** and ***getHeight()***. This length (represented by **int length = minDimension**) is passed into method ***drawSierpinski()*** which takes arguments of the **Graphics** object from ***paintComponent()***, int x, int y, and int length. This is a recursive method where the base case calls ***fillRect(x,y,1,1)*** on the Graphics object passed, which draws a square the size of a single pixel at coordinates x and y. The else{} contains 3 recursive calls, which will draw a square on the left (x, y+1/2(length)), on the right (x + 1/2(length), y + 1/2(length)), and on the top (x + 1/4(length), y), where length is approaching the base case ***length == 1*** by recursive halves.

To launch Sierpinski.java, open your terminal and run:
```
$ java Sierpinski
```
It will launch a window initialized to 300x200 pixels, displaying Sierpinski's Triangle. The GUI will repaint based on resizing of the window to display the recursive fractal. It will look something like this:

![Sierpinski](https://i.imgur.com/M5yBR76.png)

![Sierpinski](https://i.imgur.com/6NqGjFx.png)

### Message ~ A Simple GUI Graphic
This is a simple graphic that draws a "Message in a bottle". The program contains a panel that overrides ***paintComponent()*** and passes in a **Graphics** object. The program calls a handful of ***draw()*** methods to create the "Message in a bottle" displayed when the program is launched. The MessagePanel extending JPanel is added to the MessageFrame extending JFrame which closes on exit, initialized to 500x500 pixels, and titled "Message in a bottle".

To launch Message.java, open your terminal and run:
```
$ java Message
```
It will launch a GUI program that displays:

![Message](https://i.imgur.com/Vyms4YB.png)

