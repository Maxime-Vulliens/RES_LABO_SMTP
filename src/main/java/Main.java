
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Maxime on 06.05.18.
 */
public class Main {


    public static void main(String[] args) throws IOException {
        ArrayList<Groups> mygroups = new ArrayList<Groups>();
        ArrayList<Mail> mailPerGroup = new ArrayList<Mail>();


        // Permet de récupérer les entrées utilisateurs
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        // Crée le client qui se connecte au serveur
        Client mock = new Client(25000);

        // Demande le nombre de groupe
        System.out.println("Bienvenue dans l'application : sendMail");
        System.out.println("Combien de groupe voulez vous formez ?");
        int nbGroups = reader.nextInt(); // Scans the next token of the input as an int.

        int nbPers;
        // Demande le nb de personne par groupe
        do {
            System.out.println("Combien de personne par groupe ?");
            nbPers = reader.nextInt(); // Scans the next token of the input as an int.
        } while(nbPers <= 3);


        for (int i = 0; i < nbGroups; i++){
            mygroups.add(new Groups("/Users/Maxime/Documents/HEIG-VD/addresslist.txt",
                    "/Users/Maxime/Documents/HEIG-VD/messagelist.txt",nbPers-1));

        }

        for (Groups grp : mygroups){
            mailPerGroup = grp.getMessage();
            for (Mail msg : mailPerGroup){
                mock.connect();
                mock.sendMessage(msg);
                mock.diconnect();
            }
        }
    }


}
