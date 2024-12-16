package com.wxy.springbackend.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import dev.ai4j.openai4j.chat.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import dev.langchain4j.data.message.ChatMessage;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ChatRepository{
    ChatLanguageModel chatLanguageModel;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public ChatRepository(ChatLanguageModel chatLanguageModel){
        this.chatLanguageModel = chatLanguageModel;
    }
    List<String> windowsList = List.of(
            "Search",
            "MyAccount",
            "MyOrders",
            "ShowTrains(From, To, Date)"
    );

    // Convert instructions to a string (e.g., [Search, MyAccount, MyOrders, ShowTrains(From, To, Date)])
    String windowsListString = windowsList.toString();

    // Define the descriptions as another list
    List<String> instructionsDescription = List.of(
            "\"Search\": Opens a search interface for looking up train tickets.",
            "\"MyAccount\": Displays the user's personal information and allows changing passwords, usernames, or editing their profile.",
            "\"MyOrders\": Shows the user's order history, including both unpaid and paid tickets.",
            "\"ShowTrains(From, To, Date)\": Displays trains from the specified origin to the specified destination on the given date (YYYY-MM-DD)."
    );

    // Join the descriptions into a readable string with line breaks
    String instructionsDescriptionString = String.join("\n", instructionsDescription);
    String systemPrompt = String.format("""
You are a train ticket subscription assistant. Your role has two main parts:

If the user wants to use a specific feature (e.g., searching for trains, changing passwords), you must indicate which window to navigate to. The frontend will handle the actual transition; you only need to provide the appropriate instruction. A list of all possible instructions is given below: %s

Note: Some instructions may look like INS(a,b,c). In such cases, simply provide the instruction name, and then supply the parameters in the response fields. If only one or two parameters are known, set the others to "None".

Here is the description of each instruction:
%s

If the user is simply engaging in casual conversation or asking general questions, respond with wit and humor without instructing a window change. If the user's request does not correspond to any of the provided instructions, please apologize and indicate that no such function exists.

Response Format:
Always reply in JSON as follows, DO NOT provide any other words so that it will be failed to convert to JSON file:
{
  "message": "Your witty or informative response here",
  "Instruction": "The instruction name from the provided list or 'None' if just chatting",
  "Param1": "The first parameter (e.g., 'New York' for ShowTrains). If not applicable, 'None'",
  "Param2": "The second parameter (e.g., 'Miami' for ShowTrains). If not applicable, 'None'",
  "Param3": "The third parameter (e.g., '2024-12-23' for ShowTrains). If not applicable, 'None'"
}

Now this is the user's request:
""", windowsListString, instructionsDescriptionString);
    public Map<String, Object> getResponse(String message) {
        try {
            String input = systemPrompt + message;
            System.out.println(input);
            String llmOutput = chatLanguageModel.generate(input);
            System.out.println(llmOutput);
            // Parse the JSON string into a Map
            return objectMapper.readValue(llmOutput, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            // Handle JSON parsing errors or return a fallback response
            return Map.of(
                    "message", "Sure, Have a good day!",
                    "Instruction", "None",
                    "Param1","None",
                    "Param2","None",
                    "Param3","None"
            );
        }
    }
}
