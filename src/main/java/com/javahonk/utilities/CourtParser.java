package com.javahonk.utilities;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.javahonk.model.CourtChange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CourtParser {

    private static final String url = "http://www.ilboursa.com/marches/aaz.aspx";
    private Document doc;

    public CourtParser() {
        refresh();
    }

    private Document connect() {
        try {
            this.doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, e.getMessage() + ", StackTrace: " + e.toString());
        }
        return this.doc;
    }

    private void refresh() {
        this.doc = connect();
    }

    public Map<String, String[]> getAllCours() {

        Elements elems = doc.select("table#tabQuotes tbody tr");// table tbody:eq(1) tr") ;

        Map<String, String[]> map = new HashMap<>(elems.size());

        for (Element e : elems) {
            String[] cours = new String[8];


            String nom = Jsoup.parse(e.childNode(1).childNode(0).outerHtml()).text();
            String ouverture = Jsoup.parse(e.childNode(3).outerHtml()).text().trim();
            String haut = Jsoup.parse(e.childNode(5).outerHtml()).text().trim();
            String bas = Jsoup.parse(e.childNode(7).outerHtml()).text().trim();
            String volumeTitres = Jsoup.parse(e.childNode(9).outerHtml()).text().trim();
            String volumeDT = Jsoup.parse(e.childNode(11).outerHtml()).text().trim();
            String dernier = Jsoup.parse(e.childNode(13).outerHtml()).text().trim();
            String variation = Jsoup.parse(e.childNode(15).childNode(0).outerHtml()).text().trim();


            cours[0] = nom;
            cours[1] = ouverture;
            cours[2] = haut;
            cours[3] = bas;
            cours[4] = volumeTitres;
            cours[5] = volumeDT;
            cours[6] = dernier;
            cours[7] = variation;

            //System.out.println("cours: " + cours[0] + ", " + cours[1] + ", " + cours[2] + ", " + cours[3] + ", " + cours[4] + ", " + cours[5] + ", " + cours[6] + ", " + cours[7]);
            map.put(nom, cours);

            //System.out.println(map.get(i).toString());
            map.put(cours[0], cours);
        }

        return map;
    }

    public List<CourtChange> getAllCourts() {

        Elements elems = doc.select("table#tabQuotes tbody tr");// table tbody:eq(1) tr") ;

        List<CourtChange> courtChangeList = new ArrayList<CourtChange>();

        for (Element e : elems) {
            String[] court = new String[8];


            String nom = Jsoup.parse(e.childNode(1).childNode(0).outerHtml()).text();
            String ouverture = Jsoup.parse(e.childNode(3).outerHtml()).text().trim();
            String haut = Jsoup.parse(e.childNode(5).outerHtml()).text().trim();
            String bas = Jsoup.parse(e.childNode(7).outerHtml()).text().trim();
            String volumeTitres = Jsoup.parse(e.childNode(9).outerHtml()).text().trim();
            String volumeDT = Jsoup.parse(e.childNode(11).outerHtml()).text().trim();
            String dernier = Jsoup.parse(e.childNode(13).outerHtml()).text().trim();
            String variation = Jsoup.parse(e.childNode(15).childNode(0).outerHtml()).text().trim();


            court[0] = nom;
            court[1] = ouverture;
            court[2] = haut;
            court[3] = bas;
            court[4] = volumeTitres;
            court[5] = volumeDT;
            court[6] = dernier;
            court[7] = variation;

            CourtChange courtChange = new CourtChange(nom, Float.valueOf(ouverture), Float.valueOf(dernier), Float.valueOf(haut), Float.valueOf(bas), Integer.valueOf(volumeTitres), Integer.valueOf(volumeDT), Float.valueOf(variation.substring(0, variation.length() - 1)));

            //System.out.println("court: " + court[0] + ", " + court[1] + ", " + court[2] + ", " + court[3] + ", " + court[4] + ", " + court[5] + ", " + court[6] + ", " + court[7]);
            courtChangeList.add(courtChange);
        }

        return courtChangeList;
    }
}
