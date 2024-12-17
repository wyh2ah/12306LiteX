package com.wxy.springbackend.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import dev.ai4j.openai4j.chat.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ChatRepository{
    ChatLanguageModel chatLanguageModel;
    List<String> windowsList;
    List<String> instructionsDescription;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public ChatRepository(ChatLanguageModel chatLanguageModel){
        this.windowsList = List.of(
                "Search",
                "MyAccount",
                "MyOrders",
                "ShowTrains"
        );
        this.instructionsDescription = List.of(
                "Search: Opens a search interface for looking up train tickets.",
                "MyAccount: Displays the user's personal information and allows changing passwords, usernames, or editing their profile.",
                "MyOrders: Shows the user's order history, including both unpaid and paid tickets.",
                "ShowTrains: Three param (From, To, Date), not None. Displays trains from the specified origin to the specified destination on the given date (YYYY-MM-DD). If you are not sure all three params are. Just ask the user"
        );
        this.chatLanguageModel = chatLanguageModel;
    }


    public Map<String, Object> getResponse(String message) {
        int maxAttempts = 3;
        String userLocation = "New York"; // May get this param from Frontend
        String windowsListString = windowsList.toString();
        String instructionsDescriptionString = String.join("\n", instructionsDescription);
        String usefulInfo = getUsefulInfo(userLocation);

        String basePrompt = String.format(systemPrompt, windowsListString, instructionsDescriptionString, usefulInfo);
        String inputMessage = basePrompt + message;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                String llmOutput = chatLanguageModel.generate(inputMessage);

                // Attempt to parse the JSON string into a Map
                Map<String, Object> responseMap = objectMapper.readValue(llmOutput, new TypeReference<Map<String, Object>>() {});

                // Validate Instruction field
                Object instructionObj = responseMap.get("Instruction");
                if (!(instructionObj instanceof String)) {
                    // Instruction is missing or not a string
                    throw new IllegalArgumentException("Instruction field missing or invalid.");
                }

                String instruction = (String) instructionObj;
                // Check if instruction is in the windows list or is "None"
                boolean validInstruction = "None".equalsIgnoreCase(instruction) ||
                        windowsList.contains(instruction);

                if (!validInstruction) {
                    // Instruction is out of range
                    if (attempt < maxAttempts) {
                        inputMessage = basePrompt + message + llmOutput +
                                "\nYour provided Instruction is not one of the allowed values: " + windowsListString +
                                ". Please revise your response to follow the given format and use a valid Instruction.";
                        continue; // retry
                    } else {
                        // Out of attempts
                        return defaultFallbackResponse();
                    }
                }

                // If we reach here, we have valid JSON and a valid instruction
                return responseMap;

            } catch (Exception e) {
                if (attempt < maxAttempts) {
                    inputMessage = basePrompt + message +
                            "\nYour previous response was not in the correct JSON format or had invalid fields. " +
                            "Please strictly follow the JSON response format described above.";
                } else {
                    // After max attempts, return a default fallback
                    return defaultFallbackResponse();
                }
            }
        }

        // If somehow we exit the loop (which we shouldn't), return fallback
        return defaultFallbackResponse();
    }

    private Map<String, Object> defaultFallbackResponse() {
        return Map.of(
                "message", "Sure, Have a good day!",
                "Instruction", "None",
                "Param1", "None",
                "Param2", "None",
                "Param3", "None"
        );
    }

    private static String getUsefulInfo(String userLocation) { // Trivial implementation of Function Call
        LocalDate today = LocalDate.now();
        String date = today.toString(); // Format: YYYY-MM-DD

        return String.format("""
        Some useful knowledge you may need:
        - Today's date: %s
        - User's location: %s
        """, date, userLocation);
    }

    private final String systemPrompt = """
        You are a train ticket subscription assistant. Your role has two main parts:
        
        If the user wants to use a specific feature (e.g., searching for trains, changing passwords), you must indicate which window to navigate to. The frontend will handle the actual transition; you only need to provide the appropriate instruction. A list of all possible instructions is given below: %s
        
        Note: Some instructions may look like INS(a,b,c). In such cases, simply provide the instruction name, and then supply the parameters in the response fields. If only one or two parameters are known, set the others to "None".
        
        Here is the description of each instruction:
        %s
        
        If the user is simply engaging in casual conversation or asking general questions, respond with wit and humor without instructing a window change. If the user's request does not correspond to any of the provided instructions, please apologize and indicate that no such function exists.
        
        %s
        
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
        """;
}
