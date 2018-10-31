import DataEntry.Que;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Indexer {
    private String indexPath = "";

    public Indexer(String indexPath){
        this.indexPath = indexPath;
    }

    private IndexWriter indexWriter = null;

    public IndexWriter getIndexWriter() throws IOException {
        if (indexWriter == null){
            Directory indexDir = FSDirectory.open(Paths.get(indexPath));
            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());

            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

            indexWriter= new IndexWriter(indexDir,config);
        }
        return indexWriter;
    }
    public void closeIndexWriter() throws IOException{
        if (indexWriter != null){
            indexWriter.close();
        }
    }


    public void indexQue(Que que) throws IOException {
        if(que != null){
            IndexWriter writer = getIndexWriter();
            Document doc = new Document();
            FieldType indexType = new FieldType();
            // count max tf for each doc
            HashMap<String, Integer> tf = new HashMap<>();
            int maxTF = 0;
            for (String cur : que.getQuestion().split(" ")) {
                if (tf.containsKey(cur)) {
                    tf.put(cur, tf.get(cur) + 1);
                } else {
                    tf.put(cur, 1);
                }
                if (tf.get(cur) > maxTF) {
                    maxTF = tf.get(cur);
                }
            }

            doc.add(new StringField("id", String.valueOf(que.getQuestionID()), Field.Store.YES));
            doc.add(new TextField("question",que.getQuestion(),Field.Store.YES));
            doc.add(new StringField("maxtf", Integer.toString(maxTF), Field.Store.YES));
            writer.addDocument(doc);
        }
    }

    public void rebuildIndexes(List<Que> list) throws IOException {
        getIndexWriter();
        if (!list.isEmpty()){
            for (Que que : list){
                indexQue(que);
            }
            closeIndexWriter();
        }
    }

}
