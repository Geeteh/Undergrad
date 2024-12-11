import java.io.*;
import java.net.*;
import javax.swing.*;

public class Webserver {


    private static class ConnectionHandler implements Runnable {
        private Socket socket;
		private String dir;


        public ConnectionHandler(Socket socket, String dir) {
            this.socket = socket;
			this.dir = dir;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				

                String request = in.readLine();
				//System.out.println(dir);
				
				String[] requestParts = request.split("\\s");
				//for (int i = 0; i < requestParts.length; i++) { System.out.println(requestParts[i]); }
				String fileName = requestParts[1];
				
				if (fileName.equals("/ ")) fileName = "index.html";
				
				System.out.println(fileName);
				File file = new File(dir + "/" + fileName);
				//System.out.println(file.getAbsolutePath());
				
				if (file.exists()) {
					//System.out.println("File exists");
					out.println("HTTP/1.1 200 OK");
					out.println("Content-Type: text/html");
					out.println();
					//out.println("<html><body>");
					//out.println("<h1>Hello, World!</h1>");
					//out.println("</body></html>");
					
					BufferedReader fileIn = new BufferedReader(new FileReader(file));
					String fileLine;
					while ((fileLine = fileIn.readLine()) != null) {
						out.println(fileLine);
					}
					fileIn.close();
				}
				else {
					out.println("HTTP/1.1 404 Not Found\r\n\r\n");
				}
				
				out.close();
				socket.close();
				
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 8080;

		JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Choose root directory for serving files");
        fc.showOpenDialog(null);
        String dir = fc.getSelectedFile().getAbsolutePath();
		//System.out.println(dir);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ConnectionHandler(socket, dir));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}