package manipulandoapis;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

import java.io.IOException;
import okhttp3.*;
import org.json.JSONObject;

public class ManipulandoAPIS {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

//        String json = "{\"mensaje\": \"Hola como estas?\"}";
//        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
//
//
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
//
//        
//
//        Request request = new Request.Builder().url("https://hospitable-jet-devourer.glitch.me/whtester").method("POST", body).build();
//
//        Response response = client.newCall(request).execute();
//        String jsonData = response.body().string();
//
//        System.out.println(jsonData);
        HttpServer server = HttpServer.create(new InetSocketAddress(3001), 0);
        server.createContext("/home", new MyHandler());
        server.setExecutor(null); // Crea un ejecutor por defecto
        server.start();
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {
                // Lee el cuerpo de la solicitud POST
                InputStream inStream = httpExchange.getRequestBody();
                Scanner scanner = new Scanner(inStream).useDelimiter("\\A");
                String requestBody = scanner.hasNext() ? scanner.next() : "";

                // Aquí puedes procesar el JSON recibido, por ejemplo, extraer el valor del campo "nombre"
                // Para este ejemplo, asumiremos que el JSON es directamente el valor del campo "nombre"
                System.out.println(requestBody); // Imprime el valor del campo "nombre"

//                JSONObject jsonObject = new JSONObject(requestBody);
//
//               // Extraer los valores del JSON
//                String nombre = jsonObject.getString("nombre");
                // Crear un nuevo objeto JSON
//                JSONObject nuevaPersona = new JSONObject();
//                 nuevaPersona.put("El nombre misterioso es: ", nombre);
//
//
//                System.out.println(nuevaPersona.toString());
                // Envía una respuesta al cliente
                String response = "JSON recibido y procesado";
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

}
