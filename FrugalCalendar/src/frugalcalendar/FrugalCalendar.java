package frugalcalendar;

/**
 * Operating Systems
 * Professor: Dr. Frank Ganther
 * Frugal Calendar HW
 * @author Brent Turner
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class FrugalCalendar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please Enter a Year between 1950-2050: ");

        //first list: for a given year, show the prior equivalent dates 
        int year = input.nextInt();

        //User Error detection
        while(year > 2050 || year < 1950)
        {
            System.out.print("Please Enter a Year between 1950-2050");
            year = input.nextInt();
        }
        
        int tempYear = year;
        boolean nextYear = false;

        //Year Inputed
        System.out.print(year + ": ");

        while(nextYear == false)
        {
            tempYear++;
            if(areSame(year,tempYear)) {
                //Next Year that the calendar will be used
                System.out.println(tempYear);
                nextYear = true;
            }
        }
    }

    //are the two years "the same"? (can use same calendar for both years)
    public static boolean areSame(int year1, int year2){
        Calendar c_b=new GregorianCalendar();	//current year begin date (first day of year)
        Calendar p_b=new GregorianCalendar();	//past year begin date (first day of year)
        Calendar c_e=new GregorianCalendar();	//current year end date (last day of year)
        Calendar p_e=new GregorianCalendar();	//past year end date (last day of year)

        //set beginning dates
        c_b.set(Calendar.MONTH, 0);
        c_b.set(Calendar.DAY_OF_MONTH, 1);
        p_b.set(Calendar.MONTH, 0);
        p_b.set(Calendar.DAY_OF_MONTH, 1);

        //set end dates
        c_e.set(Calendar.MONTH, 11);
        c_e.set(Calendar.DAY_OF_MONTH, 31);
        p_e.set(Calendar.MONTH, 11);
        p_e.set(Calendar.DAY_OF_MONTH, 31);

        //set years
        c_b.set(Calendar.YEAR, year1);
        c_e.set(Calendar.YEAR, year1);
        p_b.set(Calendar.YEAR, year2);
        p_e.set(Calendar.YEAR, year2);

        //looking at the two years,
        //if the first days of the year share the same day of the week
        //and the last days of the year share the same day of the week
        //the calendars are assumed to be equivalent
        if(c_b.get(Calendar.DAY_OF_WEEK)==p_b.get(Calendar.DAY_OF_WEEK) && c_e.get(Calendar.DAY_OF_WEEK)==p_e.get(Calendar.DAY_OF_WEEK))
                return true;
        return false;	
    }
}

