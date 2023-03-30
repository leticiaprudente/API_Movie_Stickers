import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 films
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> film : movieList) {
            System.out.println();
            System.out.println(film.get("title"));
            System.out.println(film.get("image"));

            double rating = Double.parseDouble(film.get("imDbRating"));
            double stars = (double) Math.round(rating / 2.0);

            System.out.print("IMDb Rating: " + film.get("imDbRating") + " [");

            for (int count = 0; count < 5; count++) {
                if (count< stars) {
                    System.out.print("⭐");
                }
            }
            System.out.print("]");
            System.out.println();
        }
    }
}