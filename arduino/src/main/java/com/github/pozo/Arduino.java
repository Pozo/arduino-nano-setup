package com.github.pozo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class Arduino {
	private static final String PROPERTY_PORT_ID = "port.id";
	private static final String PORT_ID = "/dev/ttyUSB0";
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	private static SerialPort serialPort;
	
	private static final String OFF_COMMAND = "OFF";
	private static final String ON_COMMAND = "ON";
	
	public static String open() {
		String result = "";
		try {
			String portIdFromProperty = System.getProperty(PROPERTY_PORT_ID);
			String portId = (portIdFromProperty==null)? PORT_ID : portIdFromProperty;
			serialPort = (SerialPort) CommPortIdentifier.getPortIdentifier(portId).open(Arduino.class.getName(), TIME_OUT);
			// useless in this case https://github.com/processing/processing/blob/master/java/libraries/serial/src/processing/serial/Serial.java#L560
			serialPort.setDTR(false); 
			serialPort.setSerialPortParams(DATA_RATE, 
					SerialPort.DATABITS_8, 
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

			result = bufferedReader.readLine();
		} catch (PortInUseException e) {
			e.printStackTrace();
		} catch (NoSuchPortException e) {
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}

	}
	
	public static String turnOnLED() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(serialPort.getOutputStream()));

		bufferedWriter.write(ON_COMMAND);
		bufferedWriter.flush();

		return bufferedReader.readLine();

	}

	public static String turnOffLED() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(serialPort.getOutputStream()));

		bufferedWriter.write(OFF_COMMAND);
		bufferedWriter.flush();

		return bufferedReader.readLine();
	}

}
