package assignment4;

// Divide and Conquer Strategy
/*
   3. Implement algorithm for The Skyline Problem:
         Given n rectangular buildings in a 2-dimensional city, computes the skyline of these buildings, eliminating hidden
      lines. The main task is to view buildings from a side and remove all sections that are not visible.

      All buildings share common bottom and every building is represented by triplet (left, ht, right)
      'left': is x coordinate of left side (or wall).
      'right': is x coordinate of right side
      'ht': is height of building

      For example, the building on right side is represented as (1, 11, 5)

      A skyline is a collection of rectangular strips. A rectangular strip is represented as a pair (left, ht) where left is x
      coordinate of left side of strip and ht is height of strip.

      With Time Complexity O(n*logn)
*/


import java.util.*;

// A class for building
class Building {
    int left, ht, right;

    public Building(int left, int ht, int right) {
        this.left = left;
        this.ht = ht;
        this.right = right;
    }
}

// A strip in skyline
class Strip {
    int left, ht;

    public Strip(int left, int ht) {
        this.left = left;
        this.ht = ht;
    }
}

// Skyline: To represent Output(An array of strips)
class SkyLine {
    List<Strip> arr;
    int capacity, n;

    public SkyLine(int cap) {
        this.arr = new ArrayList<>();
        this.capacity = cap;
        this.n = 0;
    }

    public int count() {
        return this.n;
    }

    // A function to merge another skyline
    // to this skyline
    public SkyLine merge(SkyLine other) {
        SkyLine res = new SkyLine(this.n + other.n);
        int h1 = 0, h2 = 0, i = 0, j = 0;
        while (i < this.n && j < other.n) {
            if (this.arr.get(i).left < other.arr.get(j).left) {
                int x1 = this.arr.get(i).left;
                h1 = this.arr.get(i).ht;
                int maxh = Math.max(h1, h2);
                res.append(new Strip(x1, maxh));
                i++;
            } else {
                int x2 = other.arr.get(j).left;
                h2 = other.arr.get(j).ht;
                int maxh = Math.max(h1, h2);
                res.append(new Strip(x2, maxh));
                j++;
            }
        }
        while (i < this.n) {
            res.append(this.arr.get(i));
            i++;
        }
        while (j < other.n) {
            res.append(other.arr.get(j));
            j++;
        }
        return res;
    }

    // Function to add a strip 'st' to array
    public void append(Strip st) {
        if (this.n > 0 && this.arr.get(this.n-1).ht == st.ht) {
            return;
        }
        if (this.n > 0 && this.arr.get(this.n-1).left == st.left) {
            this.arr.get(this.n-1).ht = Math.max(this.arr.get(this.n-1).ht, st.ht);
            return;
        }
        this.arr.add(st);
        this.n++;
    }

    // A utility function to print all strips of
    // skyline
    public void printSkyline() {
        System.out.println("Skyline for given buildings is:");
        for (int i = 0; i < this.n; i++) {
            System.out.print("(" + this.arr.get(i).left + ", " + this.arr.get(i).ht + "), ");
        }
        System.out.println();
    }
}


class Example3{

    // This function returns skyline for a
    // given array of buildings arr[l..h].
    // This function is similar to mergeSort().
    public static SkyLine findSkyline(Building[] arr, int l, int h) {
        if (l == h) {
            SkyLine res = new SkyLine(2);
            res.append(new Strip(arr[l].left, arr[l].ht));
            res.append(new Strip(arr[l].right, 0));
            return res;
        }
        int mid = (l + h) / 2;


        // Recur for left and right halves
        // and merge the two results
        SkyLine sl = findSkyline(arr, l, mid);
        SkyLine sr = findSkyline(arr, mid+1, h);
        SkyLine res = sl.merge(sr);
        return res;
    }

    // Driver Code
    public static void main(String[] args) {

        Building[] arr = {
                           new Building(1, 11, 5), new Building(2, 6, 7), new Building(3, 13, 9),
                           new Building(12, 7, 16), new Building(14, 3, 25), new Building(19, 18, 22),
                           new Building(23, 13, 29), new Building(24, 4, 28)
                         };

        // Find skyline for given buildings
        // and print the skyline
        SkyLine res = findSkyline(arr, 0, arr.length-1);
        res.printSkyline();
    }

}

/*
  Output:
     Skyline for given buildings is:
     (1, 11), (3, 13), (9, 0), (12, 18), (22, 3), (25, 0), (28, 4), (29, 0),
*/
