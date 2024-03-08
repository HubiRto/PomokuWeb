package pl.pomoku.cli.commandsGroups;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pomoku.cli.manager.JwtTokenManager;
import pl.pomoku.cli.shell.InputReader;
import pl.pomoku.cli.shell.ShellHelper;
import pl.pomoku.model.dto.request.FirstSearchRequest;
import pl.pomoku.model.dto.response.FirstSearchResponse;


@ShellComponent
@RequiredArgsConstructor
public class AsdCommand {
    private final InputReader inputReader;
    private final ShellHelper shellHelper;
    private final WebClient webClient;
    private final JwtTokenManager jwtTokenManager;

    @ShellMethod(key = "dfs", value = "Algorytm DFS")
    public void dfs() {
        int number = 0;
        int[][] array = new int[0][];
        int maxSize = 0;

        while (true) {
            String line = inputReader.prompt("[" + number + "]");
            if (!StringUtils.hasText(line)) {
                shellHelper.printWarning("Wartość nie może być pusta!");
                continue;
            }
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            String[] strings = line.split(",");
            int[] numbers = new int[strings.length];
            boolean hasNegative = false;
            try {
                for (int i = 0; i < strings.length; i++) {
                    numbers[i] = Integer.parseInt(strings[i]);
                    if (numbers[i] < 0) {
                        hasNegative = true;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                shellHelper.printError("Podano nieprawidłową wartość! Musi być liczbą całkowitą.");
                continue;
            }
            if (hasNegative) {
                shellHelper.printError("Podano liczbę ujemną! Wszystkie wartości muszą być nieujemne.");
                continue;
            }

            if (array.length == 0) {
                array = new int[1][numbers.length];
                array[0] = numbers;
                maxSize = numbers.length;
            } else {
                if (maxSize < numbers.length) maxSize = numbers.length;
                int[][] copy = array;
                array = new int[copy.length + 1][maxSize];
                System.arraycopy(copy, 0, array, 0, copy.length);
                array[copy.length] = numbers;
            }
            number++;
        }

        int k;
        while (true) {
            try {
                k = Integer.parseInt(inputReader.prompt("Podaj k"));
                if (k < 0) {
                    shellHelper.printError("Podano liczbę ujemną dla k! Musi być nieujemna.");
                    continue;
                }
                if (k > number) {
                    shellHelper.printError("Wartość k nie może być większa niż liczba wprowadzonych wierszy!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                shellHelper.printError("Podano nieprawidłową wartość dla k! Musi być liczbą całkowitą.");
            }
        }

        for (int[] row : array) {
            for (int value : row) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }
        System.out.println("K: " + k);

        FirstSearchRequest request = new FirstSearchRequest(array, k);
        ResponseEntity<FirstSearchResponse> responseEntity = sendDataToApi(request);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            shellHelper.printSuccess("Dane zostały wysłane na serwer:");
            shellHelper.printSuccess(responseEntity.getBody().toString());
        } else {
            shellHelper.printError("Wystąpił błąd podczas wysyłania danych na serwer.");
        }
    }
    private ResponseEntity<FirstSearchResponse> sendDataToApi(FirstSearchRequest request) {
        System.out.println(jwtTokenManager.getJwtToken());
        return webClient.post()
                .uri("/algorithm/dfs")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenManager.getJwtToken())
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .toEntity(FirstSearchResponse.class)
                .block();
    }
}
