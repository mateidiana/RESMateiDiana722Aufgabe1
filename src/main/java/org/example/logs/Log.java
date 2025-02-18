package org.example.logs;
public class Log {
    private int Id;
    private String Charaktername;
    private String Stufe;
    private String Beschreibung;
    private String Datum;
    private Double Kraftpunkte;

    public Log(int id, String name, String stufe, String beschreibung, String date,Double punkte){
        this.Id=id;
        this.Charaktername=name;
        this.Stufe=stufe;
        this.Beschreibung=beschreibung;
        this.Datum=date;
        this.Kraftpunkte=punkte;
    }

    public int getId(){return Id;}
    public void setId(int id){this.Id=id;}

    public String getName(){return Charaktername;}

    public String getDatum(){return Datum;}

    public String getStufe(){return Stufe;}
    public String getBeschreibung(){return Beschreibung;}
    public Double getPunkte(){return Kraftpunkte;}

    @Override
    public String toString() {
        return "Log{" +
                "id=" + this.Id +
                ", Name=" + this.Charaktername +
                ", Stufe=" + this.Stufe +
                ", Beschreibung=" + this.Beschreibung +
                ", date=" + this.Datum +
                ", Kraftpunkte=" + this.Kraftpunkte +
                '}';
    }
}
