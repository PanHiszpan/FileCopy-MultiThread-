package lab03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Thread1 extends Thread
{
	@Override
    public void run() 
	{
        ProgressBar.updateTextArea("Thread started [ " + Thread.currentThread().getName() + " ] \n");

        String[] tab1;
        String fileName;
        
        
        while(true) 
        {
        	tab1 = ProgressBar.getTab(); //aktualizacja tymczasowej tablicy
        	
	        for (int i = 0; i < tab1.length; i++) //przeszukanie pierwszego nieskopiowanego
	        {
	            if (tab1[i] != "Already Copied")
	            {
	            	
	            	fileName = tab1[i]; 
	            	ProgressBar.updateTextArea(Thread.currentThread().getName() +" - copying " + fileName + "\n");
	                tab1[i] = "Already Copied";
	                ProgressBar.setTab(tab1); //aktualizowanie tablicy w mainie
	                ProgressBar.updateProgressBar();

	                try //kopiowanie pliku
	    			{
	    				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\schor\\IdeaProjects\\FileCopyMultiThread\\Copied_From\\" + fileName));
	    				BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Users\\schor\\IdeaProjects\\FileCopyMultiThread\\Copied_From\\" + fileName));
	    				BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\schor\\IdeaProjects\\FileCopyMultiThread\\Copied_To\\" + fileName));
	    				
	    				String line;
	    				String line2;
	    				
	    				int numberOfLines = 0;
	    				int currentNumberOfLines = 0;
	    				int procentThread = 0;
	    				
	    				while((line = reader.readLine()) != null) //zliczanie ilosci linii w pliku
	    				{
	    					numberOfLines++;
	    				}
	    				reader.close();
	    				
	    				while((line2 = reader2.readLine()) != null) //odczytywanie kazdej kolejnej linii z pliku
	    				{
	    					
	    					writer.write(line2);
	                        writer.newLine();
	                        
	                        currentNumberOfLines++;
	                        
	                        procentThread = (int)(((float)currentNumberOfLines / (float)numberOfLines) * 100F); 
	                        //w przypadku gdy plik ma wiecej niz 100 linijek przekazuje te same procenty bo obcina wynik po przecinku (przekazuje 25% kilka razy zamiast 25,01% 25,02%)   
	                        //podczas obliczania procentow zmienne int sa rzutowane do float'a zeby w przypadku dzielenia 50/100 nie wyszedl wynik 0 tylko 0,5
	                        // potem te 0,5 mnozy sie razy 100 zeby byly procenty i wynik mozna spowrotem zostawic w incie
	                        
	                        
	                        ProgressBar.updateThreadProgressBar(procentThread, Thread.currentThread().getName());
	    				}
	    				
	    				reader2.close();
	    				writer.close();
	    			}
	    			catch(IOException e)
	    			{
	    				e.printStackTrace();
	    				ProgressBar.updateTextArea(e.getMessage()+"\n");
	    				ProgressBar.updateTextArea("Failed to read or write files. \n ");
	    			}
	                
	                break;
	            }
	        }
        }
	}
}
