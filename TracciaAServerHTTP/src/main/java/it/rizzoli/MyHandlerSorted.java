package it.rizzoli;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class MyHandlerSorted implements HttpHandler {

    private Alberghi alberghi;
    Command command;

    public MyHandlerSorted() {
        this.alberghi = Alberghi.getInstance();
        this.command = new Command();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println(exchange.getRemoteAddress().getAddress());

        OutputStream os = exchange.getResponseBody();
        URI uri = exchange.getRequestURI();

        String result = alberghi.getOpenTable() + alberghi.getAllAlberghiSorted() + alberghi.getCloseTable();

        exchange.sendResponseHeaders(200, result.length());
        os.write(result.getBytes());
        os.close();
    }
}
