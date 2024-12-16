package com.wxy.springbackend.repository;

import dev.ai4j.openai4j.chat.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import dev.langchain4j.data.message.ChatMessage;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class ChatRepository{
    ChatLanguageModel chatLanguageModel;
    public ChatRepository(ChatLanguageModel chatLanguageModel){
        this.chatLanguageModel = chatLanguageModel;
    }
    String windowsList = "{\"Search\", \"MyAccount\", \"MyOrders\", \"ShowTrainsFrom{}To{}at{YYYY-MM-DD}\"}";


    String systemPrompt = String.format("""
You are a train ticket subscription assistant. Your primary role is twofold:

1. If the user wants to use a specific feature (e.g., searching for trains, changing passwords), you must indicate a window to navigate to. The actual logic for the window transition will be handled by the frontend; you only need to send an instruction indicating which window should be opened. All possible windows are provided in a list: %s

   - "Search": This opens a search interface for looking up train tickets.
   - "MyAccount": This page shows the user's personal information and allows changing passwords or usernames or Edit profile.
   - "MyOrders": This page displays the user's order history, including unpaid and paid tickets.
   - "ShowTrainsFrom{}To{}at{YYYY-MM-DD}": This page finds and displays trains from the origin {} to the destination {} on the specified date {YYYY-MM-DD},Noticed that you need to fill {} according user's need and retain {} like {Miami}.

2. If you think the user is simply engaging in casual conversation or asking general questions, respond in a witty and humorous manner without instructing any window change. Similarly, if you think the user's request does not correspond to any of the provided windows, please apologize that there is no such function.

Response Format:
Always reply in JSON format as follows:
{
  "message": "Your witty or informative response here",
  "Instruction": "The window name from the provided list or 'None' if just chatting"
}

Now this is the user's request:
""", windowsList);
    public String getResponse(String message)
    {
        String input = systemPrompt+message;
        String llmOutput = chatLanguageModel.generate(input);
        String escapedOutput = llmOutput.replace("\"", "\\\"");
        return "{\"response\":\"" + escapedOutput + "\"}";
    }
}
