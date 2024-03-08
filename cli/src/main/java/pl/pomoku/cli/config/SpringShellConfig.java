package pl.pomoku.cli.config;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pomoku.cli.shell.InputReader;
import pl.pomoku.cli.shell.ShellHelper;

@Configuration
public class SpringShellConfig {
    @Value("${rest.server.url}")
    public String restServerUrl;

    @Bean
    public InputReader inputReader(@Lazy LineReader lineReader) {
        return new InputReader(lineReader);
    }

    @Bean
    public ShellHelper shellHelper(@Lazy Terminal terminal) {
        return new ShellHelper(terminal);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(restServerUrl + "/api/v1")
                .build();
    }
}
