package com.zzheads.countrieshbr.controller;

import com.zzheads.countrieshbr.data.CountriesRepository;
import com.zzheads.countrieshbr.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CountriesController {
    @Autowired
    private CountriesRepository countriesRepository;
    private Country oldCountry;

    @RequestMapping({"/","/sorted_name"})
    public String homeSortedByName (ModelMap modelMap) {
        List<Country> allCountries = countriesRepository.getAllSortedByName();
        modelMap.put("countries", allCountries);
        modelMap.put("sorted", "name");
        return "index";
    }

    @RequestMapping("/sorted_pop")
    public String homeSortedByPopulation (ModelMap modelMap) {
        List<Country> allCountries = countriesRepository.getAllSortedByPopulation();
        modelMap.put("countries", allCountries);
        modelMap.put("sorted", "population");
        return "index";
    }

    @RequestMapping ("/{page}")
    public String countryDetails (@PathVariable String page, ModelMap modelMap) {
        Country country = countriesRepository.getByPage(page);
        String[] languages = countriesRepository.getOfficialLanguages(country.getName());
        modelMap.put("country", country);
        modelMap.put("languages", languages);
        return "country";
    }

    @RequestMapping (value = "/delete/{page}", method = RequestMethod.GET)
    public String countryDeleteGet (@PathVariable String page, ModelMap modelMap) {
        oldCountry = countriesRepository.getByPage(page);
        String[] languages = countriesRepository.getOfficialLanguages(oldCountry.getName());
        modelMap.put("country", oldCountry);
        modelMap.put("languages", languages);
        return "sure";
    }

    @RequestMapping (value = "/delete/{page}", method = RequestMethod.POST)
    public String countryDeletePost (@PathVariable String page, ModelMap modelMap) {
        countriesRepository.delete(oldCountry);
        return homeSortedByName(modelMap);
    }

    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String countryAdd (Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "add";
    }

    @RequestMapping (value = "/add", method = RequestMethod.POST)
    public String countryAdd (@ModelAttribute Country country, ModelMap modelMap) {
        country.check();
        countriesRepository.add(country);
        return countryDetails(country.getPage(),modelMap);
    }

    @RequestMapping (value = "/edit/{page}", method = RequestMethod.GET)
    public String countryEdit (@PathVariable String page, Model model) {
        Country country = countriesRepository.getByPage(page);
        oldCountry = country;
        model.addAttribute("country", country);
        return "edit";
    }

    @RequestMapping (value = "/edit/{page}", method = RequestMethod.POST)
    public String countryEdit (@ModelAttribute Country country, ModelMap modelMap) {
        country.check();
        countriesRepository.delete(oldCountry);
        countriesRepository.add(country);
        return countryDetails(country.getPage(),modelMap);
    }

    @RequestMapping ("/clear")
    public String clearBase (ModelMap modelMap) {
        countriesRepository.clearEmpty();
        return homeSortedByName(modelMap);
    }


}
