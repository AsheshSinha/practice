package com.prac.basics.heap;

import java.io.*;
import java.util.*;

public class SlidingWindowMedian2 {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        HRMaxHeap maxHeap = new HRMaxHeap(d / 2 + 1);
        HRMinHeap minHeap = new HRMinHeap(d / 2 + 1);
        int activityCount = 0;
        int j = 0;
        for (int i = 0; i < expenditure.length; i++) {
            double median = currentMedian(maxHeap, minHeap);
            if (i >= d) {
                /*System.out.print("minHeap: count : " + minHeap.count + " elem :");
                for (int k = 0; k < minHeap.count; k++) {
                    System.out.print(minHeap.arr[k].value + " ");
                }
                System.out.print("maxHeapcount : " + maxHeap.count + " elem :");
                for (int k = 0; k < maxHeap.count; k++) {
                    System.out.print(maxHeap.arr[k].value + " ");
                }*/
                // double median = currentMedian(maxHeap, minHeap);
                if (expenditure[i] >= (median * 2)) {
                    activityCount++;
                }

                j = i - d - 1;
                /*System.out.print("median: " + median);
                System.out.println(" activityCount " + activityCount);
                System.out.println(" expenditure[i] " + expenditure[i]);
                System.out.println("j: " + j + " i: " + i);
                System.out.println(" ");*/
                deleteOutOfWindow(j, maxHeap, minHeap, median, expenditure[i]);
            }
            buildMedianHeap(expenditure[i], maxHeap, minHeap, i, median);
        }
        return activityCount;
    }

    private static void deleteOutOfWindow(int j, HRHeap maxHeap, HRHeap minHeap, double median, int expenditure) {
        if (expenditure < median && maxHeap.map.containsKey(j))
            maxHeap.deleteAt(maxHeap.map.get(j));
        else if (minHeap.map.containsKey(j))
            minHeap.deleteAt(minHeap.map.get(j));

        rebalance(maxHeap, minHeap);

    }

    private static double currentMedian(HRHeap maxHeap, HRHeap minHeap) {
        if ((minHeap.count + maxHeap.count) % 2 == 0) {
            return ((double) minHeap.getTop().value + (double) maxHeap.getTop().value) / 2;
        } else if (maxHeap.count > minHeap.count)
            return maxHeap.getTop().value;
        else
            return minHeap.getTop().value;
    }

    private static void rebalance(HRHeap maxHeap, HRHeap minHeap) {
        while (maxHeap.count - minHeap.count > 1) {
            Data temp = maxHeap.deleteTop();
            minHeap.insert(temp);
        }
        while (maxHeap.count < minHeap.count) {
            Data temp = minHeap.deleteTop();
            maxHeap.insert(temp);
        }
    }

    private static void buildMedianHeap(int expenditure, HRHeap maxHeap, HRHeap minHeap, int i, double median) {
        Data data = new Data(i, expenditure);
        if (maxHeap.count == 0)
            maxHeap.insert(data);
        else {
            if (expenditure < median)
                maxHeap.insert(data);
            else
                minHeap.insert(data);
        }
        rebalance(maxHeap, minHeap);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);
        System.out.println(result);
        /*bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();*/


        scanner.close();
    }
}

class Data {
    int index;
    int value;

    Data(int i, int v) {
        this.index = i;
        this.value = v;
    }
}

abstract class HRHeap {
    Data arr[];
    int count;
    int capacity;
    Map<Integer, Integer> map = new HashMap<>();

    public int parentIndex(int i) {
        if (i <= 0 || i >= this.count)
            return -1;
        return (i - 1) / 2;
    }

    public int leftChildIndex(int i) {
        int left = 2 * i + 1;
        if (left >= this.count)
            return -1;
        return left;
    }

    public int rightChildIndex(int i) {
        int right = 2 * i + 2;
        if (right >= this.count)
            return -1;
        return right;
    }

    public Data getTop() {
        if (this.count <= 0)
            return new Data(-1, 0);
        return arr[0];
    }

    public abstract void percolateDown(int i);

    public Data deleteTop() {
        if (this.count <= 0) {
            return new Data(-1, 0);
        }
        Data value = arr[0];
        map.remove(value.index);
        arr[0] = arr[count - 1];
        map.replace(arr[count - 1].index, 0);
        this.count--;
        percolateDown(0);
        return value;
    }

    public Data deleteAt(int i) {
        if (this.count <= 0 || i < 0) {
            return new Data(-1, 0);
        }

        Data value = arr[i];
        map.remove(value.index);
        arr[i] = arr[count - 1];
        map.put(arr[count - 1].index, i);
        this.count--;
        percolateDown(i);
        return value;
    }

    public abstract void percolateUp(int i);

    public void insert(Data elem) {
        if (this.count == this.capacity) {
            reSizeHeap();
        }
        this.count++;
        map.put(elem.index, this.count - 1);
        arr[this.count - 1] = elem;
        percolateUp(this.count - 1);
    }

    public void reSizeHeap() {
        Data[] tempArr = new Data[this.count * 2];
        for (int i = 0; i < this.count; i++) {
            tempArr[i] = this.arr[i];
        }
        this.arr = tempArr;
        this.capacity = this.count * 2;
    }
}

class HRMinHeap extends HRHeap {
    public HRMinHeap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.arr = new Data[capacity];
    }

    @Override
    public void percolateDown(int i) {
        int min = i;
        int left = leftChildIndex(i);
        int right = rightChildIndex(i);
        if (left != -1 && arr[min].value > arr[left].value) {
            min = left;
        }
        if (right != -1 && arr[min].value > arr[right].value) {
            min = right;
        }
        if (min != i) {
            Data temp = arr[i];
            arr[i] = arr[min];
            map.replace(arr[min].index, i);
            arr[min] = temp;
            map.replace(temp.index, min);
            percolateDown(min);
        }
    }

    @Override
    public void percolateUp(int i) {
        int min = i;
        int parentIndex = parentIndex(i);
        if (parentIndex != -1 && arr[min].value < arr[parentIndex].value) {
            Data temp = arr[parentIndex];
            arr[parentIndex] = arr[min];
            map.replace(arr[min].index, parentIndex);
            arr[min] = temp;
            map.replace(temp.index, min);

            percolateUp(parentIndex);
        }
    }
}

class HRMaxHeap extends HRHeap {
    public HRMaxHeap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.arr = new Data[capacity];
    }

    @Override
    public void percolateDown(int i) {
        int max = i;
        int left = leftChildIndex(i);
        int right = rightChildIndex(i);
        if (left != -1 && arr[max].value < arr[left].value) {
            max = left;
        }
        if (right != -1 && arr[max].value < arr[right].value) {
            max = right;
        }

        if (max != i) {
            Data temp = arr[i];
            arr[i] = arr[max];
            map.replace(arr[max].index, i);
            arr[max] = temp;
            map.replace(temp.index, max);
            percolateDown(max);
        }
    }

    @Override
    public void percolateUp(int i) {
        int max = i;
        int parent = parentIndex(i);
        if (parent < 0)
            return;
        if (arr[max].value > arr[parent].value) {
            Data temp = arr[parent];
            arr[parent] = arr[max];
            map.replace(arr[max].index, parent);
            arr[max] = temp;
            map.replace(temp.index, max);
            percolateUp(parent);
        }
    }
}
