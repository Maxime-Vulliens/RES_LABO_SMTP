import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Maxime on 06.05.18.
 */
public class Groups {

    private String sender;
    private ArrayList<String> receiver;
    private String fileName;
    private String fileMsg;

    private String message;

    // Constructor
    // filename : file containing mail address
    // fileMessage : file containing message
    // nbReceiver : nb of receiver
    public Groups(String filename,String fileMessage, int nbReceiver ) {

        fileName = filename;
        fileMsg = fileMessage;

        receiver = new ArrayList<String>();

        sender = pickRandomLine(fileName);

        for (int i = 0 ; i < nbReceiver; i++){
            receiver.add(pickRandomLine(fileName));
        }
    }

    // Crée un array list de mail avec un mail pour chaque
    // receiver du group
    public ArrayList<Mail> getMessage (){

        message = pickRandomLine(fileMsg);

        ArrayList<Mail> tmp = new ArrayList<Mail>();
        for(int i = 0; i < receiver.size(); i++){

            tmp.add(new Mail(sender,receiver.get(i),message));

        }
        return tmp;
    }

    // Function use to picj a random line in a file given
    // as argument
    private String pickRandomLine(String filename){

        String line = "";
        try {
            Random random = new Random();
            int randomInt = random.nextInt(100);

            File file = new File(filename);
            FileReader fread = new FileReader(file);
            BufferedReader reader = new BufferedReader(fread);

            line = reader.readLine();
            for (int i = 0; i < randomInt + 1; i++) {
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return line;

    }

}
