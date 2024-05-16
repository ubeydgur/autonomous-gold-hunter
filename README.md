# Autonomous_Gold_Hunter

We made the treasure hunter find the chests by moving randomly in a smart way without knowing the location of any of the chests.

There are 4 kinds of chests: Gold, Silver, Emerald, Copper.

You need to collect the chests in the order I wrote this. If he sees the silver chest before the gold chest, he cannot collect it. He keeps the coordinates of the silver chest in his memory and after collecting the gold chests, he applies path finding to the silver chest whose location he knows.
