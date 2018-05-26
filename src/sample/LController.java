package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LController {
    private String daneMalarza = new String();

    private List<String> listaObrazow = new ArrayList<>();
    private List<String> listaOpisowObrazow = new ArrayList<>();

    public LController(String daneMalarza) {
        this.daneMalarza = daneMalarza;
        czytajDane();
    }

    void czytajDane(){
        BufferedReader br = null;
        File plik = new File("src/malarze/"+daneMalarza);

        String tmp = null;
        int licznik = 1;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(plik),"ISO-8859-2"));

            while((tmp = br.readLine()) != null) {
                if (licznik > 2) {
                    String[] poPrzerobieniu = tmp.trim().split("\\s+");
                    String[] opisPrzerobiony = tmp.trim().split("\\t+");
                    String opis = "";

                    poPrzerobieniu[0] = poPrzerobieniu[0].replaceAll("^\"|\"$", "");
                    listaObrazow.add(poPrzerobieniu[0]);

                    for (int i = 1; i < opisPrzerobiony.length; i++) {
                        opis += opisPrzerobiony[i].replaceAll("\"", "") + "\n";
                    }
                    listaOpisowObrazow.add(opis);
                }
                licznik++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null)
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDaneMalarza() {
        if(daneMalarza.startsWith("&nbsp;")) return daneMalarza.substring(6,daneMalarza.length() - 4);
        return daneMalarza.substring(0,daneMalarza.length() - 4);
    }

    public List<String> getListaObrazow() {
        return listaObrazow;
    }

    public List<String> getListaOpisowObrazow() {
        return listaOpisowObrazow;
    }
}
