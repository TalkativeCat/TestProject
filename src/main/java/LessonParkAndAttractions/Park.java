package LessonParkAndAttractions;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Park {
    private String name;
    private List<Attraction> attractionList;

    public Park(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Attraction> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(List<Attraction> attractionList) {
        this.attractionList = attractionList;
    }

    public class Attraction {
        private String attractionName;
        private LocalTime timeOpen;
        private LocalTime timeClose;
        private int cost;

        public Attraction(String attractionName, LocalTime timeOpen, LocalTime timeClose, int cost) {
            this.attractionName = attractionName;
            this.timeOpen = timeOpen;
            this.timeClose = timeClose;
            this.cost = cost;
        }

        public String getAttractionName() {
            return attractionName;
        }

        public LocalTime getTimeOpen() {
            return timeOpen;
        }

        public LocalTime getTimeClose() {
            return timeClose;
        }
        public int getCost() {
            return cost;
        }

    }

    public List<Attraction> attractionsList() {
        List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(new Attraction("Карусель", LocalTime.parse("09:00"), LocalTime.parse("22:00"), 300));
        attractionList.add(new Attraction("Качели", LocalTime.parse("09:00"), LocalTime.parse("22:00"), 100));
        attractionList.add(new Attraction("Американские горки", LocalTime.parse("08:00"), LocalTime.parse("23:00"), 400));
        return attractionList;
    }
}
