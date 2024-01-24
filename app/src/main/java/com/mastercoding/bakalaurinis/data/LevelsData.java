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
                "Plokščiosios figūros", "Tekstiniai uždaviniai")));
        levels.add(new Level("3 lygis", Arrays.asList("Teigiami, neigiami, realieji skičiai", "Laipsniai", "Lygtys",
                "Plokščiosios figūros", "Procentai", "Kampai ir tiesės")));
    }

    public List<Level> getLevels() {
        return levels;
    }

    //naudojamas leveliu meniu pavadinimams uzkrauti
    public List<String> getLevelNames(){
        return levels.stream().map(Level::getLevelName).collect(Collectors.toList());
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
