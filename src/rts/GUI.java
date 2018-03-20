package rts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
	private static JFrame mainFrame;
	private static JTextField txt_temp;
	private static JTextField txt_hum;
	private static JTextField airCond;
	private static JTextField humdifier;
	private static JTextField lightStts;
	
	public static void setTemp(String s){
		txt_temp.setText(s);
	}
	
	public static void setHumdity(String s){
		txt_hum.setText(s);
	}
	
	public static void setAcStts(boolean stts){
		if(stts){
			airCond.setText("ON");
			airCond.setBackground(Color.green);
		}else{
			airCond.setText("OFF");
			airCond.setBackground(Color.red);
		}
	}
	
	public static void setHumdifierStts(boolean stts){
		if(stts){
			humdifier.setText("ON");
			humdifier.setBackground(Color.green);
		}else{
			humdifier.setText("OFF");
			humdifier.setBackground(Color.red);
		}
	}
	
	public static void setLightStts(boolean stts){
		if(stts){
			lightStts.setText("ON");
			lightStts.setBackground(Color.green);
		}else{
			lightStts.setText("OFF");
			lightStts.setBackground(Color.red);
		}
	}

	public static void prepareGUInew(){
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});   
		
		//create top and bottom panel
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(153, 204, 255));
		topPanel.setPreferredSize( new Dimension(600,150));
		
		JPanel topPanel1 = new JPanel();
		topPanel1.setBackground(new Color(153, 204, 255));
		topPanel1.setPreferredSize( new Dimension(600,50));
		JLabel headr = new JLabel("Home Monitoring System",JLabel.CENTER);
		headr.setFont(new Font("Serif", Font.BOLD, 25));
		topPanel1.add(headr);
		
		txt_temp = new JTextField();
		txt_temp.setPreferredSize( new Dimension( 100, 50 ) );
		txt_temp.setHorizontalAlignment(JTextField.CENTER);
		txt_temp.setEditable(false);
		JLabel temp = new JLabel("Temperature : ",JLabel.CENTER);
		
		txt_hum = new JTextField();
		txt_hum.setPreferredSize( new Dimension( 100, 50 ) );
		txt_hum.setHorizontalAlignment(JTextField.CENTER);
		txt_hum.setEditable(false);
		JLabel hum = new JLabel("Humidity : ",JLabel.CENTER);
		
		topPanel.add(topPanel1);
		topPanel.add(temp);
		topPanel.add(txt_temp);
		topPanel.add(hum);
		topPanel.add(txt_hum);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(153, 204, 255));
		bottomPanel.setPreferredSize( new Dimension(600,300));
		
		JPanel bottomPanel1 = new JPanel();
		bottomPanel1.setBackground(new Color(153, 204, 255));
		bottomPanel1.setPreferredSize( new Dimension(400,90));
		airCond = new JTextField();
		airCond.setPreferredSize( new Dimension( 100, 80 ) );
		airCond.setHorizontalAlignment(JTextField.CENTER);
		airCond.setEditable(false);
		JLabel ac = new JLabel("Air Conditioner Status : ",JLabel.CENTER);
		ac.setFont(new Font("Serif", Font.ITALIC, 20));
		bottomPanel1.add(ac);
		bottomPanel1.add(airCond);
		
		JPanel bottomPanel2 = new JPanel();
		bottomPanel2.setBackground(new Color(153, 204, 255));
		bottomPanel2.setPreferredSize( new Dimension(400,90));
		humdifier = new JTextField();
		humdifier.setPreferredSize( new Dimension( 100, 80 ) );
		humdifier.setHorizontalAlignment(JTextField.CENTER);
		humdifier.setEditable(false);
		JLabel hmdfr = new JLabel("Humdifier Status :           ",JLabel.CENTER);
		hmdfr.setFont(new Font("Serif", Font.ITALIC, 20));
		bottomPanel2.add(hmdfr);
		bottomPanel2.add(humdifier);
		
		JPanel bottomPanel3 = new JPanel();
		bottomPanel3.setBackground(new Color(153, 204, 255));
		bottomPanel3.setPreferredSize( new Dimension(400,90));
		lightStts = new JTextField();
		lightStts.setPreferredSize( new Dimension( 100, 80 ) );
		lightStts.setHorizontalAlignment(JTextField.CENTER);
		lightStts.setEditable(false);
		JLabel ldrStts = new JLabel("Light Status :                   ",JLabel.CENTER);
		ldrStts.setFont(new Font("Serif", Font.ITALIC, 20));
		bottomPanel3.add(ldrStts);
		bottomPanel3.add(lightStts);
		
		
		bottomPanel.add(bottomPanel1);
		bottomPanel.add(bottomPanel2);
		bottomPanel.add(bottomPanel3);

		mainFrame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
		mainFrame.getContentPane().add(bottomPanel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}
}
