import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Number of process: ");
		
		int numberOfProcesses = scanner.nextInt();
		int[] burstTime = new int[numberOfProcesses];
		int[] arrivalTime = new int[numberOfProcesses];
		int[] waitTime = new int[numberOfProcesses];
		int[] turnTime = new int[numberOfProcesses];
		int[] process = new int[numberOfProcesses];
		int temp;
		int total = 0;
		double averageWait;
		
		//User Submits Burst Time
		System.out.println("Enter the Burst Time");
		for(int i = 0; i < numberOfProcesses; i++)
		{
			System.out.print("P" + (i + 1) + ": ");
			burstTime[i] = scanner.nextInt();
			process[i] = i + 1;
		}

		//User Submits Arrival Time
		System.out.println("Enter the Arrival Time");
		for(int i = 0; i < numberOfProcesses; i++)
		{
			System.out.print("P" + (i + 1) + ": ");
			arrivalTime[i] = scanner.nextInt();
		}
		
		//Sort Burst Time
	    //sorting burst time in ascending order using selection sort
	    for(int i=0; i < numberOfProcesses; i++)
	    {
	        int pos = i;
	        for(int j = i+1; j< numberOfProcesses; j++)
	        {
	            if(burstTime[j]<burstTime[pos])
	                pos=j;
	        }
	 
	        //reorder bursts
	        temp=burstTime[i];
	        burstTime[i]=burstTime[pos];
	        burstTime[pos]=temp;
	 
	        //Reorder process ID
	        temp=process[i];
	        process[i]=process[pos];
	        process[pos]=temp;
	        
	        //reorder wait
	        temp=waitTime[i];
	        waitTime[i]=waitTime[pos];
	        waitTime[pos]=temp;
	    }
	    
		//Calculate Wait Time
		waitTime[0] = arrivalTime[0];
		total += arrivalTime[0];
		for(int i = 1; i < numberOfProcesses; i++)
		{
			waitTime[i] = arrivalTime[i];
			System.out.println(waitTime[i]);
			for(int j =0; j < i; j++)
			{
				System.out.println(burstTime[j]);
				waitTime[i] += burstTime[j];
			}
			
			total += waitTime[i];
		}
		
		//Calculate Turn-around Time
		turnTime[0] = burstTime[0] + arrivalTime[0];
		for(int i = 1; i < numberOfProcesses; i++)
		{
			for(int j =0; j < i; j++)
			{
				turnTime[i] = burstTime[i] + arrivalTime[i] + turnTime[j];
			}
		}
		
		//Compute Average Wait Time
		averageWait = (float) (total/numberOfProcesses);
		
		//Output User Entered Info
		System.out.println();
		System.out.println("Process \t Bust Time \t Arrival Time \t Wait Time \t Turn-around Time");
		System.out.println("------- \t --------- \t ------------ \t --------- \t ---------------");
		for(int i = 0; i < numberOfProcesses; i++)
		{
			System.out.printf("P%-19d %-17d %-13d %-15d %d", process[i], burstTime[i], arrivalTime[i], waitTime[i], turnTime[i]);
			System.out.println();
		}
		
		//Print Average Wait Time
		System.out.println();
		System.out.println("Average Wait Time: " + averageWait);
		
	}

}
