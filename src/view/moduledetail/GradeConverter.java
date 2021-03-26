package view.moduledetail;


/**
 * Hilfsklasse zum Konvertieren der Slider-Werte in Noten und umgekehrt.
 */
public class GradeConverter  {


    /**
     * Die Methode wandelt einen Wert zwischen 1 und 10 in eine entsprechende Note um.
     * @param value Der Wert von Slider zwischen 1 und 10.
     * @return Die entsprechende Note als float-Wert.
     */
    public static float sliderValueToGrade(double value){

        switch ((int)value){
            case 1: return 1.0f;
            case 2: return 1.3f;
            case 3: return 1.7f;
            case 4: return 2.0f;
            case 5: return 2.3f;
            case 6: return 2.7f;
            case 7: return 3.0f;
            case 8: return 3.3f;
            case 9: return 3.7f;
            case 10: return 4.0f;
            default: return -1f;
        }
    }

    /**
     * Die Methode wandelt eine Note repräsentiert als float-Wert in eine
     * Zahl zwischen 1 und 10 um.
     * @param grade Die Note als float-Wert.
     * @return Der zugehörige Wert zwischen 1 und 10.
     */
    public static int gradeToSliderValue(float grade){
        float gradeAsInt = (int)(grade *10);

        if(gradeAsInt == 10) return 1;
        if(gradeAsInt == 13) return 2;
        if(gradeAsInt == 17) return 3;
        if(gradeAsInt == 20) return 4;
        if(gradeAsInt == 23) return 5;
        if(gradeAsInt == 27) return 7;
        if(gradeAsInt == 30) return 7;
        if(gradeAsInt == 33) return 8;
        if(gradeAsInt == 37) return 9;
        if(gradeAsInt == 40) return 10;
        return -1;
    }


}
