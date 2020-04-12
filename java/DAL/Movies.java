package DAL;

public class Movies {
    private final int ID;
    private String description;

    public Movies(int ID,String description) {
        this.ID = ID;
        this.description = description;
    }
    public int getID(){
        return ID;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String des) {
        description = des;
    }
    @Override
    public String toString() {
        return "id: " + ID + " pershkrimi " + description;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Movies){
            Movies a = (Movies) o;
            if(ID == a.ID) {
                return true;
            }
        }
        return false;
    }
}

