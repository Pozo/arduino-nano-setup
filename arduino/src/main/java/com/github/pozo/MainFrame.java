package com.github.pozo;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame {
	private JFrame frame;
	private final Action turnOnLed = new AbstractAction("ON") {
		private static final long serialVersionUID = 335039065675847328L;

		public void actionPerformed(ActionEvent e) {
			try {
				String response = Arduino.turnOnLED();
				System.out.println(response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	};
	private final Action turnOffLed = new AbstractAction("OFF") {
		private static final long serialVersionUID = -6049179799308301774L;

		public void actionPerformed(ActionEvent e) {
			try {
				String response = Arduino.turnOffLED();
				System.out.println(response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Arduino.open();
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			public void run() {
				Arduino.close();
			}
		}));
	}

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		Box bv = Box.createHorizontalBox();

		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(bv);
		JButton button = new JButton(turnOnLed);
		bv.add(button);

		JButton buttonOff = new JButton(turnOffLed);
		bv.add(buttonOff);
	}

}
