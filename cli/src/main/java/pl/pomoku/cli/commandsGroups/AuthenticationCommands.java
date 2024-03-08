package pl.pomoku.cli.commandsGroups;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pomoku.cli.manager.JwtTokenManager;
import pl.pomoku.cli.shell.InputReader;
import pl.pomoku.cli.shell.ShellHelper;
import pl.pomoku.model.dto.request.LoginRequest;
import pl.pomoku.model.dto.response.AuthenticationResponse;
import reactor.core.publisher.Mono;

@ShellComponent
@RequiredArgsConstructor
public class AuthenticationCommands {
    private final InputReader inputReader;
    private final WebClient webClient;
    private final ShellHelper shellHelper;
    private final JwtTokenManager jwtTokenManager;
    @ShellMethod(key = "login", value = "Loguje uzytkownika do systsemu generując klucz JWT")
    public void login() {
        String email = inputReader.prompt("Podaj email");
        String password = inputReader.prompt("Podaj hasło");
        Mono<AuthenticationResponse> responseMono = webClient.post()
                .uri("/auth/login")
                .bodyValue(new LoginRequest(email, password))
                .retrieve().bodyToMono(AuthenticationResponse.class);

        responseMono.subscribe(
                response -> {
                    shellHelper.print("\nToken JWT");
                    shellHelper.printSuccess(response.token());
                    jwtTokenManager.saveJwtToken(response.token());
                },
                error -> {
                    shellHelper.printError("Błąd logowania: " + error.getMessage());

                }
        );
    }
}
