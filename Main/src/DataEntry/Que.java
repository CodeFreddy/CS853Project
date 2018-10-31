package DataEntry;

public class Que {

    private String question;
    private int questionID;
    public Que(String question, int questionID ){
        this.question = question;
        this.questionID = questionID;
    }

    public String getQuestion(){
        return this.question;
    }

    public int getQuestionID(){
        return this.questionID;
    }
}
