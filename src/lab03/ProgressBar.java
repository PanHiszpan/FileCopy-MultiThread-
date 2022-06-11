package lab03;


	import java.awt.BorderLayout;
	import java.awt.Cursor;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.File;

	import javax.swing.BorderFactory;
	import javax.swing.JButton;
	import javax.swing.JComponent;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JProgressBar;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	 
	public class ProgressBar extends JPanel implements ActionListener {
	 
	    //TODO change settings

		String path = "C:\\Users\\schor\\IdeaProjects\\FileCopyMultiThread\\Copied_From\\";
		String copiedFolder = "Copied_To";



		private static JProgressBar progressBar;
	    private static JProgressBar progressBar1;
	    private static JProgressBar progressBar2;
	    private static JProgressBar progressBar3;
	    private static JProgressBar progressBar4;
	    
	    
	    
	    private JButton startButton;
	    static private JTextArea taskOutput;
	    
	    static String[] contents; //static zeby watki mogly nadpisywac w mainie
    	static int iloscPlikow=0;
    	static float procent=0;
    	static boolean update=false;
    	static boolean updateThread=false;
	    
	    
	    public ProgressBar() 
	    {
	        super(new BorderLayout());
	 
	        //Create the demo's UI.
	        startButton = new JButton("Start");
	        startButton.setActionCommand("start");
	        startButton.addActionListener(this);
	 
	        progressBar = new JProgressBar(0, 100);
	        progressBar.setValue(0);
	        progressBar.setStringPainted(true);
	        
	        progressBar1 = new JProgressBar(0, 100);
	        progressBar1.setValue(0);
	        progressBar1.setStringPainted(true);
	        
	        progressBar2 = new JProgressBar(0, 100);
	        progressBar2.setValue(0);
	        progressBar2.setStringPainted(true);
	        
	        progressBar3 = new JProgressBar(0, 100);
	        progressBar3.setValue(0);
	        progressBar3.setStringPainted(true);
	        
	        progressBar4 = new JProgressBar(0, 100);
	        progressBar4.setValue(0);
	        progressBar4.setStringPainted(true);
	 
	        taskOutput = new JTextArea(5, 20);
	        taskOutput.setMargin(new Insets(5,5,5,5));
	        taskOutput.setEditable(false);
	 
	        JPanel panel = new JPanel();
	        
	        panel.add(startButton);
	        
	        panel.add(progressBar);
	        panel.add(progressBar1);
	        panel.add(progressBar2);
	        panel.add(progressBar3);
	        panel.add(progressBar4);
	        
	        
	        
	        add(panel, BorderLayout.PAGE_START);
	        
	        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
	        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    }
	    
	    public void actionPerformed(ActionEvent evt) 
	    {
			System.out.println(System.getProperty("user.dir"));
	        startButton.setEnabled(false);
	        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	       
	        File directoryPath = new File(path); //Creating a File object for directory
	        contents = directoryPath.list(); //List of all files and directories

			//tworzenie nowego folderu
			File Dir = new File(copiedFolder);
			if(!Dir.exists())
			{
				if(Dir.mkdir())
				{
					updateTextArea("Folder created succesfully.");
				}
				else
				{
					updateTextArea("Folder failed to be created.");
				}
			}
			else
			{
				updateTextArea("Folder already exists.");
			}
	        
	        Thread1 w1 = new Thread1();
	        Thread1 w2 = new Thread1();
	        Thread1 w3 = new Thread1();
	        Thread1 w4 = new Thread1();    
			
	        w1.start();
	        w2.start();
	        w3.start();
	        w4.start();
	        
	    }
	 
	    
	    private static void createAndShowGUI() 
	    {

	        JFrame frame = new JFrame("File Copy");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 

	        JComponent newContentPane = new ProgressBar();
	        newContentPane.setOpaque(true); 
	        frame.setContentPane(newContentPane);
	 

	        frame.pack();
	        frame.setVisible(true);
	    }
	 
	    public static void main(String[] args) 
	    {
	        javax.swing.SwingUtilities.invokeLater(new Runnable() 
	        {
	            public void run() 
	            {
	                createAndShowGUI();
	            }
	        });
	    }
	    
	    public static void updateProgressBar() 
	    {	
	    	
	    	if(update==false)
	    	{
	    		update=true;
		    	for(int i=0; i<contents.length; i++) 
		    	{
		    		if(contents[i]=="Already Copied")
		    		{
		    			iloscPlikow++;
		    		}
		    	}	
		    	
		    	procent = (iloscPlikow/(float)contents.length)*100F; //40% minimalnie
		    	
		    	progressBar.setValue((int) procent);
		    	iloscPlikow = 0;
		    	
		    	update=false;
	    	}
	    }
	    
	    public static void updateThreadProgressBar(int procentThread, String threadName)
	    {	
	    	
	    	if(updateThread==false)
	    	{
	    	updateThread = true;
	    		
	    	switch(threadName) 
		    	{
		    	  case "Thread-0":
		    		  progressBar1.setValue(procentThread);
		    		  break;
		    	  case "Thread-1":
		    		  progressBar2.setValue(procentThread);
		    		  break;
		    	  case "Thread-2":
		    		  progressBar3.setValue(procentThread);
		    		  break;
		    	  case "Thread-3":
		    		  progressBar4.setValue(procentThread);
		    		  break;	
		    		  
		    	  default:
		    	}
	    	updateThread = false;
	    	}
	    }
	    
	    public static void updateTextArea(String text) 
	    {
	    	taskOutput.append(text);
	    }
	    
	    
	    public static void setTab(String[] tab)
		{
	        contents = tab;
	    }


	    public static String[] getTab() 
	    {
	        return contents;
	    }
	}

