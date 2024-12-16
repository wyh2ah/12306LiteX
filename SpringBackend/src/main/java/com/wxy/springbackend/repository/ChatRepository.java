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
import java.util.Map;

@Repository
public class ChatRepository{
    ChatLanguageModel chatLanguageModel;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public ChatRepository(ChatLanguageModel chatLanguageModel){
        this.chatLanguageModel = chatLanguageModel;
    }
    String windowsList = "{Search, MyAccount, MyOrders, ShowTrains(From, To, Date)";


    String systemPrompt = String.format("""
You are a train ticket subscription assistant. Your primary role is twofold:

1. If the user wants to use a specific feature (e.g., searching for trains, changing passwords), you must indicate a window to navigate to. The actual logic for the window transition will be handled by the frontend; you only need to send an instruction indicating which window should be opened. All possible windows are provided in a list: %s

   - "Search": This opens a search interface for looking up train tickets.
   - "MyAccount": This page shows the user's personal information and allows changing passwords or usernames or Edit profile.
   - "MyOrders": This page displays the user's order history, including unpaid and paid tickets.
   - "ShowTrains(From, To, Date)": This page finds and displays trains from the origin to the destination on the specified date {YYYY-MM-DD},Noticed that you just need to give ShowTrains and put the parameter in the param.

2. If you think the user is simply engaging in casual conversation or asking general questions, respond in a witty and humorous manner without instructing any window change. Similarly, if you think the user's request does not correspond to any of the provided windows, please apologize that there is no such function.

Response Format:
Always reply in JSON format as follows:
{
  "message": "Your witty or informative response here",
  "Instruction": "The window name from the provided list or 'None' if just chatting",
  "Param1": "The param1 of the instruction, like in "New York" in ShowTrainsFrom{}To{}at{YYYY-MM-DD}, if not, the value is None
  "Param2": "The param2 of the instruction, like in "Miami" in ShowTrainsFrom{}To{}at{YYYY-MM-DD}, if not, the value is None
  "Param3": "The param3 of the instruction, like in "2024-12-23" in ShowTrainsFrom{}To{}at{YYYY-MM-DD}, if not, the value is None
}

Now this is the user's request:
""", windowsList);
    public Map<String, Object> getResponse(String message) {
        try {
            String input = systemPrompt + message;
            String llmOutput = chatLanguageModel.generate(input);

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
