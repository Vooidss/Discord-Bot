package com.techovision.mybot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListSlashCommands {

    private Map<String,String> Answers = new HashMap<>();
     private String SlashCommand;
     private String Answer;

    public ListSlashCommands(String SlashCommand,String Answer){

        this.Answer = Answer;
        this.SlashCommand = SlashCommand;

        Answers.put(SlashCommand,Answer);

    }

    public ListSlashCommands(){
    }

    public String getSlashCommand() {
        return SlashCommand;
    }

    public String getAnswer() {
        return Answer;
    }

    public Map<String, String> getAnswers() {
        return Answers;
    }
}
