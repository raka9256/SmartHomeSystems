package rts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

public class SimpleRead extends GUI implements Runnable, SerialPortEventListener {
	
	static CommPortIdentifier portId;
 
	@SuppressWarnings("rawtypes")
	static Enumeration portList;

	InputStream inputStream;
	SerialPort serialPort;
	Thread readThread;
	Boolean runApplication=false;
	TempHumCalc thCalc = new TempHumCalc();
	AlarmSystem alrmSys = new AlarmSystem();

	public static void main(String[] args) {
		  
		prepareGUInew();
		
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM6")) {
					new SimpleRead();
				}
			}
		}
	}

	public SimpleRead() {
		try {
			serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
		} catch (PortInUseException e) {
			System.out.println("PortInUseException");
		}
		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			System.out.println("IOException");
		}
		try {
			serialPort.addEventListener(this);
		} catch (TooManyListenersException e) {
			System.out.println("TooManyListenersException");
		}
		serialPort.notifyOnDataAvailable(true);
		try {
			serialPort.setSerialPortParams(38400,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
			System.out.println("UnsupportedCommOperationException");
		}
		readThread = new Thread(this);
		readThread.start();
	}

	public void run() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public void serialEvent(SerialPortEvent event) {
		switch(event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			try {
				while (inputStream.available() > 0) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					String line;
					while ((line = reader.readLine()) != null) {
						getReadings(line);
					}
					reader.close();
				}
			} catch (IOException e) {System.out.println(e);}
			break;
		}
	}

	public void getReadings(String s){

		String on="On ";
		System.out.println(s);
		if(s.equals(on)){
			runApplication=true;
		}else if(runApplication){
			if(s.contains(",")){
				String[] values = s.split(",");
				// float temp =Float.parseFloat(values[0]);
				float fhum =Float.parseFloat(values[1]);
				setHumdity(values[1]);
				Boolean humStatus=thCalc.calHum(fhum);
				setHumdifierStts(humStatus);
			}else if(s.contains("C")){
				float ftemp =Float.parseFloat(s.substring(1,6));
				setTemp(s.substring(1,6));
				Boolean tempStatus=thCalc.calTemp(ftemp);
				setAcStts(tempStatus);
			}else{
				float ldrVal =Float.parseFloat(s);
				System.out.println(ldrVal);
				
				LightCalc ldrCalc= new LightCalc();
				Boolean lightStatus;
				lightStatus=ldrCalc.ldrCalculation(ldrVal);
				setLightStts(lightStatus);
			}
		}else if (s.contains("R")){
			alrmSys.setAlarm(s);
			System.out.println(s);
		}
	}
}
