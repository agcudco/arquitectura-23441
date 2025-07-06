package ec.edu.espe.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {


    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/consulta/")
    public String chat(@RequestParam String message) {
        return this.chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/stories/")
    @ResponseBody
    public Map<String, String> stories(@RequestParam String texto) {
        String storie = this.chatClient.prompt()
                .user(texto)
                .call()
                .content();

        Map<String, String> response = new HashMap<>();
        response.put("storie", storie);
        return response;
    }


}
