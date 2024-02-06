package com.mastercoding.bakalaurinis.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LevelsData {

    private List<Level> levels;

    public LevelsData() {
        levels = new ArrayList<>();
        levels.add(new Level("1 lygis", Arrays.asList("Atimtis", "Sudėtis", "Daugyba", "Dalyba", "Trumpmenos")));
        levels.add(new Level("2 lygis", Arrays.asList("Natūralieji, sveikieji ir trupmeniniai skaičiai",
                "Teigiamieji ir neigiamieji skaičiai", "Lygtys", "Plokščiosios figūros", "Figūrų plotai")));
        levels.add(new Level("3 lygis", Arrays.asList("Duomenys (vidurkis, mediana, moda)", "Laipsniai", "Lygtys",
                "Plokščiosios figūros", "Procentai", "Kampai ir tiesės")));
        levels.add(new Level("4 lygis", Arrays.asList("Šaknys", "Lygčių sistemos", "Kvadratinės lygtys", "Nelygybės",
                "Plokščiosios figūros", "Erdviniai kūnai", "Tikimybės")));
        levels.add(new Level("5 lygis", Arrays.asList("Skaičių aibės", "Lygtys", "Nelygybės", "Rodiklinės funkcijos",
                "Logaritminės funkcijos", "Trigonometrija", "Vektoriai", "Išvestinės", "Integralai",
                "Kombinatorika", "Tikimybių teorija ir statistika"
        )));
    }

    public List<Level> getLevels() {
        return levels;
    }

    //naudojamas leveliu meniu pavadinimams uzkrauti
    public List<String> getLevelNames() {
        List<String> levelNames = new ArrayList<>();
        for (Level level : levels) {
            levelNames.add(level.getLevelName());
        }
        return levelNames;
    }

    public List<String> getTopicsForLevel(String levelName) {
        for (Level level : levels) {
            if (level.getLevelName().equals(levelName)) {
                return level.getTopics();
            }
        }
        return new ArrayList<>(); //returninam tuscia jeigu nk nerado
    }
}
