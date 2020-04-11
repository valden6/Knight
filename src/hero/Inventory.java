package hero;

import objects.Object;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Object> objects;

    public Inventory(List<Object> objects) {
        this.objects = objects;
    }

    public void addInInventory(Object object){
        this.objects.add(object);
    }

    public void removeInInventory(Object object){
        this.objects.remove(object);
    }

    public List<Object> getObjects() {
        return objects;
    }

    public Object getObjectByName(String name){
        Object res = null;
        for (Object object : objects) {
            if( object.getName().equals(name)){
                res = object;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Inventory: " + objects.toString() +
                "\n";
    }
}
