package DataEntry;

public class DocTitle {

    private String document_title;
    private int documentID;

    public DocTitle(String s,int id){
        this.document_title = s;
        this.documentID = id;
    }


    public String getDocument_title(){
        return this.document_title;
    }

    public int getDocumentID(){
        return this.documentID;
    }
}
