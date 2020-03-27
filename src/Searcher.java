import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Searcher {
    public ArrayList<Doctor> searchDoc(ArrayList<Doctor> docs, int typeOfSearch, String searchStr){
        ArrayList<Doctor>docsList=new ArrayList<>();
        switch (typeOfSearch){
            case 0:{
                for(Doctor i:docs){
                    if(i.getSurname().equals(searchStr)){
                        docsList.add(i);
                    }
                }
                break;
            }
            case 1:{
                for(Doctor i:docs){
                    if(i.getProfession().equals(searchStr)){
                        docsList.add(i);
                    }
                }
                break;
            }
            case 2:{
                for(Doctor i:docs){
                    if(i.getWorkPlace().equals(searchStr)){
                        docsList.add(i);
                    }
                }
                break;
            }
        }
        return docsList;
    }

    public ArrayList<Patient> searchPat(ArrayList<Patient> pats, int typeOfSearch, String searchStr){
        ArrayList<Patient> patsList = new ArrayList<>();
        switch (typeOfSearch){
            case 0:{
                for(Patient i:pats){
                    if(i.getSurname().equals(searchStr)){
                        patsList.add(i);
                    }
                }
                break;
            }
            case 1:{
                for(Patient i:pats){
                    if(i.getPassportNum()==Integer.parseInt(searchStr)){
                        patsList.add(i);
                    }
                }
                break;
            }
            case 2:{
                for(Patient i:pats){
                    if(i.getPolisNum()==Integer.parseInt(searchStr)){
                        patsList.add(i);
                    }
                }
                break;
            }
        }
        return patsList;
    }

    public Map<String, ArrayList<Stats>> searchVisit(ArrayList<Visit> visits, int typeOfSearch){
        LinkedHashSet <String> hashSet = new LinkedHashSet<>();
        Map<String,ArrayList<Stats>> result=new LinkedHashMap<>();
        String[]months={"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};
        String[]regions={"Адмиралтейский","Василеостровский","Выборгский","Калининский","Кировкский","Колпинский",
                "Красногвардейский","Красносельский","Кронштадский","Курортный","Московский","Невский","Петроградский",
                "Петродворцовый", "Приморский", "Пушкинский", "Фрунзенский","Центральный"};
        int flag;
        switch (typeOfSearch){
            case 0:{
                for(int j =0;j<12;j++){
                    flag=0;
                    for(Visit i:visits){
                        if(i.getDateOfVisit().getMonth()==j) {
                            hashSet.add(i.getDiagnosis());
                            flag++;
                        }
                    }
                    if(flag!=0) {
                        ArrayList<Stats> stats = new ArrayList<>();
                        for (String i : hashSet) {
                            Stats s = new Stats(i);
                            stats.add(s);
                        }
                        for (Stats k : stats) {
                            for (Visit i : visits) {
                                if ((i.getDateOfVisit().getMonth() == j) && (i.getDiagnosis() == k.getDiseaseName())) {
                                    k.increaseNumberOf();
                                }
                            }
                        }
                        result.put(months[j], stats);
                    }
                }
                break;
            }
            case 1:{
                for(int j =0;j<18;j++){
                    flag=0;
                    for(Visit i:visits){
                        if(i.getRegion()==regions[j]) {
                            hashSet.add(i.getRegion());
                            flag++;
                        }
                    }
                    if(flag!=0) {
                        ArrayList<Stats> stats = new ArrayList<>();
                        for (String i : hashSet) {
                            Stats s = new Stats(i);
                            stats.add(s);
                        }
                        for (Stats k : stats) {
                            for (Visit i : visits) {
                                if ((i.getRegion() == regions[j]) && (i.getDiagnosis() == k.getDiseaseName())) {
                                    k.increaseNumberOf();
                                }
                            }
                        }
                        result.put(months[j], stats);
                    }
                }
                break;
            }

        }
        return result;
    }

}
