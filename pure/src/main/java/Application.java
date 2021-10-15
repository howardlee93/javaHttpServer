import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

//https://github.com/evilprince2009/Simple-Java-HttpServer/blob/main/Main.java
//https://dev.to/mateuszjarzyna/build-your-own-http-server-in-java-in-less-than-one-hour-only-get-method-2k02

public class Application {
    public static void main( String[] args) throws Exception{
        int port = 8080;

        HttpServer server = HttpServer.create( new InetSocketAddress(port),0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(null);
        server.start();

//        ServerSocket listener = new ServerSocket(8080);
//        while (true){
//            Socket sock = listener.accept();
//            new PrintWriter(sock.getOutputStream(),true).println("Hello");
//            sock.close();
//        }
    }


    static class RequestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpThread) throws IOException{
            String res = "Hello there";
            httpThread.sendResponseHeaders(200, res.length());
            OutputStream responseBody = httpThread.getResponseBody();
            responseBody.write(res.getBytes(StandardCharsets.UTF_8));
            responseBody.close();
        }
    }
}
