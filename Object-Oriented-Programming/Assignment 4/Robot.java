import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Robot extends JFrame implements ActionListener {
	
	private JButton[] buttons = new JButton[9];
	private String[] buttonNames = {"takeoff", "land", "turn", "right", "up", "forward", "left", "down", "backward"};
	private HashMap<String, JButton> button = new HashMap<>();
	
	private Socket socket;
	private PrintWriter out;
	
	final private String HOST = "robcog.cs.okstate.edu";
	final private int PORT = 9095;
	
	public Robot() {
		setLayout(new GridLayout(3,3));
		setTitle("Robot Controller");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
		
		for (int i = 0; i < this.buttons.length; i++) {
			this.buttons[i] = new JButton(this.buttonNames[i]);
			this.buttons[i].addActionListener(this);
			this.button.put(buttonNames[i], buttons[i]);
			add(this.buttons[i]);
		}

		try {
			socket = new Socket(HOST, PORT);
			out = new PrintWriter(this.socket.getOutputStream());
		}
		catch(SocketTimeoutException e) {
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void takeoff() {
        String msg = "{\"op\":\"publish\",\"topic\":\"/ardrone/takeoff\",\"msg\":{}}";
        send(msg);
		System.out.println("Takeoff!");
    }

    public void land() {
        String msg = "{\"op\":\"publish\",\"topic\":\"/ardrone/land\",\"msg\":{}}";
        send(msg);
    }

    public void move(double linearX, double linearY, double linearZ, double angularZ) {
        String msg = String.format("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":%f,\"y\":%f,\"z\":%f},\"angular\":{\"x\":0,\"y\":0,\"z\":%f}}}", linearX, linearY, linearZ, angularZ);
        send(msg);
    }

    private void send(String msg) {
        out.println(msg);
        out.flush();
		System.out.println("msg sent!");
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == button.get("takeoff")) takeoff(); //System.out.println("takeoff");
			else if (e.getSource() == button.get("land")) land();
			else if (e.getSource() == button.get("turn")) move(0,0,0,0.75);
			else if (e.getSource() == button.get("right")) move(0,0.25,0,0);
			else if (e.getSource() == button.get("left")) move(0,-0.25,0,0);
			else if (e.getSource() == button.get("forward")) move(-0.25,0,0,0);
			else if (e.getSource() == button.get("backward")) move(0.25,0,0,0);
			else if (e.getSource() == button.get("up")) move(0,0,0.25,0);
			else if (e.getSource() == button.get("down")) move(0,0,-0.25,0);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Robot();
	}
}