package mergesort;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Merge Sort via threads
 * @author Brenttime
 */

public class Mergesort
{
    public static void main(String[] args)
    {
        Integer [] array = new Integer[10000];
        for(int i=0; i<10000; i++)
        {
            array[i] = (int) (Math.random() * 100000);
        }
        //String[] array = {"Sudan", "United States", "Indiana", "Europe", "But", "Bua"};

        //Start Merge -- This will start 2 threads
        mergeSort(array);
        
        /*
            I sleep the main thread in order to 
            avoid not being able to see the data
        */
        try{
            //Thread.sleep(10000);
            System.out.println(Arrays.toString(array));
        }
        catch(Exception e)
        {
            //Debug exception
            System.out.println(e);   
        }   
    }

    /*set up for the mergesort function (array constructor)*/
    public static void mergeSort(Comparable [ ] a)
    {
        Comparable[] tmp = new Comparable[a.length];
        mergeSort(a, tmp,  0,  a.length - 1);
    }
    
    //Main merge sort method (non-array constructor)
    private static void mergeSort(Comparable [ ] a, Comparable [ ] tmp, 
        int left, int right)
    {
        if( left < right )
        {
            //System.out.println(Arrays.toString(a));
            
            int center = (left + right) / 2;
            
            //Thread One -- left
            Thread threadOne = new Thread() {
                @Override
                public void run()
                {
                    mergeSort(a, tmp, left, center);
                }
            };

            //Thread Two -- right
            Thread threadTwo = new Thread() {
                @Override
                public void run() {
                    mergeSort(a, tmp, center + 1, right);
                }
            };
            

            //Run all threads
            threadOne.start();
            threadTwo.start();    
            try {
                threadOne.join();
                threadTwo.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mergesort.class.getName()).log(Level.SEVERE, null, ex);
            }
            merge(a, tmp, left, center + 1, right);
        }
    }

    //Merge data
    private static void merge(Comparable[ ] a, Comparable[ ] tmp, int left, 
        int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
 }