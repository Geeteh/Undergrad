import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Browser extends JFrame implements ActionListener {

    private JTextField urlField;
	private JTextArea outputField;
    private JButton goButton;
	private String outputText;

    public Browser() {
		setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 35, 10));
        urlField = new JTextField();
        urlField.setPreferredSize(new Dimension(350, 25));
        goButton = new JButton("Go");
		goButton.addActionListener(this);
        topPanel.add(urlField);
        topPanel.add(goButton);
        outputField = new JTextArea();
        outputField.setEditable(false);
		outputField.setLineWrap(true);
		outputField.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(outputField);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setTitle("Geeteh's Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }
	
	public void actionPerformed(ActionEvent event) {
        String line;
		String urlString = urlField.getText();
		
		try {
			URL url = new URL(urlString);
			String host = url.getHost();
			String path = url.getPath();
			//System.out.println(path); System.out.println(host);
			
			Socket socket = SSLSocketFactory.getDefault().createSocket(host, 443);
			socket.setSoTimeout(500);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.print("GET " + path + " HTTP/1.1\r\n");
			out.print("Host: " + host + "\r\n\r\n");
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			boolean bodyStart = false;
			outputText = "";
			String title = "";
			String body = "";
			outputField.setText("");
			while ((line = in.readLine()) != null) {
				if (line.contains("<title>")) {
					title = line.substring(line.indexOf("<title>") + 7, line.indexOf("</title>"));
					setTitle(title);
				}
				else if (line.contains("<body")) {
					bodyStart = true;
				}
				else if (line.contains("</body>")) {
					break;
				}
				else if (bodyStart) {
					body += line + "\n";
				}
				
				
				outputField.append(outputText);
				//System.out.println(line + "\n");
			}
			outputText += title + "\n";
			outputText += body;
			outputField.append(outputText);
			
			
			
			
			
			in.close();
			out.close();
			socket.close();
			
		}
		catch (SocketTimeoutException e) {
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println("URL entered: " + urlString);
    }

    public static void main(String[] args) {
		new Browser();
    }
}
