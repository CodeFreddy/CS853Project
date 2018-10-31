package DataEntry;

public class Ans {

    private String ansBody;
    private int ansID;
    private int docID;
    private int label;

    public Ans(String ansBody,int ansID,int label){
        this.ansBody = ansBody;
        this.ansID = ansID;
        this.label = label;
//        this.docID = docID;
    }


    public String getAnsBody(){
        return this.ansBody;
    }


}
