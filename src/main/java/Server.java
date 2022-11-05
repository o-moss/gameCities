import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;


    public static void main(String[] args) {
        String city = "";
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    System.out.println("New connection accepted");
                    if (city.equals("")) {
                        out.println("???");
                        city = in.readLine();
                        out.println("OK");
                    } else {
                        out.println(city);
                        String answer = in.readLine();
                        if (city.charAt(city.length() - 1) == answer.charAt(0)) {
                            out.println("OK");
                            city = answer;
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

    }
}
