package com.zzheads.countrieshbr.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Country {
    // Gather current, accurate information on the five countries you’ll be including in your application. The information must include the following:
    //  Country name
    //  Population
    //  Capital city
    //  Official language(s)
    //  Image of flag
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private long population;
    @Column
    private String capital;
    @Column
    private String language;
    @Column
    private String flag;
    @Column
    private String page;

    public Country(String name, long population, String capital, String language, String flag) {
        this.name = name;
        this.population = population;
        this.capital = capital;
        this.language = language;
        this.flag = flag;
        if (name != null) page = name.toLowerCase().replaceAll(" ", "_");
    }

    public Country() {
    }

    public void check () {
        if (this.name == null) this.name = "unknown";
        if (name.equals("")) this.name = "unknown";
        if (this.population<1) this.population = 0L;
        if (this.capital == null&& this.name.equals("")) this.capital = "unknown";
        if (this.language == null&& this.name.equals("")) this.language = "unknown";
        if (this.flag == null&& this.name.equals("")) this.flag = "unknown";
        page = name.toLowerCase().replaceAll(" ", "_");
    }

    public boolean isEmptyFields() {
        if (name == null) return true;
        if (capital == null) return true;
        if (language == null) return true;
        if (flag == null) return true;
        if (page == null) return true;
        if (name.equals("")) return true;
        if (capital.equals("")) return true;
        if (language.equals("")) return true;
        if (flag.equals("")) return true;
        if (page.equals("")) return true;

        return false;
    }

    public String getPopulationString() {
        // Make string from Long value and insert commas between every 3 numbers from end for easy read big numbers
        if (population == 0) return "0";
        String populationString = Long.toString(population);
        int len = populationString.length();
        int commas = len / 3;
        int startPos = len - commas * 3;
        if (startPos == 0) startPos = 3;
        char[] charArray = populationString.toCharArray();
        ArrayList<Character> result = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            if (startPos == i) {
                result.add(',');
                startPos += 3;
            }
            result.add(charArray[i]);
        }
        StringBuilder builder = new StringBuilder(result.size());
        for (Character ch : result) {
            builder.append(ch);
        }
        populationString = builder.toString();
        return populationString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", capital='" + capital + '\'' +
                ", language='" + language + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
